package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastIntMap;
import org.archstudio.sysutils.FastIntMap.Entry;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;

@NonNullByDefault
public class AbstractThing implements IThing {

	private static final AtomicLong atomicLong = new AtomicLong(0);

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
	private boolean initedProperties = false;

	public AbstractThing(@Nullable Object id) {
		this.id = id == null ? Long.valueOf(atomicLong.getAndIncrement()) : id;
		this.uid = (int) atomicLong.getAndIncrement();
		initProperties();
		checkState(initedProperties, "Thing %s must call super.initPropeties().", this.getClass().getName());
	}

	@Override
	public final Object getID() {
		return id;
	}

	@Override
	public int getUID() {
		return uid;
	}

	@Override
	public int hashCode() {
		return uid;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
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
	public Class<? extends IThingPeer<?>> getPeerClass() {
		return defaultPeerClassCache.getUnchecked(this.getClass());
	}

	private final CopyOnWriteArrayList<IThingListener> thingListeners = Lists.newCopyOnWriteArrayList();

	@Override
	public void addThingListener(IThingListener thingListener) {
		thingListeners.add(thingListener);
	}

	@Override
	public void removeThingListener(IThingListener thingListener) {
		thingListeners.remove(thingListener);
	}

	protected void fireThingEvent(ThingEvent evt) {
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
	public @Nullable
	<V> V get(IThingKey<V> key) {
		return key.postRead(getRaw(key));
	}

	@Override
	public final <V> V get(IThingKey<V> key, V valueIfNull) {
		V value = get(key);
		return value != null ? value : valueIfNull;
	}

	@SuppressWarnings("unchecked")
	private final @Nullable
	<V> V getRaw(IThingKey<V> key) {
		return (V) properties.get(key.getUID());
	}

	@Override
	public @Nullable
	<V> V set(IThingKey<V> key, @Nullable V value) {
		return setRaw(key, key.preWrite(value));
	}

	@SuppressWarnings("unchecked")
	private final @Nullable
	<V> V setRaw(IThingKey<V> key, @Nullable V value) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		V oldValue = (V) properties.put(key.getUID(), value);
		if (key.isFireEventOnChange() && !SystemUtils.nullEquals(oldValue, value)) {
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, key.postRead(oldValue),
					key.postRead(value)));
		}
		return oldValue;
	}

	@Override
	public boolean has(IThingKey<?> key) {
		return properties.containsKey(key.getUID());
	}

	@Override
	public <V> boolean has(IThing.IThingKey<V> key, @Nullable V value) {
		return SystemUtils.nullEquals(properties.get(key.getUID()), value);
	};

	@SuppressWarnings("unchecked")
	@Override
	public @Nullable
	<V> V remove(IThingKey<V> key) {
		if (Display.getCurrent() == null) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		boolean containedValue = properties.containsKey(key.getUID());
		V oldValue = (V) properties.get(key.getUID());
		properties.remove(key.getUID());
		if (containedValue && key.isFireEventOnChange()) {
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_REMOVED, this, key, oldValue, null));
		}
		return oldValue;
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
									return key = BNAUtils.getRegisteredKey(entry.getKey());
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
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(SystemUtils.simpleName(this.getClass())).append("[id=").append(id);
		for (Entry<?> entry : properties.entries()) {
			sb.append(",").append(BNAUtils.getRegisteredKey(entry.getKey())).append("=").append(entry.getValue());
		}
		sb.append("]");
		return sb.toString();
	}
}
