package org.archstudio.sysutils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface TypedMap {

	public int size();

	boolean isEmpty();

	boolean containsKey(Object key);

	boolean containsValue(Object value);

	public <K extends TypedKey<V>, V> V get(K key);

	public <K extends TypedKey<V>, V> V put(K key, V value);

	public <K extends TypedKey<V>, V> V remove(K key);

	public void putAll(TypedMap m);

	public void clear();

	public Set<? extends TypedKey<?>> keySet();

	public Collection<?> values();

	public Set<? extends Map.Entry<? extends TypedKey<?>, ?>> entrySet();

	public boolean equals(Object o);

	public int hashCode();

	Map<? extends TypedKey<?>, ?> asMap();
}