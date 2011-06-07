package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * This class maintains relationships between a single source thing and multiple target things--where changes to the
 * source thing should trigger updates to the target thing(s). The target thing(s) must reference the source thing using
 * a {@link IThingRefKey} or by the byThingRefKey property. Source things may be in a different {@link IBNAModel} than
 * the logic and target things. This allows for mirroring values from things in other BNA worlds, such as spline end
 * points of an outer world, being stuck to source things in a sub-world.
 */
public abstract class AbstractMaintainRelationshipsLogic<S extends IThing, T extends IThing> extends AbstractThingLogic
		implements IBNASynchronousModelListener {

	protected final Class<S> sourceThingClass;
	protected final Class<T> targetThingClass;

	@Nullable
	protected final IThingRefKey<S> byThingRefKey;
	protected final ReferenceTrackingLogic rtl;

	protected final Set<IThingKey<?>> sourcePropertyKeys = Sets.newHashSet();
	protected final Set<IThingKey<?>> targetPropertyKeys = Sets.newHashSet();

	public AbstractMaintainRelationshipsLogic(Class<S> sourceThingClass, Class<T> targetThingClass,
			@Nullable IThingRefKey<S> byTargetRefKey, ReferenceTrackingLogic rtl) {
		this.sourceThingClass = checkNotNull(sourceThingClass);
		this.targetThingClass = checkNotNull(targetThingClass);
		this.byThingRefKey = byTargetRefKey;
		this.rtl = checkNotNull(rtl);
		if (byThingRefKey != null) {
			targetPropertyKeys.add(byThingRefKey);
		}
	}

	@Override
	protected void init() {
		super.init();
		maintainAll();
	}

	protected void maintainAll() {
		IBNAModel model = getBNAModel();
		for (Entry<IThingRefKey<?>, Object> entry : rtl.getReferences(byThingRefKey)) {
			T targetThing = castOrNull(model.getThing(entry.getValue()), targetThingClass);
			if (targetThing != null && isTargetThing(targetThing, null)) {
				updateToTarget(null, targetThing, null);
			}
		}
	}

	protected boolean isTargetThing(T targetThing, @Nullable ThingEvent<T, ?, ?> targetThingEvent) {
		if (targetThingEvent != null && !targetPropertyKeys.contains(targetThingEvent.getPropertyName())) {
			return false;
		}
		if (byThingRefKey != null && !targetThing.has(byThingRefKey)) {
			return false;
		}
		return true;
	}

	private void updateToTarget(@Nullable IBNAModel sourceModel, T targetThing,
			@Nullable ThingEvent<T, ?, ?> targetThingEvent) {
		IBNAModel targetModel = getBNAModel();
		if (sourceModel == null) {
			sourceModel = targetModel;
		}

		if (byThingRefKey != null) {
			S sourceThing = castOrNull(byThingRefKey.get(targetThing, getBNAModel()), sourceThingClass);
			if (sourceThing != null && isSourceThing(sourceModel, sourceThing, null)) {
				maintain(sourceModel, sourceThing, null, byThingRefKey, targetThing, targetThingEvent);
			}
		}
		else if (targetThingEvent == null) {
			// the target thing was probably just added, verify its values
			for (IThingRefKey<?> thingRefKey : Iterables.filter(targetThing.keySet(), IThingRefKey.class)) {
				S sourceThing = castOrNull(thingRefKey.get(targetThing, sourceModel), sourceThingClass);
				if (sourceThing != null && isSourceThing(sourceModel, sourceThing, null)) {
					// the thingRefKey pointed to a valid source thing, so it's okay to cast
					@SuppressWarnings("unchecked")
					IThingRefKey<S> sourceThingRefKey = (IThingRefKey<S>) thingRefKey;
					maintain(sourceModel, sourceThing, null, sourceThingRefKey, targetThing, targetThingEvent);
				}
			}
		}
	}

	protected boolean isSourceThing(IBNAModel sourceModel, S sourceThing, @Nullable ThingEvent<S, ?, ?> sourceThingEvent) {
		if (sourceThingEvent != null && !sourcePropertyKeys.contains(sourceThingEvent.getPropertyName())) {
			return false;
		}
		return true;
	}

	protected abstract void maintain(IBNAModel sourceModel, S sourceThing,
			@Nullable ThingEvent<S, ?, ?> sourceThingEvent, IThingRefKey<S> thingRefKey, T targetThing,
			@Nullable ThingEvent<T, ?, ?> targetThingEvent);

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {

		case THING_ADDED:
		case THING_CHANGED:

			IBNAModel model = evt.getSource();
			model.beginBulkChange();
			try {
				IThing thing = evt.getTargetThing();
				if (model.equals(getBNAModel())) {
					T targetThing = SystemUtils.castOrNull(thing, targetThingClass);
					if (targetThing != null) {
						@SuppressWarnings("unchecked")
						ThingEvent<T, ?, ?> targetThingEvent = (ThingEvent<T, ?, ?>) evt.getThingEvent();
						if (isTargetThing(targetThing, targetThingEvent)) {
							// TODO: ignore resulting thing events
							updateToTarget(null, targetThing, targetThingEvent);
						}
					}
				}

				S sourceThing = SystemUtils.castOrNull(thing, sourceThingClass);
				if (sourceThing != null) {
					@SuppressWarnings("unchecked")
					ThingEvent<S, ?, ?> sourceThingEvent = (ThingEvent<S, ?, ?>) evt.getThingEvent();
					if (isSourceThing(model, sourceThing, sourceThingEvent)) {
						updateFromSource(model, sourceThing, sourceThingEvent);
					}
				}
			}
			finally {
				model.endBulkChange();
			}
		}
	}

	protected void updateFromSource(IBNAModel sourceModel, S sourceThing, @Nullable ThingEvent<S, ?, ?> sourceThingEvent) {
		IBNAModel targetModel = getBNAModel();
		for (Entry<IThingRefKey<?>, Iterable<Object>> e : rtl.getReferences(sourceThing.getID(), byThingRefKey)) {
			for (Object value : e.getValue()) {
				T targetThing = SystemUtils.castOrNull(targetModel.getThing(value), targetThingClass);
				if (targetThing != null && isTargetThing(targetThing, null)) {
					@SuppressWarnings("unchecked")
					IThingRefKey<S> sourceThingRefKey = (IThingRefKey<S>) e.getKey();
					maintain(sourceModel, sourceThing, sourceThingEvent, sourceThingRefKey, targetThing, null);
				}
			}
		}
	}
}
