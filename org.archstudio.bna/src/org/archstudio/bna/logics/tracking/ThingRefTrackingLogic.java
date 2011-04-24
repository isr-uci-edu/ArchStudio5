package org.archstudio.bna.logics.tracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;

public class ThingRefTrackingLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	protected boolean maintainReferentialIntegrity = true;

	//Maps thing IDs (ref targets) to HashSets of ThingRefData
	protected Map<String, Set<ThingRefData>> indexMap = Collections.synchronizedMap(new HashMap<String, Set<ThingRefData>>());

	public ThingRefTrackingLogic() {
		this(true);
	}

	public ThingRefTrackingLogic(boolean maintainReferentialIntegrity) {
		super();
		this.maintainReferentialIntegrity = maintainReferentialIntegrity;
	}

	public void init() {
		init(getBNAModel());
	}

	protected void init(IBNAModel m) {
		synchronized (indexMap) {
			indexMap.clear();
			for (IThing t : m) {
				addReffingThing(t, false);
			}
		}
	}

	private void addReffingThing(IThing t, boolean fireEvents) {
		for (String propertyName : t.getAllPropertyNames()) {
			if (propertyName.startsWith("&")) {
				//It's a ref property
				String reffedThingID = (String) t.getProperty(propertyName);
				if (reffedThingID != null) {
					ThingRefData refData = new ThingRefData(t.getID(), propertyName);
					Set<ThingRefData> s = indexMap.get(reffedThingID);
					if (s == null) {
						s = new HashSet<ThingRefData>();
						indexMap.put(reffedThingID, s);
					}
					s.add(refData);
					if (fireEvents) {
						fireThingRefChangedEvent(t, propertyName, null, reffedThingID);
					}
				}
			}
		}
	}

	private void removeReffingThing(IThing removedThing) {
		for (String propertyName : removedThing.getAllPropertyNames()) {
			if (propertyName.startsWith("&")) {
				//Remove the entry from the index for this property
				String reffedThingID = (String) removedThing.getProperty(propertyName);
				if (reffedThingID != null) {
					Set<ThingRefData> s = indexMap.get(reffedThingID);
					if (s != null) {
						ThingRefData[] trds = s.toArray(new ThingRefData[s.size()]);
						for (int j = 0; j < trds.length; j++) {
							if (trds[j].getThingID().equals(removedThing.getID())) {
								s.remove(trds[j]);
								break;
							}
						}
					}
				}
			}
		}
	}

	public void destroy() {
		indexMap.clear();
	}

	protected List<IThingRefTrackingListener> thingRefTrackingListeners = new CopyOnWriteArrayList<IThingRefTrackingListener>();

	public void addThingRefTrackingListener(IThingRefTrackingListener l) {
		thingRefTrackingListeners.add(l);
	}

	public void removeThingRefTrackingListener(IThingRefTrackingListener l) {
		thingRefTrackingListeners.remove(l);
	}

	protected void fireThingRefChangedEvent(IThing targetThing, String propertyName, String oldValue, String newValue) {
		ThingRefChangedEvent evt = new ThingRefChangedEvent(this, targetThing, propertyName, oldValue, newValue);
		for (IThingRefTrackingListener l : thingRefTrackingListeners) {
			l.refChanged(evt);
		}
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing addedThing = evt.getTargetThing();
			addReffingThing(addedThing, true);
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_REMOVED) {
			IThing removedThing = evt.getTargetThing();
			//Two possibilities: we're removing a thing that references other
			//things, or we're removing a thing that is referenced by other things.
			//Possibility 1 first: we're removing a thing that refs other things
			removeReffingThing(removedThing);

			//OK, now we have removed all the refs from the index.  Let's see if
			//anything is reffing the Thing that's being removed.
			Set<ThingRefData> s = indexMap.get(removedThing.getID());
			if (s != null) {
				//OK, some things ref this thing.
				if (maintainReferentialIntegrity) {
					//Clear the properties pointing to the removed thing so 
					//we don't have dangling refs
					ThingRefData[] trds = s.toArray(new ThingRefData[s.size()]);
					for (int j = 0; j < trds.length; j++) {
						String reffingThingID = trds[j].getThingID();
						IThing reffingThing = evt.getSource().getThing(reffingThingID);
						if (reffingThing != null) {
							String propertyName = trds[j].getPropertyName();
							reffingThing.removeProperty(propertyName);
						}
					}
				}
				//Now remove the thing from the index as a reffed thing.
				indexMap.remove(removedThing.getID());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing changedThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();

			String propertyName = tevt.getPropertyName();
			if (propertyName.startsWith("&")) {
				String oldReffedThingID = (String) tevt.getOldPropertyValue();
				String newReffedThingID = (String) tevt.getNewPropertyValue();
				if (!BNAUtils.nulleq(oldReffedThingID, newReffedThingID)) {
					if (oldReffedThingID != null) {
						//We have to remove the old mapping.
						Set<ThingRefData> oldSet = indexMap.get(oldReffedThingID);
						if (oldSet != null) {
							ThingRefData[] trds = oldSet.toArray(new ThingRefData[oldSet.size()]);
							for (int j = 0; j < trds.length; j++) {
								if (trds[j].getThingID().equals(changedThing.getID())) {
									if (trds[j].getPropertyName().equals(propertyName)) {
										//It's a match
										oldSet.remove(trds[j]);
										break;
									}
								}
							}
						}
					}
					if (newReffedThingID != null) {
						//We have to add the new mapping
						Set<ThingRefData> newSet = indexMap.get(newReffedThingID);
						if (newSet == null) {
							newSet = new HashSet<ThingRefData>();
							indexMap.put(newReffedThingID, newSet);
						}
						ThingRefData trd = new ThingRefData(changedThing.getID(), propertyName);
						newSet.add(trd);
					}
					fireThingRefChangedEvent(changedThing, propertyName, oldReffedThingID, newReffedThingID);
				}
			}
		}
	}

	public static final ThingRefData[] EMPTY_THING_REF_DATA_ARRAY = new ThingRefData[0];

	public ThingRefData[] findReferencingThings(IThing t) {
		Set<ThingRefData> s = indexMap.get(t.getID());
		if (s == null) {
			return EMPTY_THING_REF_DATA_ARRAY;
		}
		return s.toArray(new ThingRefData[s.size()]);
	}

	public String[] findReferencingThings(IThing t, String propertyName) {
		List<String> l = new ArrayList<String>();
		ThingRefData[] trds = findReferencingThings(t);
		for (int i = 0; i < trds.length; i++) {
			if (trds[i].getPropertyName().equals(propertyName)) {
				l.add(trds[i].getThingID());
			}
		}
		return l.toArray(new String[l.size()]);
	}

}
