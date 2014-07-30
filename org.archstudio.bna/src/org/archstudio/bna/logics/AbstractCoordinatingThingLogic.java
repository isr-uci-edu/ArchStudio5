package org.archstudio.bna.logics;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic.Updater;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.ThingKeyID;
import org.archstudio.sysutils.FastMap;

import com.google.common.collect.Lists;

public abstract class AbstractCoordinatingThingLogic<T extends Updater> extends AbstractThingLogic implements
		IBNAModelListener {

	public static abstract class Updater {

		List<Object> registeredAs = Lists.newArrayListWithCapacity(2);
		List<Object> trackingThings = Lists.newArrayListWithCapacity(2);
		List<Object> removeWithThings = Lists.newArrayListWithCapacity(2);

		public abstract void update(ThingEvent event);

	}

	private final FastMap<Object, List<T>> registeredUpdaters = new FastMap<>(false);
	private final FastMap<Object, List<T>> updatersTrackingThings = new FastMap<>(true);
	private final FastMap<Object, List<T>> updatersToRemoveWithThings = new FastMap<>(true);

	public AbstractCoordinatingThingLogic(IBNAWorld world) {
		super(world);
	}

	protected void register(T updater, IThing thing) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkArgument(updater.registeredAs.size() == 0, "Updater already registered");

		updater.registeredAs.add(thing);
		FastMap.createList(registeredUpdaters, thing.getID()).add(updater);

		updater.update(null);
	}

	protected void register(T updater, IThing thing, IThingKey<?> key) {
		checkNotNull(updater);
		checkNotNull(thing);
		checkNotNull(key);
		checkArgument(updater.registeredAs.size() == 0, "Updater already registered");

		ThingKeyID<?> thingKeyID = ThingKeyID.create(thing, key);
		updater.registeredAs.add(thingKeyID);
		FastMap.createList(registeredUpdaters, thingKeyID).add(updater);

		updater.update(null);
	}

	protected Collection<T> getRegisteredUpdater(IThing thing) {
		return FastMap.getCollection(registeredUpdaters, thing.getID());
	}

	protected Collection<T> getRegisteredUpdater(IThing thing, IThingKey<?> key) {
		return FastMap.getCollection(registeredUpdaters, ThingKeyID.create(thing, key));
	}

	protected void unregister(IThing thing) {
		checkNotNull(thing);

		for (T updater : Lists.newArrayList(FastMap.getCollection(registeredUpdaters, thing.getID()))) {
			unregister(updater);
		}
	}

	protected void unregister(IThing thing, IThingKey<?> key) {
		checkNotNull(thing);
		checkNotNull(key);

		for (T updater : Lists.newArrayList(FastMap.getCollection(registeredUpdaters, ThingKeyID.create(thing, key)))) {
			unregister(updater);
		}
	}

	protected void unregister(T updater) {
		if (updater != null) {
			for (Object register : updater.registeredAs) {
				FastMap.getCollection(registeredUpdaters, register).remove(updater);
			}
			updater.registeredAs.clear();
			for (Object thingID : updater.trackingThings) {
				FastMap.getCollection(updatersTrackingThings, thingID).remove(updater);
			}
			updater.trackingThings.clear();
			for (Object thingID : updater.removeWithThings) {
				FastMap.getCollection(updatersToRemoveWithThings, thingID).remove(updater);
			}
			updater.removeWithThings.clear();
		}
	}

	protected void removeWithThing(T updater, IThing... things) {
		checkNotNull(updater);
		checkNotNull(things);
		checkArgument(updater.registeredAs.size() > 0, "Updater not registered");

		for (IThing thing : things) {
			checkNotNull(thing);

			updater.removeWithThings.add(thing.getID());
			FastMap.createList(updatersToRemoveWithThings, thing.getID()).add(updater);
		}
	}

	protected void track(T updater, IThing... things) {
		checkNotNull(updater);
		checkNotNull(things);
		checkArgument(updater.registeredAs.size() > 0, "Updater not registered");

		for (IThing thing : things) {
			checkNotNull(thing);

			updater.trackingThings.add(thing.getID());
			FastMap.createList(updatersTrackingThings, thing.getID()).add(updater);
		}
	}

	protected void untrack(T updater, IThing... things) {
		checkNotNull(updater);
		checkNotNull(things);
		checkArgument(updater.registeredAs.size() > 0, "Updater not registered");

		for (IThing thing : things) {
			checkNotNull(thing);

			updater.trackingThings.remove(thing.getID());
			FastMap.getCollection(updatersTrackingThings, thing.getID()).remove(updater);
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		BNAUtils.checkLock();

		switch (evt.getEventType()) {
		case THING_CHANGED:
			for (T updater : FastMap.getCollection(updatersTrackingThings, evt.getTargetThing().getID())) {
				updater.update(evt.getThingEvent());
			}
			break;
		case THING_REMOVED:
			unregister(evt.getTargetThing());
			for (T updater : FastMap.removeCollection(updatersToRemoveWithThings, evt.getTargetThing().getID())) {
				unregister(updater);
			}
			break;
		default:
			// do nothing
		}
	}
}
