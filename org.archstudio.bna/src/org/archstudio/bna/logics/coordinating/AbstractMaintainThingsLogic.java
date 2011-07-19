package org.archstudio.bna.logics.coordinating;

import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.collect.Sets;

/**
 * This class maintains relationships between a single source thing and multiple
 * target things--where changes to the source thing should trigger updates to
 * the target thing(s). The target thing(s) must be present in the same world as
 * this logic. However, the source thing may exist in another model. This allows
 * for mirroring values from things in other BNA worlds, such as spline end
 * points in an outer world, to be stuck to source things in a sub-world.
 */
public abstract class AbstractMaintainThingsLogic<S extends IThing, T extends IThing> extends AbstractThingLogic
		implements IBNASynchronousModelListener {

	protected final Class<? extends S> sourceThingClass;
	protected final Class<? extends T> targetThingClass;

	protected final Set<ThingKey<?, V>> sourcePropertyKeys = Sets.newHashSet();
	protected final Set<ThingKey<?, V>> targetPropertyKeys = Sets.newHashSet();

	public AbstractMaintainThingsLogic(Class<S> sourceThingClass, Class<T> targetThingClass) {
		if (sourceThingClass == null) {
			throw new NullPointerException();
		}
		if (targetThingClass == null) {
			throw new NullPointerException();
		}

		this.sourceThingClass = sourceThingClass;
		this.targetThingClass = targetThingClass;
	}

	@Override
	protected void init() {
		super.init();
		maintainAll();
	}

	/**
	 * The default implementation of this method queries the world model for
	 * things satisfying {@link #isTargetThing(IThing, ThingEvent)} and calls
	 * the {@link #updateToTarget(IBNAModel, IThing, ThingEvent)} method for
	 * each target thing found.
	 */
	protected void maintainAll() {
		for (IThing thing : getBNAWorld().getBNAModel().getThings()) {
			T targetThing = SystemUtils.castOrNull(thing, targetThingClass);
			if (targetThing != null && isTargetThing(targetThing, null)) {
				updateToTarget(null, targetThing, null);
			}
		}
	}

	/**
	 * This method determines if a BNA event is significant to the relationship
	 * maintained by this logic and updates target things as necessary. If the
	 * BNA event originated from the logic's own BNA world, then this logic
	 * checks whether the BNA event could represent a change to a source or
	 * target thing. Otherwise, if the BNA event originated from another BNA
	 * world (such as a sub-world), this logic only checks whether the BNA event
	 * may represent a change to a source thing.
	 */
	@Override
	public final void bnaModelChangedSync(BNAModelEvent<IThing, ThingKey<?, V>, ?> evt) {
		switch (evt.getEventType()) {

		case THING_ADDED:
		case THING_CHANGED:

			IBNAModel model = evt.getSource();
			IThing thing = evt.getTargetThing();
			// for added things, the thingEvent will be null, but that's okay
			ThingEvent<?, ?, ?> thingEvent = evt.getThingEvent();

			if (model.equals(getBNAWorld().getBNAModel())) {
				T targetThing = SystemUtils.castOrNull(thing, targetThingClass);
				if (targetThing != null) {
					@SuppressWarnings("unchecked")
					ThingEvent<T, ?, ?> targetThingEvent = (ThingEvent<T, ?, ?>) thingEvent;
					if (isTargetThing(targetThing, targetThingEvent)) {
						// TODO: ignore resulting thing events?
						updateToTarget(null, targetThing, targetThingEvent);
					}
				}
			}

			S sourceThing = SystemUtils.castOrNull(thing, sourceThingClass);
			if (sourceThing != null) {
				@SuppressWarnings("unchecked")
				ThingEvent<S, ?, ?> sourceThingEvent = (ThingEvent<S, ?, ?>) thingEvent;
				if (isSourceThing(model, sourceThing, sourceThingEvent)) {
					updateFromSource(model, sourceThing, sourceThingEvent);
				}
			}
		}
	}

	/**
	 * Updates target thing(s) given the source thing in the given source model.
	 * <b>Note:</b> the source model may not be the same as this logic's world's
	 * BNA model.
	 * 
	 * @param sourceModel
	 *            the {@link IBNAModel} that contains the source thing
	 * @param sourceThing
	 *            the source {@link IThing}
	 * @param sourceThingEvent
	 *            the event that triggered a call to this method. This may be
	 *            <code>null</code> indicating that the logic is being
	 *            initialized or that the source thing has just been added.
	 */
	abstract protected void updateFromSource(IBNAModel sourceModel, S sourceThing, ThingEvent<S, ?, ?> sourceThingEvent);

	/**
	 * Updates target thing. The source model may be <code>null</code> to
	 * indicate that it is not specified.
	 * 
	 * @param sourceModel
	 *            the {@link IBNAModel} of the potential source things. This may
	 *            be <code>null</code> indicating that it is not specified. Most
	 *            implementations should assume that such a value indicates that
	 *            the world model should be used.
	 * @param targetThing
	 *            the target {@link IThing} for the relationships
	 * @param targetThingEvent
	 *            the event that triggered a call to this method. This may be
	 *            <code>null</code> indicating that the logic is being
	 *            initialized or that the target thing has just been added.
	 */
	abstract protected void updateToTarget(IBNAModel sourceModel, T targetThing, ThingEvent<T, ?, ?> targetThingEvent);

	/**
	 * Determines if the given thing (and optionally thing event) should be used
	 * in a call to {@link #updateFromSource(IBNAModel, IThing, ThingEvent)}.
	 * This check needs to be relatively fast, further checking can be done in
	 * the {@link #updateFromSource(IBNAModel, IThing, ThingEvent)} method.
	 * 
	 * @param sourceModel
	 *            The model containing the source thing
	 * @param sourceThing
	 *            The source thing of interest
	 * @param sourceThingEvent
	 *            The ThingEvent that triggered this query, or <code>null</code>
	 *            if this query is a result of the logic being initialized or a
	 *            thing being added.
	 * @return <code>true</code> if the thing likely represents a source thing
	 *         in a maintenance relationship and if the thing event represents a
	 *         change related to the maintenance relationship (when present),
	 *         <code>false</code> otherwise.
	 */
	protected boolean isSourceThing(IBNAModel sourceModel, S sourceThing, ThingEvent<S, ?, ?> sourceThingEvent) {
		if (sourceThingEvent != null) {
			return sourcePropertyKeys.isEmpty() || sourcePropertyKeys.contains(sourceThingEvent.getPropertyName());
		}
		return false;
	}

	/**
	 * Determines if the given thing (and optionally thing event) should be used
	 * in a call to {@link #updateToTarget(IBNAModel, IThing, ThingEvent)} This
	 * check needs to be relatively fast, further checking can be done in the
	 * {@link #updateToTarget(IBNAModel, IThing, ThingEvent)} method.
	 * 
	 * @param targetThing
	 *            The target thing of interest
	 * @param targetThingEvent
	 *            The ThingEvent that triggered this query, or <code>null</code>
	 *            if this query is a result of the logic being initialized or a
	 *            thing being added.
	 * @return <code>true</code> if the thing represents a target thing in a
	 *         maintenance relationship and if the thing event represents a
	 *         change related to the maintenance relationship (when present),
	 *         <code>false</code> otherwise.
	 */
	protected boolean isTargetThing(T targetThing, ThingEvent<T, ?, ?> targetThingEvent) {
		if (targetThingEvent != null) {
			return targetPropertyKeys.isEmpty() || targetPropertyKeys.contains(targetThingEvent.getPropertyName());
		}
		return false;
	}
}
