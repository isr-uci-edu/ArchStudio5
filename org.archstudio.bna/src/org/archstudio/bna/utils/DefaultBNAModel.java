package org.archstudio.bna.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;

public class DefaultBNAModel implements IBNAModel, IThingListener {

	protected static final boolean DEBUG = false;

	protected final LoadingCache<Object, AtomicLong> debugStats = !DEBUG ? null : CacheBuilder.newBuilder().build(
			new CacheLoader<Object, AtomicLong>() {

				@Override
				public AtomicLong load(@Nullable Object input) {
					return new AtomicLong();
				}
			});

	private static final class ThingIndex implements IBNAModelListener {

		private final Map<Object, IThing> indexMap = new MapMaker().weakValues().makeMap();

		@Override
		public void bnaModelChanged(BNAModelEvent evt) {
			switch (evt.getEventType()) {
			case THING_ADDED:
				IThing targetThing = evt.getTargetThing();
				indexMap.put(targetThing.getID(), targetThing);
				break;
			case THING_REMOVED:
				indexMap.remove(evt.getTargetThing().getID());
				break;
			default:
				// do nothing
			}
		}

		public IThing getThing(Object id) {
			if (id != null) {
				return indexMap.get(id);
			}
			return null;
		}
	}

	protected final ThingTree thingTree = new ThingTree();
	protected int thingTreeModCount = 0;
	protected int thingTreeListAtModCount = thingTreeModCount - 1;
	protected List<IThing> thingTreeList = Lists.newArrayList();
	protected final ThingIndex thingIndex = new ThingIndex();
	protected final CopyOnWriteArrayList<IBNAModelListener> synchListeners = Lists.newCopyOnWriteArrayList();
	protected AtomicInteger bulkChangeCount = new AtomicInteger();
	protected boolean firedBulkChangeEvent = false;

	public DefaultBNAModel() {
		synchListeners.add(thingIndex);
	}

	@Override
	public void dispose() {
		if (DEBUG) {
			for (Entry<Object, AtomicLong> e : SystemUtils.sortedByValue(debugStats.asMap().entrySet())) {
				System.err.println(e.getValue() + " " + e.getKey());
			}
		}
	}

	@Override
	public void addBNAModelListener(IBNAModelListener l) {
		synchListeners.add(l);
	}

	@Override
	public void removeBNAModelListener(IBNAModelListener l) {
		synchListeners.remove(l);
	}

	protected void fireBnaModelChanged(BNAModelEvent evt) {
		for (IBNAModelListener l : synchListeners) {
			try {
				long lTime;
				if (DEBUG) {
					lTime = System.nanoTime();
				}
				l.bnaModelChanged(evt);
				if (DEBUG) {
					lTime = System.nanoTime() - lTime;
					debugStats.getUnchecked(l).addAndGet(lTime);
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	protected void fireBnaModelEvent(BNAModelEvent evt) {
		if (bulkChangeCount.get() > 0 && !firedBulkChangeEvent) {
			firedBulkChangeEvent = true;
			fireBnaModelChanged(BNAModelEvent.create(this, EventType.BULK_CHANGE_BEGIN, bulkChangeCount.get() > 0));
		}
		fireBnaModelChanged(evt);
	}

	@Override
	public void fireStreamNotificationEvent(String streamNotificationEvent) {
		fireBnaModelEvent(BNAModelEvent.create(this, EventType.STREAM_NOTIFICATION_EVENT, bulkChangeCount.get() > 0,
				streamNotificationEvent));
	}

	@Override
	public void thingChanged(ThingEvent thingEvent) {
		fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_CHANGED, bulkChangeCount.get() > 0,
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
				bulkChangeCount.incrementAndGet();
			}
			if (firedBulkChangeEvent) {
				fireBnaModelEvent(BNAModelEvent.create(this, EventType.BULK_CHANGE_END, bulkChangeCount.get() > 0));
			}
		}
	}

	@Override
	public <T extends IThing> T addThing(final T t) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		t.addThingListener(this);
		thingTree.add(t);
		thingTreeModCount++;
		fireBnaModelEvent(BNAModelEvent.create(this, BNAModelEvent.EventType.THING_ADDED, bulkChangeCount.get() > 0, t));
		return t;
	}

	@Override
	public <T extends IThing> T addThing(final T t, final @Nullable IThing parentThing) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		t.addThingListener(this);
		thingTree.add(t, parentThing);
		thingTreeModCount++;
		fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_ADDED, bulkChangeCount.get() > 0, t));
		return t;
	}

	public void removeThing(Object id) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		removeThing(getThing(id));
	}

	@Override
	public void removeThing(final IThing t) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_REMOVING, bulkChangeCount.get() > 0, t));
		try {
			t.removeThingListener(this);
			thingTree.remove(t);
			thingTreeModCount++;
		}
		finally {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_REMOVED, bulkChangeCount.get() > 0, t));
		}
	}

	@Override
	public void removeThingAndChildren(IThing t) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

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
	public List<IThing> getThingsByID(Iterable<Object> thingIDs) {
		return Lists.newArrayList(Iterables.transform(thingIDs, new Function<Object, IThing>() {

			@Override
			public IThing apply(Object input) {
				return getThing(input);
			}
		}));
	}

	@Override
	public List<IThing> getAllThings() {
		if (thingTreeListAtModCount != thingTreeModCount) {
			thingTreeList = thingTree.getAllThings();
			thingTreeListAtModCount = thingTreeModCount;
		}
		return thingTreeList;
	}

	@Override
	public List<IThing> getReverseThings() {
		if (thingTreeListAtModCount != thingTreeModCount) {
			thingTreeList = thingTree.getAllThings();
			thingTreeListAtModCount = thingTreeModCount;
		}
		return Lists.reverse(thingTreeList);
	}

	@Override
	public int getNumThings() {
		return thingTree.size();
	}

	@Override
	public IThing getParentThing(IThing thing) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		return thingTree.getParent(thing);
	}

	@Override
	public Collection<IThing> getChildThings(IThing thing) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		return thingTree.getChildThings(thing);
	}

	@Override
	public List<IThing> getAncestorThings(IThing thing) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		return thingTree.getAncestorThings(thing);
	}

	@Override
	public List<IThing> getDescendantThings(IThing thing) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		return thingTree.getAllDescendantThings(thing);
	}

	@Override
	public void bringToFront(Set<? extends IThing> things) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		List<IThing> movedThings = Lists.newArrayListWithExpectedSize(things.size());
		for (IThing thing : getAllThings()) {
			if (things.contains(thing)) {
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
	public void sendToBack(Set<? extends IThing> things) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		List<IThing> movedThings = Lists.newArrayListWithExpectedSize(things.size());
		for (IThing thing : getAllThings()) {
			if (things.contains(thing)) {
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
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		List<IThing> movedThings = Lists.newArrayList(thing);
		thingTree.reparent(newParentThing, thing);
		thingTreeModCount++;
		movedThings.addAll(thingTree.getChildThings(thing));
		for (IThing mt : movedThings) {
			fireBnaModelEvent(BNAModelEvent.create(this, EventType.THING_RESTACKED, bulkChangeCount.get() > 0, mt));
		}
	}
}
