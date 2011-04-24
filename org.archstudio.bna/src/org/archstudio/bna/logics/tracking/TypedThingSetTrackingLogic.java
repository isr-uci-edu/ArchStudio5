package org.archstudio.bna.logics.tracking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.BNAModelEvent.EventType;

@SuppressWarnings("unchecked")
public class TypedThingSetTrackingLogic<T extends IThing> extends AbstractThingLogic implements IBNASynchronousModelListener {

	protected Class<T> thingType;
	protected T[] thingArray = null;
	protected Set<IThing> thingSet = new HashSet<IThing>();

	public TypedThingSetTrackingLogic(Class<T> thingType) {
		this.thingType = thingType;
		thingArray = (T[]) java.lang.reflect.Array.newInstance(thingType, 0);
	}

	public void init() {
		IBNAModel m = getBNAModel();
		if (m == null) {
			thingSet.clear();
		}
		else {
			for (IThing t : m.getAllThings()) {
				if (thingType.isInstance(t)) {
					thingSet.add(t);
				}
			}
			thingArray = thingSet.toArray((T[]) java.lang.reflect.Array.newInstance(thingType, 0));
		}
	}

	public void destroy() {
		thingArray = (T[]) java.lang.reflect.Array.newInstance(thingType, 0);
		thingSet.clear();
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		EventType evtType = evt.getEventType();
		if (evtType.equals(EventType.THING_ADDED)) {
			IThing t = evt.getTargetThing();
			if (thingType.isInstance(t)) {
				thingSet.add(t);
				thingArray = thingSet.toArray((T[]) java.lang.reflect.Array.newInstance(thingType, 0));
				fireThingSetChangedEvent(t, evtType);
			}
		}
		else if (evtType.equals(EventType.THING_REMOVING)) {
			IThing t = evt.getTargetThing();
			if (thingType.isInstance(t)) {
				thingSet.remove(t);
				thingArray = thingSet.toArray((T[]) java.lang.reflect.Array.newInstance(thingType, 0));
				fireThingSetChangedEvent(t, evtType);
			}
		}
	}

	protected List<IThingSetListener> listeners = new CopyOnWriteArrayList<IThingSetListener>();

	public void addThingSetListener(IThingSetListener l) {
		listeners.add(l);
	}

	public void removeThingSetListener(IThingSetListener l) {
		listeners.remove(l);
	}

	protected void fireThingSetChangedEvent(IThing targetThing, EventType eventType) {
		ThingSetChangedEvent evt = new ThingSetChangedEvent(this, targetThing, eventType);
		for (IThingSetListener listener : listeners) {
			listener.thingSetChanged(evt);
		}
	}

	public T[] getThings() {
		return thingArray;
	}
}
