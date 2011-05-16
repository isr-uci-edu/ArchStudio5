package org.archstudio.bna.logics.tracking;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Multimap;

/**
 * This logic keeps track of {@link #mark(ThingKey) marked }thing keys that reference other things by their id.
 */
public class ReferenceTrackingLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	private static final Object synchronizeObject = new Object();
	// referenced thing id --> by property key --> from referencing thing ids
	private final Map<Object, Multimap<IThingRefKey<?>, Object>> toThingIdToKeyTofromThingIds = new MapMaker()
			.makeComputingMap(new Function<Object, Multimap<IThingRefKey<?>, Object>>() {
				@Override
				public Multimap<IThingRefKey<?>, Object> apply(Object input) {
					return HashMultimap.create();
				}
			});

	public ReferenceTrackingLogic(IThingLogicManager tlm) {
	}

	public ReferenceTrackingLogic() {
	}

	@Override
	protected void init() {
		super.init();
		synchronized (synchronizeObject) {
			for (IThing t : getBNAWorld().getBNAModel().getThings()) {
				addReferences(t);
			}
		}
	}

	@Override
	protected void destroy() {
		synchronized (synchronizeObject) {
			toThingIdToKeyTofromThingIds.clear();
		}
		super.destroy();
	}

	protected void addReferences(IThing t) {
		assert Thread.holdsLock(synchronizeObject);

		for (Map.Entry<IThingKey<?>, ?> e : t.entrySet()) {
			IThingKey<?> key = e.getKey();
			if (key instanceof IThingRefKey) {
				updateReference(t.getID(), (IThingRefKey<?>) key, null, e.getValue());
			}
		}
	}

	protected void removeReferences(IThing t) {
		assert Thread.holdsLock(synchronizeObject);

		for (Map.Entry<IThingKey<?>, ?> e : t.entrySet()) {
			IThingKey<?> key = e.getKey();
			if (key instanceof IThingRefKey) {
				updateReference(t.getID(), (IThingRefKey<?>) key, e.getValue(), null);
			}
		}
	}

	protected void updateReference(Object fromThingID, IThingRefKey<?> fromThingKey, Object oldToThingID,
			Object newToThingID) {
		assert Thread.holdsLock(synchronizeObject);

		if (!SystemUtils.nullEquals(oldToThingID, newToThingID)) {
			// remove old referenced thing id
			if (oldToThingID != null) {
				toThingIdToKeyTofromThingIds.get(oldToThingID).remove(fromThingKey, fromThingID);
			}
			// add new referenced thing id
			if (newToThingID != null) {
				toThingIdToKeyTofromThingIds.get(newToThingID).put(fromThingKey, fromThingID);
			}
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			synchronized (synchronizeObject) {
				addReferences(evt.getTargetThing());
			}
			break;
		case THING_REMOVING:
			synchronized (synchronizeObject) {
				removeReferences(evt.getTargetThing());
			}
			break;
		case THING_CHANGED:
			@SuppressWarnings("unchecked")
			ThingEvent<ET, IThingRefKey<?>, ?> te = BNAUtils.castKeyOrNull(evt.getThingEvent(), IThingRefKey.class);
			if (te != null) {
				synchronized (synchronizeObject) {
					updateReference(te.getTargetThing().getID(), te.getPropertyName(), te.getOldPropertyValue(),
							te.getNewPropertyValue());
				}
			}
			break;
		}
	}

	public boolean hasThingsReferencing(Object targetID, @Nullable IThingRefKey<?> byReferenceKey) {
		synchronized (synchronizeObject) {
			return !toThingIdToKeyTofromThingIds.get(targetID).get(byReferenceKey).isEmpty();
		}
	}

	public Iterable<Entry<IThingRefKey<?>, Object>> getReferences(@Nullable IThingRefKey<?> byReferenceKey) {
		synchronized (synchronizeObject) {
			Multimap<IThingRefKey<?>, Object> references = HashMultimap.create();
			if (byReferenceKey != null) {
				for (Multimap<IThingRefKey<?>, Object> toKeyTofromThingIds : toThingIdToKeyTofromThingIds.values()) {
					references.putAll(byReferenceKey, toKeyTofromThingIds.get(byReferenceKey));
				}
			}
			else {
				for (Multimap<IThingRefKey<?>, Object> toKeyTofromThingIds : toThingIdToKeyTofromThingIds.values()) {
					references.putAll(toKeyTofromThingIds);
				}
			}
			return references.entries();
		}
	}

	public Iterable<Entry<IThingRefKey<?>, Iterable<Object>>> getReferences(Object targetID,
			@Nullable IThingRefKey<?> byReferenceKey) {
		synchronized (synchronizeObject) {
			if (byReferenceKey != null) {

				return Collections.<IThingRefKey<?>, Iterable<Object>> singletonMap(byReferenceKey,
						SystemUtils.copyIterable(toThingIdToKeyTofromThingIds.get(targetID).get(byReferenceKey)))
						.entrySet();
			}
			return SystemUtils.cast(SystemUtils.copyIterable(toThingIdToKeyTofromThingIds.get(targetID).asMap()
					.entrySet()));
		}
	}
}
