package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkState;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.TypedHashMap;
import org.archstudio.sysutils.TypedMap;
import org.archstudio.sysutils.TypedMap.Key;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@NonNullByDefault
public class AbstractThing implements IThing {

	private static final AtomicLong atomicLong = new AtomicLong(0);

	private static final LoadingCache<Class<? extends IThing>, Class<IThingPeer<?>>> defaultPeerClassCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends IThing>, Class<IThingPeer<?>>>() {
				@SuppressWarnings("unchecked")
				@Override
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
	private final TypedMap properties = TypedHashMap.create();
	private boolean initedProperties = false;

	public AbstractThing(@Nullable Object id) {
		this.id = id == null ? Long.valueOf(atomicLong.getAndIncrement()) : id;
		initProperties();
		checkState(initedProperties, "Thing %s must call super.initPropeties().", this.getClass().getName());
	}

	@Override
	public final Object getID() {
		return id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
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

	@OverridingMethodsMustInvokeSuper
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
	public synchronized void removeThingListener(IThingListener thingListener) {
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

	private @Nullable
	<V> V getRaw(IThingKey<V> key) {
		if (Display.getCurrent() == null)
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);

		return properties.get(key);
	}

	@Override
	public @Nullable
	<V> V set(IThingKey<V> key, @Nullable V value) {
		return setRaw(key, key.preWrite(value));
	}

	private @Nullable
	<V> V setRaw(IThingKey<V> key, @Nullable V value) {
		if (Display.getCurrent() == null)
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);

		V oldValue = properties.put(key, value);
		if (key.isFireEventOnChange() && !SystemUtils.nullEquals(oldValue, value)) {
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, key.postRead(oldValue),
					key.postRead(value)));
		}
		return oldValue;
	}

	@Override
	public boolean has(IThingKey<?> key) {
		if (Display.getCurrent() == null)
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);

		return properties.containsKey(key);
	}

	@Override
	public <V> boolean has(IThing.IThingKey<V> key, @Nullable V value) {
		if (Display.getCurrent() == null)
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);

		return SystemUtils.nullEquals(properties.get(key), value);
	};

	@Override
	public @Nullable
	<V> V remove(IThingKey<V> key) {
		if (Display.getCurrent() == null)
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);

		boolean containedValue = properties.containsKey(key);
		V oldValue = properties.remove(key);
		if (containedValue && key.isFireEventOnChange()) {
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_REMOVED, this, key, oldValue, null));
		}
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IThingKey<?>> keySet() {
		if (Display.getCurrent() == null)
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);

		return Sets.newHashSet((Set<IThingKey<?>>) properties.keySet());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(SystemUtils.simpleName(this.getClass())).append("[id=").append(id);
		for (Key<?> key : SystemUtils.sorted(properties.keySet())) {
			sb.append(",").append(key).append("=").append(properties.get(key));
		}
		sb.append("]");
		return sb.toString();
	}
}
