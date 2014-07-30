package org.archstudio.bna.utils;

import java.util.Collection;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.sysutils.Finally;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;

public class DefaultBNAModel implements IBNAModel, IThingListener {

	private final ThingTree thingTree = new ThingTree();
	private final List<IBNAModelListener> listeners = Lists.newCopyOnWriteArrayList();
	private int bulkChangeCount = 0;
	private boolean firedBulkChange = false;

	public DefaultBNAModel() {
	}

	private void _fire(BNAModelEvent event) {
		for (IBNAModelListener l : listeners) {
			try {
				l.bnaModelChanged(event);
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private void fire(BNAModelEvent event) {
		if (bulkChangeCount > 0 && !firedBulkChange) {
			firedBulkChange = true;
			_fire(BNAModelEvent.create(DefaultBNAModel.this, EventType.BULK_CHANGE_BEGIN));
		}
		_fire(event);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void addBNAModelListener(IBNAModelListener l) {
		BNAUtils.checkLock();

		listeners.add(l);
	}

	@Override
	public void removeBNAModelListener(IBNAModelListener l) {
		BNAUtils.checkLock();

		listeners.remove(l);
	}

	@Override
	public void fireStreamNotificationEvent(Object streamNotificationEvent) {
		BNAUtils.checkLock();

		fire(BNAModelEvent.create(this, EventType.STREAM_NOTIFICATION_EVENT, streamNotificationEvent));
	}

	private final Finally END_BULK_CHANGE = new Finally() {

		@Override
		public void close() {
			BNAUtils.checkLock();

			if (--bulkChangeCount == 0) {
				if (firedBulkChange) {
					firedBulkChange = false;
					_fire(BNAModelEvent.create(DefaultBNAModel.this, EventType.BULK_CHANGE_END));
				}
			}
		}
	};

	@Override
	public Finally beginBulkChange() {
		BNAUtils.checkLock();

		bulkChangeCount++;

		return END_BULK_CHANGE;
	}

	@Override
	public void thingChanged(ThingEvent thingEvent) {
		BNAUtils.checkLock();

		fire(BNAModelEvent.create(this, EventType.THING_CHANGED, thingEvent));
	}

	@Override
	public <T extends IThing> T addThing(T thing) {
		return addThing(thing, null);
	}

	@Override
	public <T extends IThing> T addThing(T thing, @Nullable IThing parentThing) {
		BNAUtils.checkLock();

		thingTree.add(thing, parentThing);
		thing.addThingListener(this);
		fire(BNAModelEvent.create(this, EventType.THING_ADDED, thing));
		return thing;
	}

	@Override
	public <T extends IThing> T insertThing(T thing, IThing beforeThing) {
		BNAUtils.checkLock();

		thingTree.insert(thing, beforeThing);
		thing.addThingListener(this);
		fire(BNAModelEvent.create(this, EventType.THING_ADDED, thing));
		return thing;
	}

	@Override
	public void removeThing(IThing t) {
		BNAUtils.checkLock();

		t.removeThingListener(this);
		thingTree.remove(t);
		fire(BNAModelEvent.create(this, EventType.THING_REMOVED, t));
	}

	@Override
	public void removeThingAndChildren(IThing thing) {
		BNAUtils.checkLock();

		for (IThing childThing : getChildThings(thing)) {
			removeThingAndChildren(childThing);
		}
		removeThing(thing);
	}

	@Override
	public void bringToFront(IThing thing) {
		BNAUtils.checkLock();

		thingTree.bringToFront(thing);
		fire(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
	}

	@Override
	public void sendToBack(IThing thing) {
		BNAUtils.checkLock();

		thingTree.sendToBack(thing);
		fire(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
	}

	@Override
	public void reparent(IThing newParentThing, IThing thing) {
		BNAUtils.checkLock();

		thingTree.reparent(newParentThing, thing);
		fire(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
	}

	@Override
	public @Nullable
	IThing getThing(@Nullable Object id) {
		BNAUtils.checkLock();

		return thingTree.getThing(id);
	}

	@Override
	public List<IThing> getThingsByID(Collection<Object> thingIDs) {
		BNAUtils.checkLock();

		List<IThing> things = Lists.newArrayListWithExpectedSize(thingIDs.size());
		for (Object id : thingIDs) {
			IThing thing = getThing(id);
			if (thing != null) {
				things.add(thing);
			}
		}
		return things;
	}

	@Override
	public List<IThing> getAllThings() {
		BNAUtils.checkLock();

		return thingTree.getAllThings();
	}

	@Override
	public int getNumThings() {
		BNAUtils.checkLock();

		return thingTree.size();
	}

	@Override
	public IThing getParentThing(IThing thing) {
		BNAUtils.checkLock();

		return thingTree.getParent(thing);
	}

	@Override
	public List<IThing> getChildThings(IThing thing) {
		BNAUtils.checkLock();

		return thingTree.getChildThings(thing);
	}

	@Override
	public List<IThing> getAncestorThings(IThing thing) {
		BNAUtils.checkLock();

		return thingTree.getAncestorThings(thing);
	}

	@Override
	public List<IThing> getDescendantThings(IThing thing) {
		BNAUtils.checkLock();

		return thingTree.getAllDescendantThings(thing);
	}

}
