package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkState;
import static org.archstudio.sysutils.SystemUtils.nullEquals;
import static org.archstudio.sysutils.SystemUtils.simpleName;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastMap;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@NonNullByDefault
public class AbstractThing implements IThing {

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
	private final FastMap<IThingKey<?>, Object> properties = new FastMap<>(true);
	private final ReadWriteLock lock;
	private boolean initedProperties = false;

	public AbstractThing(@Nullable Object id) {
		this.id = id != null ? id : new Object();
		this.lock = BNAUtils.LOCK_FACTORY.newReentrantReadWriteLock("Thing " + id);
		initProperties();
		checkState(initedProperties, "Thing %s must call super.initPropeties().", this.getClass().getName());
	}

	@Override
	public final Object getID() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		}
		else if (!id.equals(other.id)) {
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
			return (V) properties.get(key);
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
			Map.Entry<IThingKey<?>, Object> entry = properties.createEntry(key);
			V oldValue = (V) entry.getValue();
			if (!nullEquals(oldValue, value)) {
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
			return properties.containsKey(key);
		}
		finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public final <V> boolean has(IThingKey<V> key, @Nullable V value) {
		lock.readLock().lock();
		try {
			return nullEquals(properties.get(key), value);
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
			Map.Entry<IThingKey<?>, Object> entry = properties.removeEntry(key);
			if (entry != null) {
				if (key.isFireEventOnChange()) {
					fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_REMOVED, this, key,
							entry.getValue(), null));
				}
				return (V) entry.getValue();
			}
			return null;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public Set<Map.Entry<IThingKey<?>, ?>> entrySet() {
		return Sets.newHashSet(Iterables.transform(properties.entrySet(),
				new Function<Map.Entry<IThingKey<?>, Object>, Map.Entry<IThingKey<?>, ?>>() {
					@Override
					public Entry<IThingKey<?>, ?> apply(final Entry<IThingKey<?>, Object> input) {
						return new Map.Entry<IThingKey<?>, Object>() {

							@Override
							public IThingKey<?> getKey() {
								return input.getKey();
							}

							@Override
							public Object getValue() {
								return input.getValue();
							}

							@Override
							public Object setValue(Object value) {
								throw new UnsupportedOperationException();
							}
						};
					}
				}));
	}

	@Override
	public String toString() {
		lock.readLock().lock();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(simpleName(this.getClass())).append("[id=").append(id);
			for (Map.Entry<IThingKey<?>, Object> entry : SystemUtils.sortedByKey(properties.entrySet())) {
				sb.append(",").append(entry.getKey()).append("=").append(entry.getValue());
			}
			sb.append("]");
			return sb.toString();
		}
		finally {
			lock.readLock().unlock();
		}
	}
}
