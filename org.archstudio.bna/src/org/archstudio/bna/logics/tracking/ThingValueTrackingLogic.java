package org.archstudio.bna.logics.tracking;

import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

public class ThingValueTrackingLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	private final Map<IThingKey<?>, SetMultimap<Object, Object>> keyToValueToThingIDsMap = new MapMaker()
			.makeComputingMap(new Function<IThingKey<?>, SetMultimap<Object, Object>>() {
				@Override
				public SetMultimap<Object, Object> apply(IThingKey<?> input) {
					SetMultimap<Object, Object> m = HashMultimap.create();
					for (IThing t : getBNAModel().getThings()) {
						Object value = t.get(input);
						if (value != null) {
							m.put(value, t.getID());
						}
					}
					return m;
				}
			});

	public ThingValueTrackingLogic() {
	}

	@Override
	public <ET extends IThing, EK extends IThing.IThingKey<EV>, EV> void bnaModelChangedSync(
			BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			ET thing = evt.getTargetThing();
			Object thingID = thing.getID();
			for (IThingKey<?> k : thing.keySet()) {
				update(thingID, k, null, thing.get(k));
			}
			break;
		}
		case THING_CHANGED: {
			ET thing = evt.getTargetThing();
			ThingEvent<ET, EK, EV> te = evt.getThingEvent();
			update(thing.getID(), te.getPropertyName(), te.getOldPropertyValue(), te.getNewPropertyValue());
			break;
		}
		case THING_REMOVED: {
			ET thing = evt.getTargetThing();
			Object thingID = thing.getID();
			for (IThingKey<?> k : thing.keySet()) {
				update(thingID, k, thing.get(k), null);
			}
			break;
		}
		}
	}

	private <V> void update(Object thingID, IThingKey<V> forKey, Object oldValue, Object newValue) {
		synchronized (keyToValueToThingIDsMap) {
			if (keyToValueToThingIDsMap.containsKey(forKey)) {
				Multimap<Object, Object> valueToThingIDsMap = keyToValueToThingIDsMap.get(forKey);
				if (oldValue != null) {
					valueToThingIDsMap.get(oldValue).remove(thingID);
				}
				if (newValue != null) {
					valueToThingIDsMap.get(newValue).add(thingID);
				}
			}
		}
	}

	public <V> Iterable<Object> getThingIDs(IThingKey<V> withKey, V ofValue) {
		synchronized (keyToValueToThingIDsMap) {
			return SystemUtils.copyIterable(keyToValueToThingIDsMap.get(withKey).get(ofValue));
		}
	}

	public <V1, V2> Iterable<Object> getThingIDs(IThingKey<V1> withKey1, V1 ofValue1, IThingKey<V2> withKey2,
			V2 ofValue2) {
		synchronized (keyToValueToThingIDsMap) {
			return SystemUtils.copyIterable(Sets.intersection(keyToValueToThingIDsMap.get(withKey1).get(ofValue1),
					keyToValueToThingIDsMap.get(withKey2).get(ofValue2)));
		}
	}
}
