package org.archstudio.sysutils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface TypedMap {

	public static interface Key<V> {
	}

	public int size();

	boolean isEmpty();

	boolean containsKey(Object key);

	boolean containsValue(Object value);

	public <K extends Key<V>, V> V get(K key);

	public <K extends Key<V>, V> V put(K key, V value);

	public <K extends Key<V>, V> V remove(K key);

	public void putAll(TypedMap m);

	public void clear();

	public Set<? extends Key<?>> keySet();

	public Collection<?> values();

	public Set<? extends Map.Entry<? extends Key<?>, ?>> entrySet();

	public boolean equals(Object o);

	public int hashCode();

	Map<? extends Key<?>, ?> asMap();
}