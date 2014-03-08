package org.archstudio.bna.utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

public class DefaultBNAModel implements IBNAModel, IThingListener {

	private ThingTree thingTree = new ThingTree();
	private List<IBNAModelListener> listeners = Lists.newCopyOnWriteArrayList();

	private Deque<BNAModelEvent> eventQueue = new LinkedList<>();
	private Thread eventThread;
	{
		eventThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					BNAModelEvent event;
					synchronized (DefaultBNAModel.this) {
						while (eventQueue.isEmpty()) {
							try {
								DefaultBNAModel.this.wait();
							}
							catch (Exception e) {
							}
						}
						event = eventQueue.remove();
					}
					if (event == null) {
						return;
					}
					for (IBNAModelListener l : listeners) {
						try {
							l.bnaModelChanged(event);
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
			}
		});
		eventThread.setDaemon(true);
		eventThread.setName(DefaultBNAModel.class.getName());
		eventThread.start();
	}

	public DefaultBNAModel() {
	}

	@Override
	synchronized public void dispose() {
		eventQueue.clear();
		enqueueEvent(null);
	}

	private void enqueueEvent(BNAModelEvent event) {
		eventQueue.add(event);
		notifyAll();
	}

	@Override
	public void addBNAModelListener(IBNAModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeBNAModelListener(IBNAModelListener l) {
		listeners.remove(l);
	}

	@Override
	synchronized public void fireStreamNotificationEvent(Object streamNotificationEvent) {
		enqueueEvent(BNAModelEvent.create(this, EventType.STREAM_NOTIFICATION_EVENT, streamNotificationEvent));
	}

	@Override
	synchronized public void beginBulkChange() {
		enqueueEvent(BNAModelEvent.create(this, EventType.BULK_CHANGE_BEGIN));
	}

	@Override
	synchronized public void endBulkChange() {
		enqueueEvent(BNAModelEvent.create(this, EventType.BULK_CHANGE_END));
	}

	@Override
	public <T extends IThing> T addThing(T thing) {
		return addThing(thing, null);
	}

	@Override
	synchronized public void thingChanged(ThingEvent thingEvent) {
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_CHANGED, thingEvent));
	}

	@Override
	synchronized public <T extends IThing> T addThing(T thing, @Nullable IThing parentThing) {
		thingTree.add(thing, parentThing);
		thing.addThingListener(this);
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_ADDED, thing));
		return thing;
	}

	@Override
	synchronized public <T extends IThing> T insertThing(T thing, IThing beforeThing) {
		thingTree.insert(thing, beforeThing);
		thing.addThingListener(this);
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_ADDED, thing));
		return thing;
	}

	@Override
	synchronized public void removeThing(IThing t) {
		t.removeThingListener(this);
		thingTree.remove(t);
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_REMOVED, t));
	}

	@Override
	public void removeThingAndChildren(IThing thing) {
		for (IThing childThing : getChildThings(thing)) {
			removeThingAndChildren(childThing);
		}
		removeThing(thing);
	}

	@Override
	synchronized public @Nullable
	IThing getThing(@Nullable Object id) {
		return thingTree.getThing(id);
	}

	@Override
	synchronized public List<IThing> getThingsByID(Iterable<Object> thingIDs) {
		List<IThing> things = Lists.newArrayList();
		for (Object id : thingIDs) {
			IThing thing = getThing(id);
			if (thing != null) {
				things.add(thing);
			}
		}
		return things;
	}

	@Override
	synchronized public List<IThing> getAllThings() {
		return thingTree.getAllThings();
	}

	@Override
	synchronized public int getNumThings() {
		return thingTree.size();
	}

	@Override
	synchronized public IThing getParentThing(IThing thing) {
		return thingTree.getParent(thing);
	}

	@Override
	synchronized public List<IThing> getChildThings(IThing thing) {
		return thingTree.getChildThings(thing);
	}

	@Override
	synchronized public List<IThing> getAncestorThings(IThing thing) {
		return thingTree.getAncestorThings(thing);
	}

	@Override
	synchronized public List<IThing> getDescendantThings(IThing thing) {
		return thingTree.getAllDescendantThings(thing);
	}

	@Override
	synchronized public void bringToFront(IThing thing) {
		thingTree.bringToFront(thing);
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
	}

	@Override
	synchronized public void sendToBack(IThing thing) {
		thingTree.sendToBack(thing);
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
	}

	@Override
	synchronized public void reparent(IThing newParentThing, IThing thing) {
		thingTree.reparent(newParentThing, thing);
		enqueueEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
	}

}
