package org.archstudio.bna.utils;

import static org.archstudio.sysutils.SystemUtils.newCopyOnWriteArrayList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;

public class DefaultBNAModel implements IBNAModel, IThingListener {

	protected static final boolean DEBUG = false;
	protected final Map<Object, AtomicLong> listenerStats = !DEBUG ? null : new MapMaker().weakKeys().makeComputingMap(
			new Function<Object, AtomicLong>() {
				@Override
				public AtomicLong apply(Object input) {
					return new AtomicLong();
				}
			});

	private static final class ThingIndex implements IBNASynchronousModelListener {

		private final Map<Object, IThing> indexMap = new MapMaker().weakValues().makeMap();

		@Override
		public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
			switch (evt.getEventType()) {
			case THING_ADDED:
				IThing targetThing = evt.getTargetThing();
				synchronized (indexMap) {
					indexMap.put(targetThing.getID(), targetThing);
				}
				break;
			case THING_REMOVED:
				synchronized (indexMap) {
					indexMap.remove(evt.getTargetThing().getID());
				}
				break;
			}
		}

		public IThing getThing(Object id) {
			if (id != null) {
				synchronized (indexMap) {
					return indexMap.get(id);
				}
			}
			return null;
		}
	}

	protected final ThingTree thingTree = new ThingTree();
	protected int thingTreeModCount = 0;
	protected int thingTreeListAtModCount = thingTreeModCount - 1;
	protected List<IThing> thingTreeList = Lists.newArrayList();
	protected final ThingIndex thingIndex = new ThingIndex();
	protected final CopyOnWriteArrayList<IBNASynchronousModelListener> synchListeners = newCopyOnWriteArrayList();
	protected final CopyOnWriteArrayList<IBNAModelListener> asyncListeners = newCopyOnWriteArrayList();
	protected final ExecutorService asyncExecutor;
	protected AtomicInteger bulkChangeCount = new AtomicInteger();
	protected boolean firedBulkChangeEvent = false;

	public DefaultBNAModel() {
		synchListeners.add(thingIndex);
		asyncExecutor = Executors.newSingleThreadExecutor();
	}

	public void terminate(boolean t) {
		try {
			asyncExecutor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException ie) {
		}
	}

	@Override
	public void addSynchronousBNAModelListener(IBNASynchronousModelListener l) {
		synchListeners.add(l);
	}

	@Override
	public void removeSynchronousBNAModelListener(IBNASynchronousModelListener l) {
		synchListeners.remove(l);
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void fireBnaModelChangedSync(
			BNAModelEvent<ET, EK, EV> evt) {
		for (IBNASynchronousModelListener l : synchListeners) {
			try {
				long lTime;
				if (DEBUG) {
					lTime = System.nanoTime();
				}
				l.bnaModelChangedSync(evt);
				if (DEBUG) {
					lTime = System.nanoTime() - lTime;
					listenerStats.get(l).addAndGet(lTime);
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	@Override
	public void addBNAModelListener(IBNAModelListener l) {
		asyncListeners.add(l);
	}

	@Override
	public void removeBNAModelListener(IBNAModelListener l) {
		asyncListeners.remove(l);
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void fireBnaModelChangedAsync(
			final BNAModelEvent<ET, EK, EV> evt) {

		asyncExecutor.submit(new Runnable() {

			@Override
			public void run() {
				for (IBNAModelListener l : asyncListeners) {
					try {
						long lTime;
						if (DEBUG) {
							lTime = System.nanoTime();
						}
						l.bnaModelChanged(evt);
						if (DEBUG) {
							lTime = System.nanoTime() - lTime;
							listenerStats.get(l).addAndGet(lTime);
						}
					}
					catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
		});
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void fireBnaModelEvent(BNAModelEvent<ET, EK, EV> evt) {
		if (bulkChangeCount.get() > 0 && !firedBulkChangeEvent) {
			firedBulkChangeEvent = true;
			_fireBnaModelEvent(BNAModelEvent.create(this, EventType.BULK_CHANGE_BEGIN, bulkChangeCount.get() > 0));
		}
		_fireBnaModelEvent(evt);
	}

	private <ET extends IThing, EK extends IThingKey<EV>, EV> void _fireBnaModelEvent(BNAModelEvent<ET, EK, EV> evt) {
		fireBnaModelChangedSync(evt);
		fireBnaModelChangedAsync(evt);
	}

	@Override
	public void fireStreamNotificationEvent(String streamNotificationEvent) {
		fireBnaModelEvent(BNAModelEvent.create(this, EventType.STREAM_NOTIFICATION_EVENT, bulkChangeCount.get() > 0,
				streamNotificationEvent));
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
		fireBnaModelEvent(BNAModelEvent.<ET, EK, EV> create(this, EventType.THING_CHANGED, bulkChangeCount.get() > 0,
				thingEvent.getTargetThing(), thingEvent));
	}

	@Override
	public void beginBulkChange() {
		if (bulkChangeCount.getAndIncrement() == 0) {
			firedBulkChangeEvent = false;
		}
	}

	@Override
	public void endBulkChange() {
		if (bulkChangeCount.decrementAndGet() <= 0) {
			if (bulkChangeCount.get() < 0) {
				System.err.println("Bulk change count < 0");
			}
			if (firedBulkChangeEvent) {
				fireBnaModelEvent(BNAModelEvent.create(this, EventType.BULK_CHANGE_END, bulkChangeCount.get() > 0));
			}
		}
	}

	@Override
	public <T extends IThing> T addThing(final T t) {
		t.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				t.addThingListener(DefaultBNAModel.this);
				synchronized (thingTree) {
					thingTree.add(t);
					thingTreeModCount++;
				}
			}
		});
		fireBnaModelEvent(BNAModelEvent.create(this, BNAModelEvent.EventType.THING_ADDED, bulkChangeCount.get() > 0, t));
		return t;
	}

	@Override
	public <T extends IThing> T addThing(final T t, final IThing parentThing) {
		t.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				t.addThingListener(DefaultBNAModel.this);
				synchronized (thingTree) {
					thingTree.add(t, parentThing);
					thingTreeModCount++;
				}
			}
		});
		fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_ADDED, bulkChangeCount.get() > 0, t));
		return t;
	}

	public void removeThing(Object id) {
		removeThing(getThing(id));
	}

	@Override
	public void removeThing(final IThing t) {
		fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_REMOVING, bulkChangeCount.get() > 0, t));
		try {
			t.synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					t.removeThingListener(DefaultBNAModel.this);
					synchronized (thingTree) {
						thingTree.remove(t);
						thingTreeModCount++;
					}
				}
			});
		}
		finally {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_REMOVED, bulkChangeCount.get() > 0, t));
		}
	}

	@Override
	public void removeThingAndChildren(IThing t) {
		for (IThing thing : getChildThings(t)) {
			removeThingAndChildren(thing);
		}
		removeThing(t);
	}

	@Override
	public IThing getThing(Object id) {
		return thingIndex.getThing(id);
	}

	@Override
	public Iterable<IThing> getThings(Iterable<Object> thingIDs) {
		return Iterables.transform(thingIDs, new Function<Object, IThing>() {
			@Override
			public IThing apply(Object input) {
				return getThing(input);
			}
		});
	}

	@Override
	public Iterable<IThing> getThings() {
		synchronized (thingTree) {
			if (thingTreeListAtModCount != thingTreeModCount) {
				thingTreeList = thingTree.getAllThings();
				thingTreeListAtModCount = thingTreeModCount;
			}
			return thingTreeList;
		}
	}

	@Override
	public Iterable<IThing> getReverseThings() {
		synchronized (thingTree) {
			if (thingTreeListAtModCount != thingTreeModCount) {
				thingTreeList = thingTree.getAllThings();
				thingTreeListAtModCount = thingTreeModCount;
			}
			return Lists.reverse(thingTreeList);
		}
	}

	@Override
	public int getNumThings() {
		synchronized (thingTree) {
			return thingTree.size();
		}
	}

	@Override
	public IThing getParentThing(IThing thing) {
		synchronized (thingTree) {
			return thingTree.getParent(thing);
		}
	}

	@Override
	public Iterable<IThing> getChildThings(IThing thing) {
		synchronized (thingTree) {
			return thingTree.getChildThings(thing);
		}
	}

	@Override
	public Iterable<IThing> getAncestorThings(IThing thing) {
		synchronized (thingTree) {
			return thingTree.getAncestorThings(thing);
		}
	}

	@Override
	public Iterable<IThing> getDescendantThings(IThing thing) {
		synchronized (thingTree) {
			return thingTree.getAllDescendantThings(thing);
		}
	}

	@Override
	public void stackAbove(IThing lowerThing, Iterable<? extends IThing> toStackAboveThings) {
		synchronized (thingTree) {
			for (IThing toStackAboveThing : toStackAboveThings) {
				thingTree.moveAfter(toStackAboveThing, lowerThing);
			}
		}
		for (IThing mt : toStackAboveThings) {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, bulkChangeCount.get() > 0, mt));
		}
	}

	@Override
	public void bringToFront(Iterable<? extends IThing> things) {
		List<IThing> movedThings = Lists.newArrayList(things);
		synchronized (thingTree) {
			for (IThing thing : things) {
				thingTree.bringToFront(thing);
				thingTreeModCount++;
				movedThings.addAll(thingTree.getChildThings(thing));
			}
		}
		for (IThing mt : movedThings) {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, bulkChangeCount.get() > 0, mt));
		}
	}

	@Override
	public void sendToBack(Iterable<? extends IThing> things) {
		List<IThing> movedThings = Lists.newArrayList(things);
		synchronized (thingTree) {
			for (IThing thing : things) {
				thingTree.sendToBack(thing);
				thingTreeModCount++;
				movedThings.addAll(thingTree.getChildThings(thing));
			}
		}
		for (IThing mt : movedThings) {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, bulkChangeCount.get() > 0, mt));
		}
	}

	@Override
	public void reparent(IThing newParentThing, IThing thing) {
		List<IThing> movedThings = Lists.newArrayList(thing);
		synchronized (thingTree) {
			thingTree.reparent(newParentThing, thing);
			thingTreeModCount++;
			movedThings.addAll(thingTree.getChildThings(thing));
		}
		for (IThing mt : movedThings) {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, bulkChangeCount.get() > 0, mt));
		}
	}
}
