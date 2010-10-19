package org.archstudio.bna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.BNAModelEvent.EventType;

public class DefaultBNAModel implements IBNAModel, IThingListener {

	protected Object thingTreeLock = new Object();
	protected ThingTree thingTree;

	private List<IBNASynchronousModelListener> syncModelListeners = new CopyOnWriteArrayList<IBNASynchronousModelListener>();

	protected ThreadModelEventProcessor threadModelEventProcessor;

	protected ThingIndex thingIndex = null;

	public DefaultBNAModel() {
		thingTree = new ThingTree();
		threadModelEventProcessor = new ThreadModelEventProcessor();
		thingIndex = new ThingIndex(this);
	}

	public Object getLock() {
		return thingTreeLock;
	}

	public void addBNAModelListener(IBNAModelListener l) {
		threadModelEventProcessor.addModelListener(l);
	}

	public void removeBNAModelListener(IBNAModelListener l) {
		threadModelEventProcessor.removeModelListener(l);
	}

	public void addSynchronousBNAModelListener(IBNASynchronousModelListener l) {
		syncModelListeners.add(l);
	}

	public void removeSynchronousBNAModelListener(IBNASynchronousModelListener l) {
		syncModelListeners.remove(l);
	}

	protected void fireBNAModelEvent(BNAModelEvent.EventType eventType, IThing targetThing) {
		BNAModelEvent evt = new BNAModelEvent(this, eventType, targetThing);
		fireSyncBNAModelEvent(evt);
		threadModelEventProcessor.fireBNAModelEvent(evt);
	}

	protected void fireBNAModelEvent(BNAModelEvent.EventType eventType, IThing targetThing, ThingEvent thingEvent) {
		BNAModelEvent evt = new BNAModelEvent(this, eventType, targetThing, thingEvent);
		fireSyncBNAModelEvent(evt);
		threadModelEventProcessor.fireBNAModelEvent(evt);
	}

	public void fireStreamNotificationEvent(String streamNotificationEvent) {
		BNAModelEvent evt = new BNAModelEvent(this, streamNotificationEvent);
		fireSyncBNAModelEvent(evt);
		threadModelEventProcessor.fireBNAModelEvent(evt);
	}

	protected void fireSyncBNAModelEvent(BNAModelEvent evt) {
		for (IBNASynchronousModelListener l : syncModelListeners) {
			l.bnaModelChangedSync(evt);
		}
	}

	public void beginBulkChange() {
		fireBNAModelEvent(BNAModelEvent.EventType.BULK_CHANGE_BEGIN, null);
	}

	public void endBulkChange() {
		fireBNAModelEvent(BNAModelEvent.EventType.BULK_CHANGE_END, null);
	}

	/**
	 * Blocks the calling thread until all pending ThingEvents have been
	 * processed. This is rarely needed, but useful if a caller needs to wait
	 * for the various logics to have processed a ThingEvent before making
	 * further changes.
	 */
	public void waitForProcessing() {
		threadModelEventProcessor.waitForProcessing();
	}

	public void addThing(IThing t) {
		synchronized (thingTreeLock) {
			t.addThingListener(this);
			thingTree.add(t);
			if (t instanceof IModelAwareThing) {
				((IModelAwareThing) t).adding();
			}
			fireBNAModelEvent(BNAModelEvent.EventType.THING_ADDED, t);
		}
	}

	public void addThing(IThing t, IThing parentThing) {
		if (parentThing == null) {
			addThing(t);
			return;
		}
		synchronized (thingTreeLock) {
			t.addThingListener(this);
			thingTree.add(t, parentThing);
			if (t instanceof IModelAwareThing) {
				((IModelAwareThing) t).adding();
			}
			fireBNAModelEvent(BNAModelEvent.EventType.THING_ADDED, t);
		}
	}

	public void removeThing(IThing t) {
		synchronized (thingTreeLock) {
			if (thingTree.contains(t)) {
				fireBNAModelEvent(BNAModelEvent.EventType.THING_REMOVING, t);
				t.removeThingListener(this);
				thingTree.remove(t);
				fireBNAModelEvent(BNAModelEvent.EventType.THING_REMOVED, t);
			}
		}
	}

	public void removeThingAndChildren(IThing t) {
		synchronized (thingTreeLock) {
			if (thingTree.contains(t)) {
				List<IThing> doomedThingList = new ArrayList<IThing>();
				doomedThingList.add(t);
				for (int i = 0; i < doomedThingList.size(); i++) {
					IThing ttr = (IThing) doomedThingList.get(i);
					for (IThing childThing : getChildThings(ttr)) {
						doomedThingList.add(childThing);
					}
				}
				for (int i = doomedThingList.size() - 1; i >= 0; i--) {
					IThing ttr = (IThing) doomedThingList.get(i);
					fireBNAModelEvent(BNAModelEvent.EventType.THING_REMOVING, ttr);
					ttr.removeThingListener(this);
					thingTree.removeWithChildren(ttr);
					fireBNAModelEvent(BNAModelEvent.EventType.THING_REMOVED, ttr);
				}
				//updateListCache();
			}
		}
	}

	public IThing getThing(String id) {
		return thingIndex.getThing(id);
	}

	public void removeThing(String id) {
		IThing t = getThing(id);
		if (t != null) {
			synchronized (thingTreeLock) {
				fireBNAModelEvent(BNAModelEvent.EventType.THING_REMOVING, t);
				t.removeThingListener(this);
				thingTree.remove(t);
				fireBNAModelEvent(BNAModelEvent.EventType.THING_REMOVED, t);
				//updateListCache();
			}
		}
	}
	
	@Override
	public Iterator<IThing> iterator() {
		return getThingIterator();
	}

	public Iterator<IThing> getThingIterator() {
		synchronized (thingTreeLock) {
			return thingTree.iterator();
		}
	}

	public ListIterator<IThing> getThingListIterator(int index) {
		synchronized (thingTreeLock) {
			return thingTree.listIterator(index);
		}
	}

	public int getNumThings() {
		synchronized (thingTreeLock) {
			return thingTree.size();
		}
	}

	public List<IThing> getAllThings() {
		synchronized (thingTreeLock) {
			return thingTree.asList();
		}
	}

	public List<IThing> getChildThings(IThing t) {
		synchronized (thingTreeLock) {
			List<IThing> l = thingTree.getChildren(t);
			if (l == null) {
				return null;
			}
			else {
				return Collections.unmodifiableList(l);
			}
		}
	}

	public IThing getParentThing(IThing t) {
		synchronized (thingTreeLock) {
			return thingTree.getParent(t);
		}
	}

	public List<IThing> getAncestorThings(IThing t) {
		synchronized (thingTreeLock) {
			List<IThing> ancestorList = thingTree.getAncestors(t);
			return Collections.unmodifiableList(ancestorList);
		}
	}

	public void thingChanged(ThingEvent evt) {
		fireBNAModelEvent(BNAModelEvent.EventType.THING_CHANGED, evt.getTargetThing(), evt);
	}

	public void stackAbove(IThing upperThing, IThing lowerThing) {
		synchronized (thingTreeLock) {
			thingTree.moveAfter(lowerThing, upperThing);
		}
	}

	public void bringToFront(IThing thing) {
		synchronized (thingTreeLock) {
			thingTree.bringToFront(thing);
		}
	}

	public void sendToBack(IThing thing) {
		synchronized (thingTreeLock) {
			thingTree.sendToBack(thing);
		}
	}

	public void dumpThingTree(IThing thing) {
		synchronized (thingTreeLock) {
			thingTree.dumpThingTree(thing);
		}
	}

	static class ThingIndex implements IBNASynchronousModelListener {
		protected Map<String, IThing> indexMap = new WeakHashMap<String, IThing>();

		public ThingIndex(IBNAModel m) {
			m.addSynchronousBNAModelListener(this);
		}

		public void bnaModelChangedSync(BNAModelEvent evt) {
			if (evt.getEventType().equals(EventType.THING_ADDED)) {
				indexMap.put(evt.targetThing.getID(), evt.targetThing);
			}
			else if (evt.getEventType().equals(EventType.THING_REMOVED)) {
				indexMap.remove(evt.targetThing.getID());
			}
		}

		public IThing getThing(String id) {
			return indexMap.get(id);
		}
	}
}
