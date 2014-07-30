package org.archstudio.bna.logics.tracking;

import static org.archstudio.sysutils.SystemUtils.filter;

import java.util.Collection;
import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

public class ThingValueTrackingLogic extends AbstractThingLogic implements IBNAModelListener {

	private final LoadingCache<IThingKey<?>, SetMultimap<Object, Object>> keyToValueToThingIDsCache = CacheBuilder
			.newBuilder().build(new CacheLoader<IThingKey<?>, SetMultimap<Object, Object>>() {

				@Override
				public SetMultimap<Object, Object> load(IThingKey<?> input) {
					SetMultimap<Object, Object> m = HashMultimap.create();
					for (IThing t : model.getAllThings()) {
						Object value = t.get(input);
						if (value != null) {
							m.put(value, t.getID());
						}
					}
					return m;
				}
			});

	public ThingValueTrackingLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();

		keyToValueToThingIDsCache.invalidateAll();
		super.dispose();
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		BNAUtils.checkLock();

		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			Object thingID = thing.getID();
			for (Map.Entry<IThingKey<?>, ?> entry : thing.entrySet()) {
				update(thingID, entry.getKey(), null, entry.getValue());
			}
		}
			break;
		case THING_CHANGED: {
			IThing thing = evt.getTargetThing();
			ThingEvent te = evt.getThingEvent();
			update(thing.getID(), te.getPropertyName(), te.getOldPropertyValue(), te.getNewPropertyValue());
		}
			break;
		case THING_REMOVED: {
			IThing thing = evt.getTargetThing();
			Object thingID = thing.getID();
			for (Map.Entry<IThingKey<?>, ?> entry : thing.entrySet()) {
				update(thingID, entry.getKey(), entry.getValue(), null);
			}
		}
			break;
		default:
			// do nothing
		}
	}

	private <V> void update(Object thingID, IThingKey<V> forKey, Object oldValue, Object newValue) {
		Multimap<Object, Object> valueToThingIDsMap = keyToValueToThingIDsCache.getIfPresent(forKey);
		if (valueToThingIDsMap != null) {
			if (oldValue != null) {
				valueToThingIDsMap.get(oldValue).remove(thingID);
			}
			if (newValue != null) {
				valueToThingIDsMap.get(newValue).add(thingID);
			}
		}
	}

	public <V> Collection<Object> getThingIDs(IThingKey<V> withKey, V ofValue) {
		BNAUtils.checkLock();

		return Lists.newArrayList(keyToValueToThingIDsCache.getUnchecked(withKey).get(ofValue));
	}

	public <V> Collection<IThing> getThings(IThingKey<V> withKey, V ofValue) {
		BNAUtils.checkLock();

		return model.getThingsByID(keyToValueToThingIDsCache.getUnchecked(withKey).get(ofValue));
	}

	public <V, T extends IThing> Collection<T> getThings(IThingKey<V> withKey, V ofValue, Class<T> ofType) {
		BNAUtils.checkLock();

		return filter(model.getThingsByID(keyToValueToThingIDsCache.getUnchecked(withKey).get(ofValue)), ofType);
	}

	public <V1, V2> Collection<Object> getThingIDs(IThingKey<V1> withKey1, V1 ofValue1, IThingKey<V2> withKey2,
			V2 ofValue2) {
		BNAUtils.checkLock();

		return Lists.newArrayList(Sets.intersection(keyToValueToThingIDsCache.getUnchecked(withKey1).get(ofValue1),
				keyToValueToThingIDsCache.getUnchecked(withKey2).get(ofValue2)));
	}

	public <V1, V2> Collection<IThing> getThings(IThingKey<V1> withKey1, V1 ofValue1, IThingKey<V2> withKey2,
			V2 ofValue2) {
		BNAUtils.checkLock();

		return model.getThingsByID(Sets.intersection(keyToValueToThingIDsCache.getUnchecked(withKey1).get(ofValue1),
				keyToValueToThingIDsCache.getUnchecked(withKey2).get(ofValue2)));
	}

	public <V1, V2, T extends IThing> Collection<T> getThings(IThingKey<V1> withKey1, V1 ofValue1,
			IThingKey<V2> withKey2, V2 ofValue2, Class<T> ofType) {
		BNAUtils.checkLock();

		return filter(model.getThingsByID(Sets.intersection(
				keyToValueToThingIDsCache.getUnchecked(withKey1).get(ofValue1),
				keyToValueToThingIDsCache.getUnchecked(withKey2).get(ofValue2))), ofType);
	}

	public Collection<IThing> getThings(IThingKey<?> withKey) {
		BNAUtils.checkLock();

		return model.getThingsByID(keyToValueToThingIDsCache.getUnchecked(withKey).values());
	}
}
