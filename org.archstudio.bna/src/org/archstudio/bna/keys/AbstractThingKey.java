package org.archstudio.bna.keys;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

@NonNullByDefault
abstract class AbstractThingKey<V> implements IThingKey<V> {

	private static Map<IThingKey<?>, IThingKey<?>> identityMap = Maps.newHashMap();

	@SuppressWarnings("unchecked")
	synchronized protected static <K extends IThingKey<?>> K identity(K key) {
		IThingKey<?> cached = identityMap.get(key);
		if (cached == null) {
			identityMap.put(key, cached = key);
		}
		return (K) cached;
	}

	private final Object id;
	private final int idHashCode;
	private final Function<V, V> cloneFunction;
	private final boolean nullable;

	protected AbstractThingKey(Object id, @Nullable Function<V, V> cloneFunction, boolean nullable) {
		this.id = id;
		this.idHashCode = this.id.hashCode();
		this.cloneFunction = cloneFunction;
		this.nullable = nullable;
	}

	@Override
	public int hashCode() {
		return idHashCode;
	}

	@SuppressWarnings("rawtypes")
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
		AbstractThingKey other = (AbstractThingKey) obj;
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

	@Override
	public @Nullable
	V clone(@Nullable V value) {
		return value == null ? null : cloneFunction != null ? cloneFunction.apply(value) : value;
	}

	@Override
	public boolean isNullable() {
		return nullable;
	}

	@Override
	public String toString() {
		return id.toString().replaceAll("[\\[\\]]", "");
	}

}