package org.archstudio.bna.logics.tracking;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;

public abstract class AbstractTrackingLogic<L, E> extends AbstractThingLogic implements IBNASynchronousModelListener {
	protected Map<String, E> idToTrackedMap = Collections.synchronizedMap(new HashMap<String, E>());
	protected boolean initialized = false;

	protected Class<?> thingInterfaceClass = null;

	public AbstractTrackingLogic(Class<?> thingInterfaceClass) {
		super();
		this.thingInterfaceClass = thingInterfaceClass;
	}

	protected List<L> trackingListeners = new CopyOnWriteArrayList<L>();

	public void addTrackingListener(L l) {
		trackingListeners.add(l);
	}

	public void removeTrackingListener(L l) {
		trackingListeners.remove(l);
	}

	protected abstract void fireTrackedChangedEvent(IThing targetThing, E oldTracked, E newTracked);

	protected abstract E getTrackedElement(IThing targetThing);

	protected void init(IBNAModel m) {
		for (IThing t : m) {
			if (t.getClass().isInstance(thingInterfaceClass)) {
				idToTrackedMap.put(t.getID(), getTrackedElement(t));
			}
		}
		initialized = true;
	}

	public void init() {
		init(getBNAModel());
	}

	protected boolean isTracking(IThing t) {
		return thingInterfaceClass.isInstance(t);
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		if (!initialized) {
			init(evt.getSource());
		}
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing addedThing = evt.getTargetThing();
			if (isTracking(addedThing)) {
				E oldTracked = idToTrackedMap.get(addedThing.getID());
				E newTracked = getTrackedElement(addedThing);
				idToTrackedMap.put(addedThing.getID(), newTracked);
				fireTrackedChangedEvent(addedThing, oldTracked, newTracked);
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_REMOVED) {
			if (idToTrackedMap.get(evt.getTargetThing().getID()) != null) {
				E oldTracked = idToTrackedMap.get(evt.getTargetThing().getID());
				idToTrackedMap.remove(evt.getTargetThing().getID());
				fireTrackedChangedEvent(evt.getTargetThing(), oldTracked, null);
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing changedThing = evt.getTargetThing();
			if (isTracking(changedThing)) {
				E oldTracked = idToTrackedMap.get(changedThing.getID());
				E newTracked = getTrackedElement(changedThing);

				if (!BNAUtils.nulleq(oldTracked, newTracked)) {
					idToTrackedMap.put(changedThing.getID(), newTracked);
					fireTrackedChangedEvent(evt.getTargetThing(), oldTracked, newTracked);
				}
			}
		}
	}

}
