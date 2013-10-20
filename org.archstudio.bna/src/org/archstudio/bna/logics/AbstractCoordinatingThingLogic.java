package org.archstudio.bna.logics;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastIntMap;
import org.archstudio.sysutils.FastLongMap;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public abstract class AbstractCoordinatingThingLogic extends AbstractThingLogic implements IBNAModelListener {

	public boolean DEBUG = false;

	protected abstract class Updater {

		final List<Integer> existentialAsThings = Lists.newArrayList();
		final List<Integer> registeredAsThings = Lists.newArrayList();
		final List<Long> registeredAsThingKeys = Lists.newArrayList();
		final List<Integer> trackedAsThings = Lists.newArrayList();
		final List<Long> trackedAsThingKeys = Lists.newArrayList();

		public abstract void update();

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for (int uid : registeredAsThings) {
				sb.append("(" + uid + ")");
			}
			for (long uid : registeredAsThingKeys) {
				sb.append("(" + BNAUtils.getThingUID(uid) + ")." + BNAUtils.getThingKey(uid));
			}
			sb.append(super.toString());
			return sb.toString();
		}

	}

	private final FastIntMap<List<Updater>> existentialThings = new FastIntMap<>();
	private final FastIntMap<Updater> registeredThings = new FastIntMap<>();
	private final FastLongMap<Updater> registeredThingKeys = new FastLongMap<>();
	private final FastIntMap<List<Updater>> trackedThings = new FastIntMap<>();
	private final FastLongMap<List<Updater>> trackedThingKeys = new FastLongMap<>();
	private final Set<Updater> toUpdate = Sets.newHashSet();

	public AbstractCoordinatingThingLogic(IBNAWorld world) {
		super(world);
	}

	private void registerAsExistential(Updater updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);

		updater.existentialAsThings.add(thing.getUID());
		FastIntMap.createList(existentialThings, thing.getUID()).add(updater);
	}

	protected void register(Updater updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);

		unregister(registeredThings.get(thing.getUID()));

		registerAsExistential(updater, thing);
		updater.registeredAsThings.add(thing.getUID());
		registeredThings.put(thing.getUID(), updater);

		if (DEBUG) {
			System.err.println("---");
			System.err.println("Registering: " + updater);
		}

		toUpdate.add(updater);
		model.ensureFlush();
	}

	@SuppressWarnings("unchecked")
	protected <T extends Updater> T getRegistered(IThing thing) {
		return (T) registeredThings.get(thing.getUID());
	}

	protected void unregister(IThing thing) {
		checkNotNull(thing);

		unregister(registeredThings.get(thing.getUID()));
	}

	protected void register(Updater updater, IThing thing, IThingKey<?> key) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(key);

		unregister(registeredThingKeys.get(BNAUtils.getThingKeyUID(thing, key)));

		registerAsExistential(updater, thing);
		updater.registeredAsThingKeys.add(BNAUtils.getThingKeyUID(thing, key));
		registeredThingKeys.put(BNAUtils.getThingKeyUID(thing, key), updater);

		if (DEBUG) {
			System.err.println("---");
			System.err.println("Registering: " + updater);
		}

		toUpdate.add(updater);
		model.ensureFlush();
	}

	@SuppressWarnings("unchecked")
	protected <T extends Updater> T getRegistered(IThing thing, IThingKey<?> key) {
		return (T) registeredThingKeys.get(BNAUtils.getThingKeyUID(thing, key));
	}

	protected void unregister(IThing thing, IThingKey<?> key) {
		checkNotNull(thing);
		checkNotNull(key);

		unregister(registeredThingKeys.get(BNAUtils.getThingKeyUID(thing, key)));
	}

	protected void unregister(Updater updater) {
		if (updater != null) {

			if (DEBUG) {
				System.err.println("Unregistering: " + updater);
			}

			for (int thingUID : updater.existentialAsThings) {
				FastIntMap.get(existentialThings, thingUID).remove(updater);
			}
			for (int thingUID : updater.registeredAsThings) {
				registeredThings.remove(thingUID);
			}
			for (long thingKeyUID : updater.registeredAsThingKeys) {
				registeredThingKeys.remove(thingKeyUID);
			}
			for (int thingUID : updater.trackedAsThings) {
				FastIntMap.get(trackedThings, thingUID).remove(updater);
			}
			for (long thingKeyUID : updater.trackedAsThingKeys) {
				FastLongMap.get(trackedThingKeys, thingKeyUID).remove(updater);
			}

			toUpdate.remove(updater);
		}
	}

	protected void track(Updater updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);

		registerAsExistential(updater, thing);
		updater.trackedAsThings.add(thing.getUID());
		FastIntMap.createList(trackedThings, thing.getUID()).add(updater);
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
		updater.trackedAsThingKeys.add(BNAUtils.getThingKeyUID(thing, key));
		FastLongMap.createList(trackedThingKeys, BNAUtils.getThingKeyUID(thing, key)).add(updater);
	}

	protected void track(Updater updater, IThing thing, Iterable<IThingKey<?>> keys) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(keys);

		registerAsExistential(updater, thing);
		for (IThingKey<?> key : keys) {
			checkNotNull(key);

			updater.trackedAsThingKeys.add(BNAUtils.getThingKeyUID(thing, key));
			FastLongMap.createList(trackedThingKeys, BNAUtils.getThingKeyUID(thing, key)).add(updater);
		}
	}

	protected void untrack(Updater updater, IThing thing, IThingKey<?> key) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(key);

		updater.trackedAsThingKeys.remove(BNAUtils.getThingKeyUID(thing, key));
		FastLongMap.get(trackedThingKeys, BNAUtils.getThingKeyUID(thing, key)).remove(updater);
	}

	protected void untrack(Updater updater, IThing thing, Iterable<IThingKey<?>> keys) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(keys);

		for (IThingKey<?> key : keys) {
			checkNotNull(key);

			updater.trackedAsThingKeys.remove(BNAUtils.getThingKeyUID(thing, key));
			FastLongMap.get(trackedThingKeys, BNAUtils.getThingKeyUID(thing, key)).remove(updater);
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			if (DEBUG) {
				if (FastIntMap.get(existentialThings, evt.getTargetThing().getUID()).size() > 0) {
					System.err.println("Adding: " + FastIntMap.get(existentialThings, evt.getTargetThing().getUID()));
				}
			}
			toUpdate.addAll(FastIntMap.get(existentialThings, evt.getTargetThing().getUID()));
			break;
		case THING_CHANGED:
			if (DEBUG) {
				Set<Updater> updaters = Sets.newHashSet();
				updaters.addAll(FastLongMap.get(trackedThingKeys, evt.getThingEvent().getThingKeyUID()));
				updaters.addAll(FastIntMap.get(trackedThings, evt.getTargetThing().getUID()));
				if (updaters.size() > 0) {
					System.err.println("Updating: " + updaters + " " + evt.getThingEvent());
				}
			}
			toUpdate.addAll(FastLongMap.get(trackedThingKeys, evt.getThingEvent().getThingKeyUID()));
			toUpdate.addAll(FastIntMap.get(trackedThings, evt.getTargetThing().getUID()));
			break;
		case THING_REMOVED:
			if (DEBUG) {
				if (FastIntMap.get(existentialThings, evt.getTargetThing().getUID()).size() > 0) {
					System.err.println("Removing: " + FastIntMap.get(existentialThings, evt.getTargetThing().getUID()));
				}
			}
			for (Updater updater : Lists.newArrayList(FastIntMap.get(existentialThings, evt.getTargetThing().getUID()))) {
				unregister(updater);
			}
			break;
		case FLUSH:
			if (toUpdate.size() > 0) {
				for (Updater updater : SystemUtils.getAndClear(toUpdate)) {
					if (DEBUG) {
						System.err.println("Flushing: " + updater);
					}
					try {
						updater.update();
						toUpdate.remove(updater);
					}
					catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
			break;
		default:
			// do nothing
		}
	}
}
