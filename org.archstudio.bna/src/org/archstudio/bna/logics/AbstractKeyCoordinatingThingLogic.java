package org.archstudio.bna.logics;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.tracking.ThingReferenceTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingReferenceTrackingLogic.Reference;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.IgnoreEvents;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public abstract class AbstractKeyCoordinatingThingLogic extends AbstractThingLogic implements IBNAModelListener {

	protected final ThingReferenceTrackingLogic referenceLogic;
	protected final ThingValueTrackingLogic valueLogic;

	private final Set<IThingKey<?>> trackedKeys = Sets.newHashSet();
	private final Set<IThingRefKey<?>> trackedRefKeys = Sets.newHashSet();
	private final Set<List<Object>> toUpdate = Sets.newHashSet();
	private final IgnoreEvents ignoreEvents;
	private final boolean ignoreUpdateEvents;

	public AbstractKeyCoordinatingThingLogic(IBNAWorld world, boolean ignoreUpdateEvents) {
		super(world);
		referenceLogic = logics.addThingLogic(ThingReferenceTrackingLogic.class);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		ignoreEvents = new IgnoreEvents(world.getBNAModel());
		this.ignoreUpdateEvents = ignoreUpdateEvents;
	}

	protected void track(IThingKey<?> key) {
		checkNotNull(key);
		trackedKeys.add(key);
		if (key instanceof IThingRefKey) {
			trackedRefKeys.add((IThingRefKey<?>) key);
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			for (Map.Entry<IThingKey<?>, ?> entry : thing.entrySet()) {
				IThingKey<?> key = entry.getKey();
				if (trackedKeys.contains(key)) {
					toUpdate.add(Lists.newArrayList(thing, key));
				}
			}
			if (trackedRefKeys.size() > 0) {
				for (Reference reference : referenceLogic.getReferences(thing)) {
					IThingRefKey<?> fromKey = reference.getFromKey();
					if (trackedRefKeys.contains(fromKey)) {
						IThing fromThing = model.getThing(reference.getFromThingID());
						if (fromThing != null) {
							toUpdate.add(Lists.newArrayList(fromThing, fromKey));
						}
					}
				}
			}
		}
			break;
		case THING_REMOVED:
			if (trackedRefKeys.size() > 0) {
				IThing thing = evt.getTargetThing();
				for (IThingRefKey<?> fromKey : trackedRefKeys) {
					for (IThing fromThing : valueLogic.getThings(fromKey, thing.getID())) {
						toUpdate.add(Lists.newArrayList(fromThing, fromKey));
					}
				}
			}
			break;
		case THING_CHANGED:
			if (!ignoreUpdateEvents || !ignoreEvents.shouldIgnore(evt)) {
				ThingEvent thingEvent = evt.getThingEvent();
				if (trackedKeys.contains(thingEvent.getPropertyName())) {
					toUpdate.add(Lists.newArrayList(thingEvent.getTargetThing(), thingEvent.getPropertyName()));
				}
			}
			break;
		case FLUSH:
			if (toUpdate.size() > 0) {
				if (ignoreUpdateEvents) {
					ignoreEvents.startIgnoring();
				}
				try {
					for (List<Object> update : SystemUtils.getAndClear(toUpdate)) {
						try {
							IThing thing = (IThing) update.get(0);
							IThingKey<?> key = (IThingKey<?>) update.get(1);
							update(thing, key);
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
				finally {
					if (ignoreUpdateEvents) {
						ignoreEvents.stopIgnoring();
					}
				}
			}
			break;
		default:
			// do nothing
		}
	}

	protected abstract void update(IThing thing, IThingKey<?> key);
}
