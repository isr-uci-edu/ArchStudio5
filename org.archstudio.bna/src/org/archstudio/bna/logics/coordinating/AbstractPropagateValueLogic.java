package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.IThingKeyKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.IThingRefKeyKey;
import org.archstudio.bna.keys.ThingKeyKey;
import org.archstudio.bna.keys.ThingRefKeyKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public abstract class AbstractPropagateValueLogic<FROM_THING extends IThing, TO_THING extends IThing, TO_KEY extends IThingKey<?>>
		extends AbstractThingLogic implements IBNASynchronousModelListener {

	/**
	 * A mapping of toThing keys that affect the propagation outcome for the
	 * toKey. Whenever any of the included keys are changed, a propagation will
	 * be forced. Note, this needs to contain the toKey itself to ensure that
	 * the toKey value remains valid.
	 */
	private final Map<IThingKey<?>, TO_KEY> toThingSettingsKeys = Maps.newHashMap();

	/**
	 * The set of keys that obtain references to the fromThing, which contains
	 * the values being propagated. Whenever the referenced fromThing is
	 * changed, a propagation will be forced.
	 */
	private final Set<IThingRefKeyKey<FROM_THING, ?, TO_KEY>> fromThingRefKeys = Sets.newHashSet();

	protected ThingValueTrackingLogic valuesLogic = null;
	protected final Class<FROM_THING> fromThingClass;
	protected final Class<TO_THING> toThingClass;

	public AbstractPropagateValueLogic(Class<FROM_THING> fromThingClass, Class<TO_THING> toThingClass) {
		super();
		this.fromThingClass = checkNotNull(fromThingClass);
		this.toThingClass = checkNotNull(toThingClass);
	}

	@Override
	protected void init() {
		super.init();
		this.valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	protected <V> IThingKey<V> getSettingKey(TO_KEY toKey, IThingKey<V> settingKey) {
		checkNotNull(toKey);
		checkNotNull(settingKey);

		IThingKeyKey<?, TO_KEY, V> key = ThingKeyKey.create(settingKey, toKey);
		toThingSettingsKeys.put(key, key.getKey());
		toThingSettingsKeys.put(toKey, toKey);
		return key;
	}

	protected <T extends IThing> IThingRefKey<T> getSettingKey(TO_KEY toKey, IThingRefKey<T> settingKey) {
		checkNotNull(toKey);
		checkNotNull(settingKey);

		IThingRefKeyKey<T, ?, TO_KEY> key = ThingRefKeyKey.create(settingKey, toKey);
		toThingSettingsKeys.put(key, key.getKey());
		toThingSettingsKeys.put(toKey, toKey);
		return key;
	}

	protected void addSettingKey(TO_KEY toKey, IThingKey<?> settingKey) {
		toThingSettingsKeys.put(settingKey, toKey);
		toThingSettingsKeys.put(toKey, toKey);
	}

	protected <V> V getSetting(TO_THING toThing, TO_KEY toKey, IThingKey<V> settingKey) {
		checkNotNull(toThing);
		checkNotNull(toKey);
		checkNotNull(settingKey);

		return toThing.get(getSettingKey(toKey, settingKey));
	}

	protected <V> void setSetting(TO_THING toThing, TO_KEY toKey, IThingKey<V> settingKey, V settingValue) {
		checkNotNull(toThing);
		checkNotNull(toKey);
		checkNotNull(settingKey);

		toThing.set(getSettingKey(toKey, settingKey), settingValue);
	}

	public IThingRefKey<FROM_THING> getThingRefKey(TO_KEY toKey) {
		checkNotNull(toKey);

		IThingRefKeyKey<FROM_THING, ?, TO_KEY> key = ThingRefKeyKey.create("&propagateFromThingID", toKey);
		toThingSettingsKeys.put(key, key.getKey());
		fromThingRefKeys.add(key);
		return key;
	}

	public IThingRefKey<IHasWorld> getWorldThingRefKey(TO_KEY toKey) {
		checkNotNull(toKey);

		IThingRefKeyKey<IHasWorld, ?, TO_KEY> key = ThingRefKeyKey.create("&propagateFromWorldThingID", toKey);
		toThingSettingsKeys.put(key, key.getKey());
		toThingSettingsKeys.put(toKey, toKey);
		return key;
	}

	public Object getThingRef(TO_THING toThing, TO_KEY toKey) {
		checkNotNull(toThing);
		checkNotNull(toKey);

		return toThing.get(getThingRefKey(toKey));
	}

	public <V> void setThingRef(TO_THING toThing, TO_KEY toKey, @Nullable Object thingID) {
		checkNotNull(toThing);
		checkNotNull(toKey);

		toThing.set(getThingRefKey(toKey), thingID);
	}

	protected void unpropagate(final TO_THING toThing, final TO_KEY toKey) {
		checkNotNull(toThing);
		checkNotNull(toKey);

		toThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				for (Entry<IThingKey<?>, TO_KEY> entry : toThingSettingsKeys.entrySet()) {
					if (entry.getKey() instanceof IThingKeyKey<?, ?, ?>) {
						toThing.remove(entry.getKey());
					}
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_CHANGED: {

			// if a fromThing is modified, perform all propagations from it
			if (fromThingClass.isInstance(evt.getTargetThing())) {
				fromThingChangedSync((BNAModelEvent<FROM_THING, EK, EV>) evt);
			}

			// if a propagated value or its related data is externally modified, re-propagate the value
			if (toThingClass.isInstance(evt.getTargetThing())) {
				toThingChangedSync((BNAModelEvent<TO_THING, EK, EV>) evt);
			}

		}
		}
	}

	protected <EK extends IThingKey<EV>, EV> void fromThingChangedSync(BNAModelEvent<FROM_THING, EK, EV> evt) {
		// note: we use the event source in order to propagate changes from an inner world to outer worlds
		FROM_THING fromThing = evt.getTargetThing();
		for (IThingRefKeyKey<FROM_THING, ?, TO_KEY> thingRefKey : Lists.newArrayList(fromThingRefKeys)) {
			for (TO_THING toThing : Iterables.filter(
			// note: we propagate changes to things in the logic's BNA model to propagate 
			// to propagate from the inner world to the outer world
					getBNAModel().getThings(valuesLogic.getThingIDs(thingRefKey, fromThing.getID())), toThingClass)) {
				doPropagationUnlessInCycle(evt.getSource(), fromThing, evt.getThingEvent(), toThing, null, thingRefKey.getKey());
			}
		}
	}

	protected <EK extends IThingKey<EV>, EV> void toThingChangedSync(BNAModelEvent<TO_THING, EK, EV> evt) {
		TO_KEY toKey = toThingSettingsKeys.get(evt.getThingEvent().getPropertyName());
		if (toKey != null) {
			ThingEvent<TO_THING, EK, EV> toThingEvent = evt.getThingEvent();
			TO_THING toThing = toThingEvent.getTargetThing();
			IThingRefKey<FROM_THING> fromThingRefKey = getThingRefKey(toKey);
			IBNAModel fromModel = evt.getSource();
			IThingRefKey<IHasWorld> worldThingRefKey = getWorldThingRefKey(toKey);
			Object worldThingID = toThing.get(worldThingRefKey);
			if(worldThingID != null){
				IHasWorld worldThing = castOrNull(evt.getSource().getThing(worldThingID), IHasWorld.class);
				if(worldThing != null){
					IBNAWorld world = worldThing.getWorld();
					if(world != null){
						fromModel = world.getBNAModel();
					}
				}
			}
			FROM_THING fromThing = castOrNull(fromThingRefKey.get(toThing, fromModel), fromThingClass);
			if (fromThing != null) {
				doPropagationUnlessInCycle(fromModel, fromThing, null, toThing, toThingEvent, toKey);
			}
		}
	}

	private final Set<Object> updatingThingIDs = Sets.newHashSet();

	private void doPropagationUnlessInCycle(final IBNAModel fromModel, final FROM_THING fromThing,
			@Nullable final ThingEvent<FROM_THING, ?, ?> fromThingEvent, final TO_THING toThing,
			@Nullable final ThingEvent<TO_THING, ?, ?> toThingEvent, final TO_KEY toKey) {

		if (!updatingThingIDs.contains(toThing.getID())) {
			updatingThingIDs.add(toThing.getID());
			try {
				toThing.synchronizedUpdate(new Runnable() {
					@Override
					public void run() {
						doSynchronizedPropagation(fromModel, fromThing, fromThingEvent, toThing, toThingEvent, toKey);
					}
				});
			}
			finally {
				updatingThingIDs.remove(toThing.getID());
			}
		}
	}

	abstract protected void doSynchronizedPropagation(IBNAModel fromModel, FROM_THING fromThing,
			ThingEvent<FROM_THING, ?, ?> fromThingEvent, TO_THING toThing, ThingEvent<TO_THING, ?, ?> toThingEvent,
			TO_KEY toKey);

}
