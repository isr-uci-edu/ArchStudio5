package org.archstudio.bna.logics.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class WorldThingInternalEventsLogic extends AbstractThingLogic implements IBNAModelListener {

	public static interface IInternalBNAModelListener {

		public void internalBNAModelChanged(IHasWorld worldThing, BNAModelEvent evt);

	}

	private static final IThingKey<Integer> INTERNAL_MODEL_CHANGE_TICKER = ThingKey.create("InternalChangeTicker");
	private static final Set<IHasWorld> worldsBeingNotified = Collections
			.synchronizedSet(Sets.<IHasWorld> newHashSet());

	class InternalModelListener implements IBNAModelListener {

		final IHasWorld worldThing;
		final IBNAModel worldThingModel;

		public InternalModelListener(IHasWorld worldThing, IBNAModel worldThingModel) {
			checkNotNull(worldThing);
			checkNotNull(worldThingModel);

			this.worldThing = worldThing;
			this.worldThingModel = worldThingModel;
			worldThing.set(INTERNAL_MODEL_CHANGE_TICKER, 0);
		}

		@Override
		public void bnaModelChanged(BNAModelEvent evt) {
			// prevent infinite loops
			if (worldsBeingNotified.add(worldThing)) {
				try {
					fireInternalBNAModelEvent(worldThing, evt);
					if (evt.getEventType() == EventType.BULK_CHANGE_END) {
						worldThing.set(INTERNAL_MODEL_CHANGE_TICKER, worldThing.get(INTERNAL_MODEL_CHANGE_TICKER) + 1);
					}
				}
				finally {
					worldsBeingNotified.remove(worldThing);
				}
			}
		}
	}

	protected final ThingTypeTrackingLogic typeLogic;
	protected Map<IHasWorld, InternalModelListener> worldThingListeners = new HashMap<IHasWorld, InternalModelListener>();

	public WorldThingInternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			addListener(worldThing);
		}
	}

	@Override
	synchronized public void dispose() {
		for (IHasWorld vt : Lists.newArrayList(worldThingListeners.keySet())) {
			removeListener(vt);
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getTargetThing() instanceof IHasWorld) {
			IHasWorld worldThing = (IHasWorld) evt.getTargetThing();
			switch (evt.getEventType()) {
			case THING_ADDED:
				addListener(worldThing);
				break;
			case THING_REMOVED:
				removeListener(worldThing);
				break;
			case THING_CHANGED:
				ThingEvent thingEvent = evt.getThingEvent();
				if (thingEvent.getPropertyName().equals(IHasWorld.WORLD_KEY)) {
					removeListener(worldThing);
					addListener(worldThing);
				}
				break;
			default:
				// do nothing
			}
		}
	}

	protected void addListener(IHasWorld worldThing) {
		IBNAWorld world = worldThing.getWorld();
		if (world != null) {
			IBNAModel model = world.getBNAModel();
			InternalModelListener l = new InternalModelListener(worldThing, model);
			worldThingListeners.put(worldThing, l);
			model.addBNAModelListener(l);
		}
	}

	protected void removeListener(IHasWorld worldThing) {
		InternalModelListener l = worldThingListeners.remove(worldThing);
		if (l != null) {
			l.worldThingModel.removeBNAModelListener(l);
		}
	}

	protected void fireInternalBNAModelEvent(IHasWorld worldThing, BNAModelEvent event) {
		for (IInternalBNAModelListener l : logics.getThingLogics(IInternalBNAModelListener.class)) {
			l.internalBNAModelChanged(worldThing, event);
		}
	}
}
