package org.archstudio.bna.things;

import static org.archstudio.sysutils.SystemUtils.simpleName;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastMap;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@NonNullByDefault
public abstract class AbstractThing implements IThing {

	private final Object id;
	private final FastMap<IThingKey<?>, Object> properties = new FastMap<>(true);
	private FastMap<IThingKey<?>, Object> shapeModifyingKeys = new FastMap<>(true);

	public AbstractThing(@Nullable Object id) {
		this.id = id != null ? id : new Object();
		initProperties();
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
	}

	protected final <V> void initProperty(IThingKey<V> key, @Nullable V value) {
		properties.put(key, value);
	}

	protected void addShapeModifyingKey(IThingKey<?> key) {
		shapeModifyingKeys.put(key, null);
	}

	protected void removeShapeModifyingKey(IThingKey<?> key) {
		shapeModifyingKeys.remove(key);
	}

	@Override
	public boolean isShapeModifyingKey(IThingKey<?> key) {
		return shapeModifyingKeys.containsKey(key);
	}

	@Override
	public Set<IThingKey<?>> getShapeModifyingKeys() {
		return shapeModifyingKeys.keySet();
	}

	private final CopyOnWriteArrayList<IThingListener> thingListeners = Lists.newCopyOnWriteArrayList();

	@Override
	public final void addThingListener(IThingListener thingListener) {
		thingListeners.add(thingListener);
	}

	@Override
	public final void removeThingListener(IThingListener thingListener) {
		thingListeners.remove(thingListener);
	}

	private final void fireThingEvent(ThingEvent event) {
		for (IThingListener l : thingListeners) {
			try {
				l.thingChanged(event);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public final @Nullable
	<V> V get(IThingKey<V> key) {
		V value = getRaw(key);
		return value != null ? key.clone(value) : null;
	}

	@Override
	public final <V> V get(IThingKey<V> key, V valueIfNull) {
		V value = getRaw(key);
		return value != null ? key.clone(value) : valueIfNull;
	}

	@SuppressWarnings("unchecked")
	protected final synchronized @Nullable
	<V> V getRaw(IThingKey<V> key) {
		return (V) properties.get(key);
	}

	protected final <V> V getRaw(IThingKey<V> key, V valueIfNull) {
		V value = getRaw(key);
		return value != null ? value : valueIfNull;
	}

	@Override
	synchronized public final boolean has(IThingKey<?> key) {
		return properties.containsKey(key);
	}

	@Override
	synchronized public final <V> boolean has(IThingKey<V> key, @Nullable V value) {
		Map.Entry<IThingKey<?>, Object> entry = properties.getEntry(key);
		if (entry != null) {
			return BNAUtils.like(entry.getValue(), value);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	synchronized public final <V> V set(IThingKey<V> key, @Nullable V value) {
		if (!key.isNullable() && value == null) {
			throw new NullPointerException(key.toString());
		}
		V oldValue = value;
		synchronized (this) {
			Map.Entry<IThingKey<?>, Object> entry = properties.createEntry(key);
			if (!BNAUtils.like(entry.getValue(), value)) {
				oldValue = (V) entry.getValue();
				entry.setValue(key.clone(value));
				fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, oldValue, value));
			}
		}
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	synchronized protected final <V> V setRaw(IThingKey<V> key, @Nullable V value) {
		if (!key.isNullable() && value == null) {
			throw new NullPointerException(key.toString());
		}
		V oldValue = value;
		synchronized (this) {
			Map.Entry<IThingKey<?>, Object> entry = properties.createEntry(key);
			if (!BNAUtils.like(entry.getValue(), value)) {
				oldValue = (V) entry.getValue();
				entry.setValue(key.clone(value));
				fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, oldValue, value));
			}
		}
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final @Nullable
	<V> V remove(IThingKey<V> key) {
		V oldValue = null;
		synchronized (this) {
			Map.Entry<IThingKey<?>, Object> entry = properties.removeEntry(key);
			if (entry != null) {
				oldValue = (V) entry.getValue();
				fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_REMOVED, this, key, entry.getValue(),
						null));
			}
		}
		return oldValue;
	}

	@Override
	synchronized public Set<Map.Entry<IThingKey<?>, ?>> entrySet() {
		return Collections.unmodifiableSet(Sets.newHashSet(Iterables.transform(properties.entrySet(),
				new Function<Map.Entry<IThingKey<?>, Object>, Map.Entry<IThingKey<?>, ?>>() {
					@Override
					public Map.Entry<IThingKey<?>, ?> apply(Map.Entry<IThingKey<?>, Object> input) {

						final IThingKey<?> key = input.getKey();
						final Object value = input.getValue();

						return new Map.Entry<IThingKey<?>, Object>() {

							@Override
							public IThingKey<?> getKey() {
								return key;
							}

							@Override
							public Object getValue() {
								return value;
							}

							@Override
							public Object setValue(Object value) {
								throw new UnsupportedOperationException();
							}

						};
					}
				})));
	}

	@Override
	synchronized public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(simpleName(this.getClass())).append("[id=").append(id);
		for (Map.Entry<IThingKey<?>, ?> entry : SystemUtils.sortedByKey(properties.entrySet())) {
			sb.append(",").append(entry.getKey()).append("=").append(entry.getValue());
		}
		sb.append("]");
		return sb.toString();
	}
}
