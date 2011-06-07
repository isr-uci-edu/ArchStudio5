package org.archstudio.bna.logics.coordinating;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.AbstractGenericThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Multimap;

/**
 * This logic performs an action (specified within the key) whenever another thing is found that has the same value
 * stored within a <code>targetKey</code> of the other thing. This is useful for maintaining references to other things.
 * 
 * @see #create(Object, IThingKey, MatchFoundAction)
 */
public class ActionOnMatchingValueLogic extends AbstractThingLogic implements IBNAModelListener {

	public static interface MatchFoundAction<V> {
		public void found(IThing sourceThing, IThingKey<V> sourceKey, IThing targetThing, IThingKey<V> targetKey,
				V value);
	}

	private static final class ExecutableThingKey<D, V> extends AbstractGenericThingKey<D, V> {

		protected final IThingKey<V> targetKey;
		protected final MatchFoundAction<V> matchAction;

		public ExecutableThingKey(D keyData, boolean isFireEventOnChange, IThingKey<V> targetKey,
				MatchFoundAction<V> matchAction) {
			super(keyData, isFireEventOnChange);
			this.targetKey = targetKey;
			this.matchAction = matchAction;
		}
	}

	private static final class ExecutableThingEntry {

		protected final IThing sourceThing;
		protected final ExecutableThingKey<?, ?> sourceKey;

		public ExecutableThingEntry(IThing sourceThing, ExecutableThingKey<?, ?> sourceKey) {
			this.sourceThing = sourceThing;
			this.sourceKey = sourceKey;
		}
	}

	public static <D, V> IThingKey<V> create(D keyData, IThingKey<V> targetKey, MatchFoundAction<V> matchAction) {
		return new ExecutableThingKey<D, V>(keyData, true, targetKey, matchAction);
	}

	Map<Object, Multimap<IThingKey<?>, ExecutableThingEntry>> executableThingKeys = new MapMaker()
			.makeComputingMap(new Function<Object, Multimap<IThingKey<?>, ExecutableThingEntry>>() {
				@Override
				public Multimap<IThingKey<?>, ExecutableThingEntry> apply(Object input) {
					return ArrayListMultimap.create();
				}
			});

	ThingValueTrackingLogic values = null;

	public ActionOnMatchingValueLogic() {
	}

	@Override
	protected void init() {
		values = getBNAWorld().getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		super.init();
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			ET t = evt.getTargetThing();
			for (IThingKey<?> k : t.keySet()) {
				if (k instanceof ExecutableThingKey) {
					addExecutableThingKey(t, (ExecutableThingKey<?, ?>) k, t.get(k));
				}
				checkNewValue(t, k, t.get(k));
			}
			break;
		}
		case THING_CHANGED: {
			IThing t = evt.getTargetThing();
			ThingEvent<ET, EK, EV> te = evt.getThingEvent();
			EK k = te.getPropertyName();
			switch (te.getEventType()) {
			case PROPERTY_SET:
				if (k instanceof ExecutableThingKey) {
					updateExecutableThingKey((ExecutableThingKey<?, ?>) k, t, te.getOldPropertyValue(),
							te.getNewPropertyValue());
				}
				checkNewValue(t, k, te.getNewPropertyValue());
				break;
			case PROPERTY_REMOVED:
				if (k instanceof ExecutableThingKey) {
					removeExecutableThingKey((ExecutableThingKey<?, ?>) k, te.getOldPropertyValue());
				}
				break;
			}
			break;
		}
		case THING_REMOVING:
			ET t = evt.getTargetThing();
			for (IThingKey<?> k : t.keySet()) {
				if (k instanceof ExecutableThingKey) {
					removeExecutableThingKey((ExecutableThingKey<?, ?>) k, t.get(k));
				}
			}
			break;
		}
	}

	private void updateExecutableThingKey(ExecutableThingKey<?, ?> k, IThing t, Object oldValue, Object newValue) {
		removeExecutableThingKey(k, oldValue);
		addExecutableThingKey(t, k, newValue);
	}

	private void removeExecutableThingKey(ExecutableThingKey<?, ?> k, Object value) {
		if (value != null) {
			synchronized (executableThingKeys) {
				executableThingKeys.get(value).remove(k.targetKey, k);
			}
		}
	}

	private void addExecutableThingKey(IThing sourceThing, ExecutableThingKey<?, ?> sourceKey, Object value) {
		if (value != null) {
			synchronized (executableThingKeys) {
				executableThingKeys.get(value).put(sourceKey.targetKey,
						new ExecutableThingEntry(sourceThing, sourceKey));
			}
			checkExecutableThingKey(sourceThing, sourceKey, value);
		}
	}

	private void checkNewValue(IThing targetThing, IThingKey<?> targetKey, Object value) {
		if (value != null) {
			List<ExecutableThingEntry> executableThings = Collections.emptyList();
			synchronized (executableThingKeys) {
				if (executableThingKeys.containsKey(value)) {
					executableThings = Lists.newArrayList(executableThingKeys.get(value).get(targetKey));
				}
			}
			for (ExecutableThingEntry e : executableThings) {
				if (SystemUtils.nullEquals(e.sourceThing.get(e.sourceKey), value)) {
					foundMatch(e.sourceThing, e.sourceKey, targetThing, targetKey, value);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void checkExecutableThingKey(IThing sourceThing, ExecutableThingKey<?, ?> sourceKey, Object value) {
		for (IThing targetThing : getBNAModel().getThings(
				values.getThingIDs((IThingKey<Object>) sourceKey.targetKey, value))) {
			if (SystemUtils.nullEquals(value, targetThing.get(sourceKey.targetKey))) {
				foundMatch(sourceThing, sourceKey, targetThing, sourceKey.targetKey, value);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private final void foundMatch(IThing sourceThing, ExecutableThingKey<?, ?> sourceKey, IThing targetThing,
			IThingKey<?> targetKey, Object value) {
		((MatchFoundAction<Object>) sourceKey.matchAction).found(sourceThing, (IThingKey<Object>) sourceKey,
				targetThing, (IThingKey<Object>) targetKey, value);
	}
}
