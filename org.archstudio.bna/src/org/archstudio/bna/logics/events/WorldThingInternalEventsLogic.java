package org.archstudio.bna.logics.events;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.CloneThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.things.utility.NoThing;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Sets;

public class WorldThingInternalEventsLogic extends AbstractThingLogic implements IBNAModelListener {

	public static interface IInternalBNAModelListener {
		public void internalBNAModelChanged(IHasWorld worldThing, BNAModelEvent evt);
	}

	private static IThingKey<Set<WorldChange>> WORLD_CHANGES_TICKER_KEY = CloneThingKey.create(
			"internal-world-changes-ticker", CloneThingKey.<WorldChange> set(null));

	synchronized private static IThing getWorldChangedThing(IBNAWorld world) {
		IBNAModel model = world.getBNAModel();
		IThing worldChangeThing = model.getThing(WorldThingInternalEventsLogic.class);
		if (worldChangeThing == null) {
			worldChangeThing = model.addThing(new NoThing(WorldThingInternalEventsLogic.class));
		}
		return worldChangeThing;
	}

	private static class WorldChange {

		private static AtomicLong uidGenerator = new AtomicLong();

		private final long uid;
		private final IBNAWorld world;
		private final Set<IBNAWorld> worldsNotified;

		public WorldChange(IBNAWorld world) {
			this.uid = uidGenerator.getAndIncrement();
			this.world = world;
			this.worldsNotified = Sets.newHashSet(world);
		}

		public WorldChange(WorldChange worldChange, IBNAWorld notifiedWorld) {
			this.uid = worldChange.uid;
			this.world = worldChange.world;
			Set<IBNAWorld> worldsNotified = Sets.newHashSet(worldChange.worldsNotified);
			worldsNotified.add(notifiedWorld);
			this.worldsNotified = worldsNotified;
		}

		public boolean isNotified(IBNAWorld world) {
			return worldsNotified.contains(world);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (uid ^ uid >>> 32);
			result = prime * result + (world == null ? 0 : world.hashCode());
			result = prime * result + (worldsNotified == null ? 0 : worldsNotified.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			WorldChange other = (WorldChange) obj;
			if (uid != other.uid) {
				return false;
			}
			if (world == null) {
				if (other.world != null) {
					return false;
				}
			}
			else if (!world.equals(other.world)) {
				return false;
			}
			if (worldsNotified == null) {
				if (other.worldsNotified != null) {
					return false;
				}
			}
			else if (!worldsNotified.equals(other.worldsNotified)) {
				return false;
			}
			return true;
		}
	}

	private class WorldChangeTicker implements IBNAModelListener {

		private final IBNAWorld iWorld;
		private Collection<IHasWorld> worldThings = Sets.newHashSet();

		public WorldChangeTicker(IBNAWorld iWorld) {
			this.iWorld = iWorld;
			iWorld.getBNAModel().addBNAModelListener(this);
		}

		public void addWorldThing(IHasWorld worldThing) {
			worldThings.add(worldThing);
		}

		public void removeWorldThing(IHasWorld worldThing) {
			worldThings.remove(worldThing);
		}

		public void dispose() {
			iWorld.getBNAModel().removeBNAModelListener(this);
			worldThings.clear();
		}

		boolean worldChanged = false;
		Set<WorldChange> pendingWorldChanges = Sets.newHashSet();

		@SuppressWarnings("unchecked")
		@Override
		public void bnaModelChanged(BNAModelEvent evt) {
			if (!worldThings.isEmpty()) {
				for (IInternalBNAModelListener l : logics.getThingLogics(IInternalBNAModelListener.class)) {
					for (IHasWorld worldThing : worldThings) {
						l.internalBNAModelChanged(worldThing, evt);
					}
				}

				switch (evt.getEventType()) {
				case THING_CHANGED:
					IThing thing = evt.getTargetThing();
					ThingEvent thingEvent = evt.getThingEvent();
					if (WorldThingInternalEventsLogic.class.equals(thing.getID())) {
						for (WorldChange worldChange : (Set<WorldChange>) thingEvent.getNewPropertyValue()) {
							if (!worldChange.isNotified(iWorld)) {
								pendingWorldChanges.add(new WorldChange(worldChange, iWorld));
							}
						}
					}
					else {
						worldChanged = true;
					}
					break;

				default:
					if (evt.getEventType().isModelModifyingEvent()) {
						worldChanged = true;
					}
					break;

				case BULK_CHANGE_END:
					if (worldChanged) {
						pendingWorldChanges.add(new WorldChange(iWorld));
					}
					getWorldChangedThing(world).set(WORLD_CHANGES_TICKER_KEY, pendingWorldChanges);
					worldChanged = false;
					pendingWorldChanges.clear();
					break;
				}
			}
		}
	}

	protected final ThingTypeTrackingLogic typeLogic;
	private LoadingCache<IBNAWorld, WorldChangeTicker> worldChangeTickers = CacheBuilder.newBuilder().weakKeys()
			.removalListener(new RemovalListener<IBNAWorld, WorldChangeTicker>() {
				@Override
				public void onRemoval(RemovalNotification<IBNAWorld, WorldChangeTicker> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<IBNAWorld, WorldChangeTicker>() {
				@Override
				public WorldChangeTicker load(IBNAWorld key) throws Exception {
					return new WorldChangeTicker(key);
				}
			});

	public WorldThingInternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			register(worldThing, worldThing.getWorld());
		}
		model.doNotMergeEventsForKey(WORLD_CHANGES_TICKER_KEY);
	}

	@Override
	synchronized public void dispose() {
		worldChangeTickers.invalidateAll();
	}

	private void register(IHasWorld worldThing, IBNAWorld iWorld) {
		if (iWorld != null) {
			worldChangeTickers.getUnchecked(iWorld).addWorldThing(worldThing);
		}
	}

	private void unregister(IHasWorld worldThing, IBNAWorld iWorld) {
		if (iWorld != null) {
			worldChangeTickers.getUnchecked(iWorld).removeWorldThing(worldThing);
		}
	}

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
	}
}
