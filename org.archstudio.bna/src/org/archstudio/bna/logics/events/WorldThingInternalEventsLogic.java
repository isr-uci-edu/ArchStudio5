package org.archstudio.bna.logics.events;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class WorldThingInternalEventsLogic extends AbstractThingLogic implements IBNAModelListener {

	public static interface IInternalBNAModelListener {

		public void internalBNAModelChanged(IHasWorld worldThing, BNAModelEvent evt);

	}

	private class WorldData implements IBNAModelListener {

		private final IBNAWorld iWorld;
		private final IThingLogicManager iLogics;
		private final WorldThingInternalEventsLogic iInternalLogic;
		private final List<IHasWorld> worldThings = Lists.newCopyOnWriteArrayList();

		public WorldData(IBNAWorld iWorld) {
			this.iWorld = iWorld;
			iLogics = iWorld.getThingLogicManager();
			iInternalLogic = iLogics.addThingLogic(WorldThingInternalEventsLogic.class);
			iWorld.getBNAModel().addBNAModelListener(this);
			iInternalLogic.changeListeners.add(WorldThingInternalEventsLogic.this);
		}

		public void dispose() {
			iWorld.getBNAModel().removeBNAModelListener(this);
			iInternalLogic.changeListeners.remove(WorldThingInternalEventsLogic.this);
		}

		@Override
		public void bnaModelChanged(BNAModelEvent evt) {
			for (IHasWorld worldThing : worldThings) {
				for (IInternalBNAModelListener l : logics.getThingLogics(IInternalBNAModelListener.class)) {
					l.internalBNAModelChanged(worldThing, evt);
				}
			}
		}

	}

	private static final IThingKey<Integer> INTERNAL_MODEL_CHANGE_TICKER = ThingKey
			.create(WorldThingInternalEventsLogic.class);

	protected final ThingTypeTrackingLogic typeLogic;
	private Map<IBNAWorld, WorldData> worldToWorldData = Maps.newHashMap();
	private List<WorldThingInternalEventsLogic> changeListeners = Lists.newCopyOnWriteArrayList();

	public WorldThingInternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			register(worldThing, worldThing.getWorld());
		}
	}

	@Override
	synchronized public void dispose() {
		for (WorldData worldData : worldToWorldData.values()) {
			worldData.dispose();
		}
		worldToWorldData.clear();
	}

	private void register(IHasWorld worldThing, IBNAWorld iWorld) {
		if (iWorld != null) {
			WorldData worldData = worldToWorldData.get(iWorld);
			if (worldData == null) {
				worldToWorldData.put(iWorld, worldData = new WorldData(iWorld));
			}
			worldThing.set(INTERNAL_MODEL_CHANGE_TICKER, 0);
			worldData.worldThings.add(worldThing);
		}
	}

	private void unregister(IHasWorld worldThing, IBNAWorld iWorld) {
		if (iWorld != null) {
			WorldData worldData = worldToWorldData.get(iWorld);
			if (worldData != null) {
				worldData.worldThings.remove(worldThing);
			}
		}
	}

	private boolean modelChanged = false;

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {

		// update the lists of registered world things
		if (evt.getTargetThing() instanceof IHasWorld) {
			IHasWorld worldThing = (IHasWorld) evt.getTargetThing();
			switch (evt.getEventType()) {
			case THING_ADDED:
				register(worldThing, worldThing.getWorld());
				break;
			case THING_REMOVED:
				unregister(worldThing, worldThing.getWorld());
				break;
			case THING_CHANGED:
				ThingEvent thingEvent = evt.getThingEvent();
				if (thingEvent.getPropertyName().equals(IHasWorld.WORLD_KEY)) {
					unregister(worldThing, (IBNAWorld) thingEvent.getOldPropertyValue());
					register(worldThing, (IBNAWorld) thingEvent.getNewPropertyValue());
				}
				break;
			default:
				// do nothing
			}
		}

		// check for modifications to this world
		switch (evt.getEventType()) {
		case BULK_CHANGE_BEGIN:
			modelChanged = false;
			break;
		case THING_ADDED:
		case THING_REMOVED:
		case THING_RESTACKED:
			modelChanged = true;
			break;
		case THING_CHANGED:
			ThingEvent thingEvent = evt.getThingEvent();
			if (!INTERNAL_MODEL_CHANGE_TICKER.equals(thingEvent.getPropertyName())) {
				modelChanged = true;
			}
			break;
		case BULK_CHANGE_END:
			if (modelChanged) {
				fireInternalBNAModelChanged(world, Sets.newHashSet(world));
			}
		default:
			// do nothing
		}
	}

	private void fireInternalBNAModelChanged(IBNAWorld iWorld, Set<IBNAWorld> worldsAwareOfChange) {
		for (WorldThingInternalEventsLogic l : changeListeners) {
			l.internalBNAModelChanged(iWorld, worldsAwareOfChange);
		}
	}

	private void internalBNAModelChanged(IBNAWorld iWorld, Set<IBNAWorld> worldsAwareOfChange) {
		if (!worldsAwareOfChange.contains(world)) {
			WorldData worldData = worldToWorldData.get(iWorld);
			if (worldData != null) {
				for (IHasWorld worldThing : worldData.worldThings) {
					worldThing.set(INTERNAL_MODEL_CHANGE_TICKER, worldThing.get(INTERNAL_MODEL_CHANGE_TICKER) + 1);
				}
			}
			Set<IBNAWorld> newWorldsAwareOfChange = Sets.newHashSet(worldsAwareOfChange);
			newWorldsAwareOfChange.add(world);
			fireInternalBNAModelChanged(iWorld, newWorldsAwareOfChange);
		}
	}
}
