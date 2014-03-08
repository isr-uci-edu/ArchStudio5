package org.archstudio.bna.logics;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.ThingKeyID;
import org.archstudio.sysutils.FastMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public abstract class AbstractCoordinatingThingLogic extends AbstractThingLogic implements IBNAModelListener {

	public boolean DEBUG = false;

	protected abstract class Updater {

		final List<Object> existentialAsThings = Lists.newArrayList();
		final List<Object> registeredAsThings = Lists.newArrayList();
		final List<ThingKeyID<?>> registeredAsThingKeys = Lists.newArrayList();
		final List<Object> trackedAsThings = Lists.newArrayList();
		final List<ThingKeyID<?>> trackedAsThingKeys = Lists.newArrayList();

		public abstract void update();

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for (Object thingID : registeredAsThings) {
				sb.append("(" + thingID + ")");
			}
			for (ThingKeyID<?> thingKey : registeredAsThingKeys) {
				sb.append("(" + thingKey.getThingID() + ")." + thingKey.getKey());
			}
			sb.append(super.toString());
			return sb.toString();
		}

	}

	private final FastMap<Object, List<Updater>> existentialThings = new FastMap<>(true);
	private final FastMap<Object, Updater> registeredThings = new FastMap<>(true);
	private final FastMap<ThingKeyID<?>, Updater> registeredThingKeys = new FastMap<>(true);
	private final FastMap<Object, List<Updater>> trackedThings = new FastMap<>(true);
	private final FastMap<ThingKeyID<?>, List<Updater>> trackedThingKeys = new FastMap<>(true);

	public AbstractCoordinatingThingLogic(IBNAWorld world) {
		super(world);
	}

	private void registerAsExistential(Updater updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);

		updater.existentialAsThings.add(thing);
		FastMap.createList(existentialThings, thing).add(updater);
	}

	protected void register(Updater updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);

		unregister(registeredThings.get(thing));

		registerAsExistential(updater, thing);
		updater.registeredAsThings.add(thing);
		registeredThings.put(thing, updater);

		if (DEBUG) {
			System.err.println("---");
			System.err.println("Registering: " + updater);
		}

		updater.update();
	}

	@SuppressWarnings("unchecked")
	protected <T extends Updater> T getRegistered(IThing thing) {
		return (T) registeredThings.get(thing);
	}

	protected void unregister(IThing thing) {
		checkNotNull(thing);

		unregister(registeredThings.get(thing));
	}

	protected void register(Updater updater, IThing thing, IThingKey<?> key) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(key);

		unregister(registeredThingKeys.get(ThingKeyID.create(thing, key)));

		registerAsExistential(updater, thing);
		updater.registeredAsThingKeys.add(ThingKeyID.create(thing, key));
		registeredThingKeys.put(ThingKeyID.create(thing, key), updater);

		if (DEBUG) {
			System.err.println("---");
			System.err.println("Registering: " + updater);
		}

		updater.update();
	}

	@SuppressWarnings("unchecked")
	protected <T extends Updater> T getRegistered(IThing thing, IThingKey<?> key) {
		return (T) registeredThingKeys.get(ThingKeyID.create(thing, key));
	}

	protected void unregister(IThing thing, IThingKey<?> key) {
		checkNotNull(thing);
		checkNotNull(key);

		unregister(registeredThingKeys.get(ThingKeyID.create(thing, key)));
	}

	protected void unregister(Updater updater) {
		if (updater != null) {

			if (DEBUG) {
				System.err.println("Unregistering: " + updater);
			}

			for (Object thingID : updater.existentialAsThings) {
				FastMap.getCollection(existentialThings, thingID).remove(updater);
			}
			for (Object thingID : updater.registeredAsThings) {
				registeredThings.remove(thingID);
			}
			for (ThingKeyID<?> thingKeyID : updater.registeredAsThingKeys) {
				registeredThingKeys.remove(thingKeyID);
			}
			for (Object thingID : updater.trackedAsThings) {
				FastMap.getCollection(trackedThings, thingID).remove(updater);
			}
			for (ThingKeyID<?> thingKeyID : updater.trackedAsThingKeys) {
				FastMap.getCollection(trackedThingKeys, thingKeyID).remove(updater);
			}

		}
	}

	protected void track(Updater updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);

		registerAsExistential(updater, thing);
		updater.trackedAsThings.add(thing);
		FastMap.createList(trackedThings, thing).add(updater);
	}

	protected void track(Updater updater, IThing... things) {
		checkNotNull(updater);
		checkNotNull(things);

		for (IThing thing : things) {
			track(updater, thing);
		}
	}

	protected void track(Updater updater, IThing thing, IThingKey<?> key) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(key);

		registerAsExistential(updater, thing);
		updater.trackedAsThingKeys.add(ThingKeyID.create(thing, key));
		FastMap.createList(trackedThingKeys, ThingKeyID.create(thing, key)).add(updater);
	}

	protected void track(Updater updater, IThing thing, Iterable<IThingKey<?>> keys) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(keys);

		registerAsExistential(updater, thing);
		for (IThingKey<?> key : keys) {
			checkNotNull(key);

			updater.trackedAsThingKeys.add(ThingKeyID.create(thing, key));
			FastMap.createList(trackedThingKeys, ThingKeyID.create(thing, key)).add(updater);
		}
	}

	protected void untrack(Updater updater, IThing thing, IThingKey<?> key) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(key);

		updater.trackedAsThingKeys.remove(ThingKeyID.create(thing, key));
		FastMap.getCollection(trackedThingKeys, ThingKeyID.create(thing, key)).remove(updater);
	}

	protected void untrack(Updater updater, IThing thing, Iterable<IThingKey<?>> keys) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(keys);

		for (IThingKey<?> key : keys) {
			checkNotNull(key);

			updater.trackedAsThingKeys.remove(ThingKeyID.create(thing, key));
			FastMap.getCollection(trackedThingKeys, ThingKeyID.create(thing, key)).remove(updater);
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			if (DEBUG) {
				if (FastMap.getCollection(existentialThings, evt.getTargetThing()).size() > 0) {
					System.err.println("Adding: " + FastMap.getCollection(existentialThings, evt.getTargetThing()));
				}
			}
			for (Updater updater : FastMap.getCollection(existentialThings, evt.getTargetThing())) {
				updater.update();
			}
			break;
		case THING_CHANGED:
			if (DEBUG) {
				Set<Updater> updaters = Sets.newHashSet();
				updaters.addAll(FastMap.getCollection(trackedThingKeys, evt.getThingEvent().getThingKeyID()));
				updaters.addAll(FastMap.getCollection(trackedThings, evt.getTargetThing()));
				if (updaters.size() > 0) {
					System.err.println("Updating: " + updaters + " " + evt.getThingEvent());
				}
			}
			for (Updater updater : FastMap.getCollection(trackedThingKeys, evt.getThingEvent().getThingKeyID())) {
				updater.update();
			}
			for (Updater updater : FastMap.getCollection(trackedThings, evt.getTargetThing())) {
				updater.update();
			}
			break;
		case THING_REMOVED:
			if (DEBUG) {
				if (FastMap.getCollection(existentialThings, evt.getTargetThing()).size() > 0) {
					System.err.println("Removing: " + FastMap.getCollection(existentialThings, evt.getTargetThing()));
				}
			}
			for (Updater updater : Lists.newArrayList(FastMap.getCollection(existentialThings, evt.getTargetThing()))) {
				unregister(updater);
			}
			break;
		default:
			// do nothing
		}
	}
}
