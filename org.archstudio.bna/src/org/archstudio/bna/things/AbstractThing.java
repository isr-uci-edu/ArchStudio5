package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkState;
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
	private final int hashCode;
	private final FastMap<IThingKey<?>, Object> properties = new FastMap<>(true);
	private final FastMap<IThingKey<?>, Object> shapeModifyingKeys = new FastMap<>(true);
	private boolean initialized;

	public AbstractThing(@Nullable Object id) {
		this.id = id != null ? id : new Object();
		hashCode = this.id.hashCode();
		initProperties();
		initialized = true;
	}

	@Override
	public final Object getID() {
		return id;
	}

	@Override
	public int hashCode() {
		return hashCode;
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
		else if (id != other.id && !id.equals(other.id)) {
			return false;
		}
		return true;
	}

	protected void initProperties() {
	}

	protected final <V> void initProperty(IThingKey<V> key, @Nullable V value) {
		checkState(!initialized, "Method may only be called from initProperties()");
		properties.put(key, value);
	}

	protected void addShapeModifyingKey(IThingKey<?> key) {
		checkState(!initialized, "Method may only be called from initProperties()");
		shapeModifyingKeys.put(key, null);
	}

	protected void removeShapeModifyingKey(IThingKey<?> key) {
		checkState(!initialized, "Method may only be called from initProperties()");
		shapeModifyingKeys.remove(key);
	}

	@Override
	public boolean isShapeModifyingKey(IThingKey<?> key) {
		return shapeModifyingKeys.containsKey(key);
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
		BNAUtils.checkLock();

		V value = getRaw(key);
		return value != null ? key.clone(value) : null;
	}

	@Override
	public final <V> V get(IThingKey<V> key, V valueIfNull) {
		BNAUtils.checkLock();

		V value = getRaw(key);
		return value != null ? key.clone(value) : valueIfNull;
	}

	@SuppressWarnings("unchecked")
	protected final @Nullable
	<V> V getRaw(IThingKey<V> key) {
		BNAUtils.checkLock();

		return (V) properties.get(key);
	}

	protected final <V> V getRaw(IThingKey<V> key, V valueIfNull) {
		BNAUtils.checkLock();

		V value = getRaw(key);
		return value != null ? value : valueIfNull;
	}

	@Override
	public final boolean has(IThingKey<?> key) {
		BNAUtils.checkLock();

		return properties.containsKey(key);
	}

	@Override
	public final <V> boolean has(IThingKey<V> key, @Nullable V value) {
		BNAUtils.checkLock();

		Map.Entry<IThingKey<?>, Object> entry = properties.getEntry(key);
		if (entry != null) {
			return BNAUtils.like(entry.getValue(), value);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <V> V set(IThingKey<V> key, @Nullable V value) {
		BNAUtils.checkLock();

		if (!key.isNullable() && value == null) {
			throw new NullPointerException(key.toString());
		}
		V oldValue = value;
		Map.Entry<IThingKey<?>, Object> entry = properties.createEntry(key);
		if (!BNAUtils.like(entry.getValue(), value)) {
			oldValue = (V) entry.getValue();
			entry.setValue(key.clone(value));
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, oldValue, value));
		}
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	protected final <V> V setRaw(IThingKey<V> key, @Nullable V value) {
		BNAUtils.checkLock();

		if (!key.isNullable() && value == null) {
			throw new NullPointerException(key.toString());
		}
		V oldValue = value;
		Map.Entry<IThingKey<?>, Object> entry = properties.createEntry(key);
		if (!BNAUtils.like(entry.getValue(), value)) {
			oldValue = (V) entry.getValue();
			entry.setValue(key.clone(value));
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_SET, this, key, oldValue, value));
		}
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final @Nullable
	<V> V remove(IThingKey<V> key) {
		BNAUtils.checkLock();

		V oldValue = null;
		Map.Entry<IThingKey<?>, Object> entry = properties.removeEntry(key);
		if (entry != null) {
			oldValue = (V) entry.getValue();
			fireThingEvent(ThingEvent.create(ThingEvent.EventType.PROPERTY_REMOVED, this, key, entry.getValue(), null));
		}
		return oldValue;
	}

	@Override
	public Set<Map.Entry<IThingKey<?>, ?>> entrySet() {
		BNAUtils.checkLock();

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

							@Override
							public String toString() {
								return key + " = " + value;
							}

						};
					}
				})));
	}

	@Override
	public String toString() {
		BNAUtils.checkLock();

		StringBuffer sb = new StringBuffer();
		sb.append(simpleName(this.getClass())).append("[id=").append(id);
		for (Map.Entry<IThingKey<?>, ?> entry : SystemUtils.sortedByKey(properties.entrySet())) {
			sb.append(",").append(entry.getKey()).append("=").append(entry.getValue());
		}
		sb.append("]");
		return sb.toString();
	}
}
