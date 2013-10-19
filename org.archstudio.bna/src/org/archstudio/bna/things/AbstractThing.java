package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkState;
import static org.archstudio.sysutils.SystemUtils.nullEquals;
import static org.archstudio.sysutils.SystemUtils.simpleName;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.AbstractThingKey;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastIntMap;
import org.archstudio.sysutils.FastIntMap.Entry;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;

@NonNullByDefault
public class AbstractThing implements IThing {

	private static final AtomicInteger uidGenerator = new AtomicInteger(0);

	private static final LoadingCache<Class<? extends IThing>, Class<IThingPeer<?>>> defaultPeerClassCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends IThing>, Class<IThingPeer<?>>>() {
				@Override
				@SuppressWarnings("unchecked")
				public Class<IThingPeer<?>> load(@Nullable Class<? extends IThing> input) throws Exception {
					Class<?> thingClass = input;
					while (thingClass != null) {
						try {
							return (Class<IThingPeer<?>>) Class.forName(//
									thingClass.getName() + "Peer", false, thingClass.getClassLoader());
						}
						catch (ClassNotFoundException e) {
						}
						thingClass = thingClass.getSuperclass();
					}
					throw new ClassNotFoundException("IThingPeer not found: "
							+ (input != null ? input.getName() : null));
				}
			});

	private final Object id;
	private final int uid;
	private final FastIntMap<Object> properties = new FastIntMap<Object>();
	private final ReadWriteLock lock;
	private boolean initedProperties = false;
	volatile private int modCount = 0;

	public AbstractThing(@Nullable Object id) {
		this.id = id != null ? id : new Object();
		this.uid = uidGenerator.incrementAndGet();
		this.lock = BNAUtils.LOCK_FACTORY.newReentrantReadWriteLock("Thing " + uid);
		initProperties();
		checkState(initedProperties, "Thing %s must call super.initPropeties().", this.getClass().getName());
	}

	@Override
	public final Object getID() {
		return id;
	}

	@Override
	public final int getUID() {
		return uid;
	}

	@Override
	public final int hashCode() {
		return uid;
	}

	@Override
	public final boolean equals(@Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractThing other = (AbstractThing) obj;
		if (uid != other.uid) {
			return false;
		}
		return true;
	}

	protected void initProperties() {
		checkState(!initedProperties, "Thing %s may only call super.initPropeties() once.", this.getClass());
		initedProperties = true;
	}

	@Override
	public final Class<? extends IThingPeer<?>> getPeerClass() {
		return defaultPeerClassCache.getUnchecked(this.getClass());
	}

	private final CopyOnWriteArrayList<IThingListener> thingListeners = Lists.newCopyOnWriteArrayList();

	@Override
	public void insertThingListener(IThingListener thingListener) {
		thingListeners.add(0, thingListener);
	}

	@Override
	public final void addThingListener(IThingListener thingListener) {
		thingListeners.add(thingListener);
	}

	@Override
	public final void removeThingListener(IThingListener thingListener) {
		thingListeners.remove(thingListener);
	}

	protected final void fireThingEvent(ThingEvent evt) {
		for (IThingListener l : thingListeners) {
			try {
				l.thingChanged(evt);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public final @Nullable
	<V> V get(IThingKey<V> key) {
		return key.postRead(getRaw(key));
	}

	@Override
	public final <V> V get(IThingKey<V> key, V valueIfNull) {
		V value = getRaw(key);
		return value != null ? key.postRead(value) : valueIfNull;
	}

	@SuppressWarnings("unchecked")
	private @Nullable
	<V> V getRaw(IThingKey<V> key) {
		lock.readLock().lock();
		try {
			return (V) properties.get(key.getUID());
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public final <V> void set(IThingKey<V> key, @Nullable V value) {
		setRaw(key, value);
	}

	@Override
	public final @Nullable
	<V> V getAndSet(IThingKey<V> key, @Nullable V value) {
		return key.postRead(setRaw(key, value));
	}

	@SuppressWarnings("unchecked")
	private @Nullable
	<V> V setRaw(IThingKey<V> key, @Nullable V value) {
		lock.writeLock().lock();
		try {
			Entry<Object> entry = properties.createEntry(key.getUID());
			V oldValue = (V) entry.getValue();
			if (!nullEquals(oldValue, value)) {
				modCount++;
				entry.setValue(key.preWrite(value));
				if (key.isFireEventOnChange()) {
					fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, oldValue,
							key.preWrite(value)));
				}
			}
			return oldValue;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public final boolean has(IThingKey<?> key) {
		lock.readLock().lock();
		try {
			return properties.containsKey(key.getUID());
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public final <V> boolean has(IThingKey<V> key, @Nullable V value) {
		lock.readLock().lock();
		try {
			return nullEquals(properties.get(key.getUID()), value);
		}
		finally {
			lock.readLock().unlock();
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	public final @Nullable
	<V> V remove(IThingKey<V> key) {
		lock.writeLock().lock();
		try {
			Entry<V> entry = (Entry<V>) properties.removeEntry(key.getUID());
			if (entry != null) {
				modCount++;
				if (key.isFireEventOnChange()) {
					fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_REMOVED, this, key,
							entry.getValue(), null));
				}
				return entry.getValue();
			}
			return null;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public Iterable<IEntry> entries() {
		return new Iterable<IEntry>() {
			@Override
			public Iterator<IEntry> iterator() {
				return new AbstractIterator<IEntry>() {
					Iterator<Entry<Object>> i = properties.entries().iterator();

					@Override
					protected IEntry computeNext() {
						if (i.hasNext()) {
							final Entry<Object> entry = i.next();
							return new IEntry() {
								IThingKey<?> key = null;

								@Override
								public IThingKey<?> getKey() {
									if (key != null) {
										return key;
									}
									return key = AbstractThingKey.getKey(entry.getKey());
								}

								@Override
								public Object getValue() {
									return entry.getValue();
								}
							};
						}
						return endOfData();
					}
				};
			}
		};
	}

	@Override
	public int getModCount() {
		/*
		 * Note: Locking here causes deadlock with the synchronized eventQueue from
		 * DefaultBNAModelEventProcessingThread.
		 */
		//lock.readLock().lock();
		//try {
		return modCount;
		//}
		//finally {
		//	lock.readLock().unlock();
		//}
	}

	@Override
	public String toString() {
		lock.readLock().lock();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(simpleName(this.getClass())).append("[uid=").append(uid);
			for (Entry<?> entry : properties.entries()) {
				sb.append(",").append(AbstractThingKey.getKey(entry.getKey())).append("=").append(entry.getValue());
			}
			sb.append("]");
			return sb.toString();
		}
		finally {
			lock.readLock().unlock();
		}
	}
}
