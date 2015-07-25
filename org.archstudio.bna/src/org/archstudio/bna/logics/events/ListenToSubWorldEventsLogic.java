package org.archstudio.bna.logics.events;

import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.utility.NoThing;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Sets;

/**
 * Monitors worlds within {@link IHasWorld things with worlds} for changes. Performs two actions when a change happens
 * in the monitored worlds:
 * <ul>
 * <li>Forwards the event to all logics implementing {@link IInternalBNAModelListener}.</li>
 * <li>Updates a {@link ListenToSubWorldEventsLogic#WORLD_CHANGED_KEY value} of the {@link #worldChangeNotifierThing
 * world change notifier thing} causing a change event in the parent world.</li>
 * </ul>
 * The world change notifier thing is simply used to create a new change event in the local model. This causes listeners
 * to treat the world as if modified even though the original source of the event was from a world in a thing. This is
 * used, among other things, to force a redraw of the top level view.
 * <p/>
 * Care is taken to prevent endless updates in models that have cyclic world references. Instead, each impacted world
 * will receive only one notification.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class ListenToSubWorldEventsLogic extends AbstractThingLogic implements IBNAModelListener {
	/** The key containing the change event that is generated when a {@link IHasWorld monitored world} is modified. */
	private static final IThingKey<WorldChange> WORLD_CHANGED_KEY = ThingKey.create(ListenToSubWorldEventsLogic.class);

	/**
	 * The value set in the {@link #getWorldChangedNotifierThing(IBNAWorld) special thing} in the model, which indicates
	 * that a thing's {@link IHasWorld world} world has been modified. This value is special in that this logic updates
	 * it with the worlds that have already been notified in order to handle cycles of world references in the BNA
	 * model.
	 */
	private static final class WorldChange {
		/**
		 * The worlds that have already been notified of a change to another thing's {@link IHasWorld world}. This is
		 * updated by the logic and then propagated to the {@link #getWorldChangedNotifierThing(IBNAWorld) special
		 * thing} in this world if the world has not already received the event.
		 */
		private final Set<IBNAWorld> worldsNotified;

		/**
		 * Creates a change value with a record that the current world has already received an event for the change
		 * (i.e., the current world has already received a {@link IBNAModelListener} event).
		 *
		 * @param world The world originating the change.
		 */
		public WorldChange(IBNAWorld world) {
			this.worldsNotified = Sets.newHashSet(world);
		}
	}

	/**
	 * Returns the {@link NoThing special thing} in the world that is used to propagate changes from other thing's
	 * {@link IHasWorld world} events.
	 *
	 * @param world The world form which to get the special thing.
	 * @return
	 */
	private static final IThing getWorldChangedNotifierThing(IBNAWorld world) {
		IBNAModel model = world.getBNAModel();
		IThing worldChangedTickerThing = model.getThing(ListenToSubWorldEventsLogic.class);
		if (worldChangedTickerThing == null) {
			worldChangedTickerThing = model.addThing(new NoThing(ListenToSubWorldEventsLogic.class));
		}
		return worldChangedTickerThing;
	}

	/**
	 * A {@link IBNAModelListener BNA model listener} that listens for events in a specific {@link #monitoredWorld
	 * monitored world}.
	 */
	private class WorldChangeMonitor implements IBNAModelListener {
		/** The world that multiple things refer to. */
		private final IBNAWorld monitoredWorld;
		/**
		 * The world change notifier thing in the {@link #reportingToWorld reporting world} that is modified when the
		 * {@link #monitoredWorld monitored world} is changed.
		 */
		private final IThing worldChangeNotifierThing;

		/**
		 * Creates a new {@link WorldChangeMonitor} for the given world.
		 *
		 * @param monitoredWorld The world to be monitored for changes.
		 */
		public WorldChangeMonitor(IBNAWorld monitoredWorld) {
			this.monitoredWorld = Preconditions.checkNotNull(monitoredWorld);
			this.worldChangeNotifierThing = getWorldChangedNotifierThing(world);
			monitoredWorld.getBNAModel().addBNAModelListener(this);
		}

		/**
		 * Disposes this monitor by removing it from the list of listeners in the {@link #monitoredWorld monitored
		 * world}.
		 */
		public void dispose() {
			monitoredWorld.getBNAModel().removeBNAModelListener(this);
		}

		/**
		 * Performs two actions when a change in the {@link #monitoredWorld monitored world} happens:
		 * <ul>
		 * <li>Forwards the event to all logics implementing {@link IInternalBNAModelListener} in the
		 * {@link #reportingToWorld}.</li>
		 * <li>Updates a {@link ListenToSubWorldEventsLogic#WORLD_CHANGED_KEY value} of the
		 * {@link #worldChangeNotifierThing world change notifier thing} causing a change event in the parent world.
		 * </li>
		 * </ul>
		 * However, if a change has already caused the world change notifier thing to be updated it is not updated a
		 * again. This prevents cycles in the {@link IHasWorld} graph from causing endless notifications.
		 */
		@Override
		public void bnaModelChanged(BNAModelEvent evt) {
			if (evt.getThingEvent() != null && evt.getThingEvent().getNewPropertyValue() instanceof WorldChange) {
				WorldChange worldChange = (WorldChange) evt.getThingEvent().getNewPropertyValue();
				if (worldChange.worldsNotified.add(world)) {
					worldChangeNotifierThing.set(WORLD_CHANGED_KEY, worldChange);
				}
			}
			else {
				for (IHasWorld thingWithMonitoredWorld : valueLogic.getThings(IHasWorld.WORLD_KEY, monitoredWorld,
						IHasWorld.class)) {
					for (IInternalBNAModelListener l : logics.getThingLogics(IInternalBNAModelListener.class)) {
						l.internalBNAModelChanged(thingWithMonitoredWorld, evt);
					}
				}
				worldChangeNotifierThing.set(WORLD_CHANGED_KEY, new WorldChange(world));
			}
		}
	}

	/**
	 * The value logic used to search for things that reference the monitored world. These things are used when
	 * informing logics implementing {@link IInternalBNAModelListener}.
	 */
	protected final ThingValueTrackingLogic valueLogic;

	/** The monitors for each monitored world. */
	private LoadingCache<IBNAWorld, WorldChangeMonitor> worldMonitors =
			CacheBuilder.newBuilder().weakKeys().removalListener(new RemovalListener<IBNAWorld, WorldChangeMonitor>() {
				@Override
				public void onRemoval(RemovalNotification<IBNAWorld, WorldChangeMonitor> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<IBNAWorld, WorldChangeMonitor>() {
				@Override
				public WorldChangeMonitor load(IBNAWorld key) throws Exception {
					return new WorldChangeMonitor(key);
				}
			});

	/**
	 * Creates a new instance of this logic for the specified world.
	 *
	 * @param world The world that this logic will be a part of.
	 */
	public ListenToSubWorldEventsLogic(IBNAWorld world) {
		super(world);
		valueLogic = world.getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		for (IThing thing : model.getAllThings()) {
			if (thing instanceof IHasWorld) {
				IBNAWorld thingWorld = ((IHasWorld) thing).getWorld();
				if (thingWorld != null) {
					worldMonitors.getUnchecked(thingWorld);
				}
			}
		}
	}

	/**
	 * Adds things with a world to the list of worlds that are monitored.
	 *
	 * @param evt The BNA model event.
	 */
	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		// If a thing has a world set then create a WorldChangeMonitor to list to its events.
		if (evt.getThingEvent() != null && evt.getTargetThing() instanceof IHasWorld) {
			if (IHasWorld.WORLD_KEY.equals(evt.getThingEvent().getPropertyName())) {
				IBNAWorld world = (IBNAWorld) evt.getThingEvent().getNewPropertyValue();
				if (world != null) {
					worldMonitors.getUnchecked(world);
				}
			}
		}
	}
}
