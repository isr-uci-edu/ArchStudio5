package org.archstudio.bna.utils;

import static org.archstudio.sysutils.SystemUtils.sortedByValue;

import java.util.Deque;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IPrivilegedBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.sysutils.FastIntMap;
import org.archstudio.sysutils.FastLongMap;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public class DefaultBNAModel implements IBNAModel, IThingListener {

	public boolean PROFILE = true;
	protected static final LoadingCache<Object, AtomicLong> profileStats = CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Object, AtomicLong>() {
				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});
	protected static int eventsNotFiredStats = 0;

	private class DefaultBNAModelEventProcessingThread extends Thread {

		private boolean disposed = false;
		private final Object EXIT_NOTIFICATION = Lists.newArrayList("Exiting " + this.getClass(), this);
		private final BNAModelEvent EXIT_EVENT = BNAModelEvent.create(DefaultBNAModel.this,
				EventType.STREAM_NOTIFICATION_EVENT, EXIT_NOTIFICATION);
		private final ThreadPoolExecutor asyncExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						t.setPriority(Thread.MAX_PRIORITY);
						t.setDaemon(true);
						t.setName(DefaultBNAModelEventProcessingThread.class.getName());
						return t;
					}
				});
		private final Deque<BNAModelEvent> eventQueue = new LinkedBlockingDeque<>();
		private final FastIntMap<Integer> thingMinimumModCount = new FastIntMap<>();
		private final FastLongMap<BNAModelEvent> pendingThingEvents = new FastLongMap<>();
		private boolean firedBulkChangeBegin = false;
		private int bulkChangeCount = 0;
		private int pendingBulkChangeWorthyEvents = 0;

		public DefaultBNAModelEventProcessingThread() {
			super(DefaultBNAModel.class.getName());
			setDaemon(true);
			setPriority(MAX_PRIORITY);
		}

		public void ensureFlush() {
			synchronized (eventQueue) {
				if (!firedBulkChangeBegin) {
					process(FLUSH_ENSURE);
				}
			}
		}

		public void process(BNAModelEvent event) {
			if (disposed) {
				return;
			}
			synchronized (eventQueue) {
				ThingEvent thingEvent = event.getThingEvent();
				if (thingEvent != null) {
					{
						/*
						 * If a thing has recently been added, but the notification has not gone out yet, then ignore
						 * it's property changes until the added notification goes out.
						 */
						int thingUID = thingEvent.getTargetThing().getUID();
						FastIntMap.Entry<Integer> entry = thingMinimumModCount.getEntry(thingUID);
						if (entry == null || entry.getValue() > thingEvent.getModCount()) {
							eventsNotFiredStats++;
							return;
						}
					}
					{
						/*
						 * If a thing key's value has already been updated, but the notification has not gone out yet,
						 * then roll this update into that one to reduce the overall number of events sent out.
						 */
						long thingKeyUID = thingEvent.getThingKeyUID();
						FastLongMap.Entry<BNAModelEvent> entry = pendingThingEvents.createEntry(thingKeyUID);
						if (entry.getValue() != null && entry.getValue().getThread() == event.getThread()) {
							entry.getValue().getThingEvent().setNewPropertyValue(thingEvent.getNewPropertyValue());
							eventsNotFiredStats++;
							return;
						}
						else {
							entry.setValue(event);
						}
					}
				}
				if (!firedBulkChangeBegin) {
					if (event.getEventType() == EventType.BULK_CHANGE_BEGIN) {
						bulkChangeCount++;
						return;
					}
					else if (event.getEventType() == EventType.BULK_CHANGE_END) {
						if (--bulkChangeCount <= 0) {
							bulkChangeCount = 0;
						}
						return;
					}
				}
				else {
					if (event.getEventType() == EventType.BULK_CHANGE_BEGIN) {
						bulkChangeCount++;
						return;
					}
					else if (event.getEventType() == EventType.BULK_CHANGE_END) {
						if (--bulkChangeCount <= 0) {
							bulkChangeCount = 0;
						}
						if (!eventQueue.isEmpty() || bulkChangeCount > 0) {
							return;
						}
					}
				}
				if (isBulkChangeWorthyEvent(event)) {
					pendingBulkChangeWorthyEvents++;
				}
				eventQueue.add(event);
				eventQueue.notifyAll();
			}
		}

		private boolean isBulkChangeWorthyEvent(BNAModelEvent event) {
			switch (event.getEventType()) {
			case BULK_CHANGE_BEGIN:
			case BULK_CHANGE_END:
				return false;
			case STREAM_NOTIFICATION_EVENT:
				if (event.getStreamNotification() instanceof RunnableStreamNotification) {
					return false;
				}
				return true;
			default:
				return true;
			}
		}

		public void dispose() {
			disposed = true;
			synchronized (eventQueue) {
				eventQueue.clear();
				// add a final event, to be removed by the thread, which causes it to exit
				eventQueue.add(EXIT_EVENT);
				eventQueue.notifyAll();
			}
		}

		public void waitForCompletion(IProgressMonitor monitor) {
			SubMonitor progress = SubMonitor.convert(monitor);
			synchronized (eventQueue) {
				while (firedBulkChangeBegin) {
					try {
						if (Display.getCurrent() != null) {
							progress.setWorkRemaining(10000);
							SubMonitor.convert(progress.newChild(1)).worked(1);
						}
						eventQueue.wait();
					}
					catch (Exception e) {
					}
				}
			}
		}

		@Override
		public void run() {
			while (true) {
				BNAModelEvent event = null;
				synchronized (eventQueue) {
					while (eventQueue.isEmpty()) {
						try {
							eventQueue.wait();
						}
						catch (InterruptedException e) {
						}
					}
					event = eventQueue.remove();
					if (event == EXIT_EVENT) {
						// final event, signaling exit
						asyncExecutor.shutdownNow();
						if (PROFILE) {
							synchronized (profileStats) {
								System.err.println("Total events skipped: " + eventsNotFiredStats);
								for (Entry<Object, AtomicLong> entry : sortedByValue(profileStats.asMap().entrySet())) {
									System.err.println(entry.getValue() + "\t" + entry.getKey());
								}
							}
						}
						return;
					}
					ThingEvent thingEvent = event.getThingEvent();
					if (thingEvent != null) {
						pendingThingEvents.remove(thingEvent.getThingKeyUID());
					}
					if (isBulkChangeWorthyEvent(event)) {
						pendingBulkChangeWorthyEvents--;
					}
					eventQueue.notifyAll();
				}
				if (event.getEventType() == EventType.THING_ADDED) {
					IThing thing = event.getTargetThing();
					thingMinimumModCount.put(thing.getUID(), thing.getModCount());
				}
				if (event.getStreamNotification() instanceof RunnableStreamNotification) {
					// run these, don't send them out
					((RunnableStreamNotification) event.getStreamNotification()).run();
				}
				else if (event.getEventType() == EventType.BULK_CHANGE_BEGIN) {
					throw new IllegalArgumentException("BulkChangeBegin should be handled in process(...).");
				}
				else if (event.getEventType() == EventType.BULK_CHANGE_END) {
					// process these later..., we don't want to sent it out
				}
				else {
					if (!firedBulkChangeBegin) {
						fireBNAModelEvent(BULK_CHANGE_BEGIN);
						firedBulkChangeBegin = true;
					}
					fireBNAModelEvent(event);
				}
				if (firedBulkChangeBegin && bulkChangeCount == 0 && pendingBulkChangeWorthyEvents == 0) {
					fireBNAModelEvent(FLUSH_BULK_CHANGE_END);
					if (pendingBulkChangeWorthyEvents == 0) {
						firedBulkChangeBegin = false;
						fireBNAModelEvent(BULK_CHANGE_END);
						synchronized (eventQueue) {
							eventQueue.notifyAll();
						}
					}
				}
			}
		}

		private void fireBNAModelEvent(final BNAModelEvent event) {
			final AtomicInteger activeThreadCount = new AtomicInteger();
			for (final IPrivilegedBNAModelListener l : privilegedListeners) {
				activeThreadCount.incrementAndGet();
				asyncExecutor.execute(new Runnable() {
					@Override
					public void run() {
						long time = System.nanoTime();
						try {
							l.privilegedBNAModelChanged(event);
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
						finally {
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.getUnchecked(l).addAndGet(time);
							}
							activeThreadCount.decrementAndGet();
							synchronized (activeThreadCount) {
								activeThreadCount.notifyAll();
							}
						}
					}
				});
			}
			synchronized (activeThreadCount) {
				while (activeThreadCount.get() > 0) {
					try {
						activeThreadCount.wait();
					}
					catch (InterruptedException e) {
					}
				}
			}
			for (final IBNAModelListener l : listeners) {
				activeThreadCount.incrementAndGet();
				asyncExecutor.execute(new Runnable() {
					@Override
					public void run() {
						long time = System.nanoTime();
						try {
							l.bnaModelChanged(event);
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
						finally {
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.getUnchecked(l).addAndGet(time);
							}
							activeThreadCount.decrementAndGet();
							synchronized (activeThreadCount) {
								activeThreadCount.notifyAll();
							}
						}
					}
				});
			}
			synchronized (activeThreadCount) {
				while (activeThreadCount.get() > 0) {
					try {
						activeThreadCount.wait();
					}
					catch (InterruptedException e) {
					}
				}
			}
		}
	}

	protected final BNAModelEvent BULK_CHANGE_BEGIN = BNAModelEvent.create(this, EventType.BULK_CHANGE_BEGIN);
	protected final BNAModelEvent FLUSH_MANUAL = BNAModelEvent.create(this, EventType.FLUSH, "Manual");
	protected final BNAModelEvent FLUSH_EMPTY = BNAModelEvent.create(this, EventType.FLUSH, "Empty");
	protected final BNAModelEvent FLUSH_ENSURE = BNAModelEvent.create(this, EventType.FLUSH, "Ensure");
	protected final BNAModelEvent FLUSH_BULK_CHANGE_END = BNAModelEvent.create(this, EventType.FLUSH, "BulkChangeEnd");
	protected final BNAModelEvent BULK_CHANGE_END = BNAModelEvent.create(this, EventType.BULK_CHANGE_END);

	protected final ReentrantReadWriteLock lock = BNAUtils.LOCK_FACTORY.newReentrantReadWriteLock("Default BNA Model");
	protected final ThingTree thingTree = new ThingTree();
	protected final CopyOnWriteArrayList<IPrivilegedBNAModelListener> privilegedListeners = Lists
			.newCopyOnWriteArrayList();
	protected final CopyOnWriteArrayList<IBNAModelListener> listeners = Lists.newCopyOnWriteArrayList();
	protected final DefaultBNAModelEventProcessingThread eventProcessingThread;

	public DefaultBNAModel() {
		eventProcessingThread = new DefaultBNAModelEventProcessingThread();
		eventProcessingThread.start();
	}

	@Override
	public void dispose() {
		eventProcessingThread.dispose();
	}

	@Override
	public void addPrivilegedBNAModelListener(IPrivilegedBNAModelListener l) {
		privilegedListeners.add(l);
	}

	@Override
	public void removePrivilegedBNAModelListener(IPrivilegedBNAModelListener l) {
		privilegedListeners.remove(l);
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
	public void fireStreamNotificationEvent(Object streamNotificationEvent) {
		eventProcessingThread.process(BNAModelEvent.create(this, EventType.STREAM_NOTIFICATION_EVENT,
				streamNotificationEvent));
	}

	@Override
	public void beginBulkChange() {
		eventProcessingThread.process(BULK_CHANGE_BEGIN);
	}

	@Override
	public boolean isInBulkChange() {
		return eventProcessingThread.firedBulkChangeBegin;
	}

	@Override
	public void endBulkChange() {
		eventProcessingThread.process(BULK_CHANGE_END);
	}

	@Override
	public void flush() {
		flush(null);
	}

	@Override
	public void flush(IProgressMonitor monitor) {
		eventProcessingThread.process(FLUSH_MANUAL);
		eventProcessingThread.waitForCompletion(monitor);
	}

	@Override
	public void ensureFlush() {
		eventProcessingThread.ensureFlush();
	}

	@Override
	public void thingChanged(ThingEvent thingEvent) {
		eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_CHANGED, thingEvent.getTargetThing(),
				thingEvent));
	}

	@Override
	public <T extends IThing> T addThing(T thing) {
		return addThing(thing, null);
	}

	@Override
	public <T extends IThing> T addThing(T thing, @Nullable IThing parentThing) {
		lock.writeLock().lock();
		try {
			thingTree.add(thing, parentThing);
			thing.insertThingListener(this);
			eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_ADDED, thing));
			return thing;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public <T extends IThing> T insertThing(T thing, IThing beforeThing) {
		lock.writeLock().lock();
		try {
			thingTree.insert(thing, beforeThing);
			thing.insertThingListener(this);
			eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_ADDED, thing));
			return thing;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void removeThing(IThing t) {
		lock.writeLock().lock();
		try {
			t.removeThingListener(this);
			thingTree.remove(t);
			eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_REMOVED, t));
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void removeThingAndChildren(IThing thing) {
		for (IThing childThing : getChildThings(thing)) {
			removeThingAndChildren(childThing);
		}
		removeThing(thing);
	}

	@Override
	public @Nullable
	IThing getThing(int uid) {
		lock.readLock().lock();
		try {
			return thingTree.getThing(uid);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public @Nullable
	IThing getThing(@Nullable Integer uid) {
		return uid != null ? getThing(uid.intValue()) : null;
	}

	@Override
	public List<IThing> getThingsByUID(Iterable<Integer> thingUIDs) {
		List<IThing> things = Lists.newArrayList();
		for (Integer uid : thingUIDs) {
			if (uid != null) {
				IThing thing = getThing(uid);
				if (thing != null) {
					things.add(thing);
				}
			}
		}
		return things;
	}

	@Override
	public @Nullable
	IThing getThing(@Nullable Object id) {
		lock.readLock().lock();
		try {
			return thingTree.getThing(id);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<IThing> getThingsByID(Iterable<Object> thingIDs) {
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
	public List<IThing> getAllThings() {
		lock.readLock().lock();
		try {
			return thingTree.getAllThings();
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<IThing> getReverseThings() {
		return Lists.reverse(getAllThings());
	}

	@Override
	public int getNumThings() {
		lock.readLock().lock();
		try {
			return thingTree.size();
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public IThing getParentThing(IThing thing) {
		lock.readLock().lock();
		try {
			return thingTree.getParent(thing);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<IThing> getChildThings(IThing thing) {
		lock.readLock().lock();
		try {
			return thingTree.getChildThings(thing);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<IThing> getAncestorThings(IThing thing) {
		lock.readLock().lock();
		try {
			return thingTree.getAncestorThings(thing);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<IThing> getDescendantThings(IThing thing) {
		lock.readLock().lock();
		try {
			return thingTree.getAllDescendantThings(thing);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public void bringToFront(IThing thing) {
		lock.writeLock().lock();
		try {
			thingTree.bringToFront(thing);
			eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void sendToBack(IThing thing) {
		lock.writeLock().lock();
		try {
			thingTree.sendToBack(thing);
			eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_RESTACKED, thing));
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void reparent(IThing newParentThing, IThing thing) {
		lock.writeLock().lock();
		try {
			List<IThing> movedThings = Lists.newArrayList(thing);
			thingTree.reparent(newParentThing, thing);
			movedThings.addAll(thingTree.getChildThings(thing));
			for (IThing movedThing : movedThings) {
				eventProcessingThread.process(BNAModelEvent.create(this, EventType.THING_RESTACKED, movedThing));
			}
		}
		finally {
			lock.writeLock().unlock();
		}
	}

}
