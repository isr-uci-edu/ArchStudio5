package org.archstudio.sysutils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class TypedHashMap implements TypedMap {

	public static TypedHashMap create() {
		return new TypedHashMap();
	}

	public static TypedHashMap create(TypedMap map) {
		return new TypedHashMap(map);
	}

	public static TypedHashMap create(int expectedSize) {
		return new TypedHashMap(expectedSize);
	}

	private final Map<Key<? extends Object>, Object> map;

	protected TypedHashMap() {
		this.map = Maps.newHashMap();
	}

	protected TypedHashMap(TypedMap map) {
		this.map = Maps.newHashMap(map.asMap());
	}

	protected TypedHashMap(int expectedSize) {
		this.map = Maps.newHashMapWithExpectedSize(expectedSize);
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@SuppressWarnings("unchecked")
	public <K extends Key<V>, V> V get(K key) {
		return (V) map.get(key);
	}

	@SuppressWarnings("unchecked")
	public <K extends Key<V>, V> V put(K key, V value) {
		return (V) map.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <K extends Key<V>, V> V remove(K key) {
		return (V) map.remove(key);
	}

	public void putAll(TypedMap m) {
		map.putAll(m.asMap());
	}

	public void clear() {
		map.clear();
	}

	public Set<? extends Key<?>> keySet() {
		return map.keySet();
	}

	public Collection<?> values() {
		return map.values();
	}

	public Set<? extends Map.Entry<? extends Key<?>, ?>> entrySet() {
		return map.entrySet();
	}

	public boolean equals(Object o) {
		return map.equals(o);
	}

	public int hashCode() {
		return map.hashCode();
	}

	public Map<? extends Key<?>, ?> asMap() {
		return map;
	}
}
