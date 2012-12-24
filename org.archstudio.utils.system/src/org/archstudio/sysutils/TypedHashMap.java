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

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K extends Key<V>, V> V get(K key) {
		return (V) map.get(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K extends Key<V>, V> V put(K key, V value) {
		return (V) map.put(key, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K extends Key<V>, V> V remove(K key) {
		return (V) map.remove(key);
	}

	@Override
	public void putAll(TypedMap m) {
		map.putAll(m.asMap());
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<? extends Key<?>> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<?> values() {
		return map.values();
	}

	@Override
	public Set<? extends Map.Entry<? extends Key<?>, ?>> entrySet() {
		return map.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return map.equals(o);
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public Map<? extends Key<?>, ?> asMap() {
		return map;
	}
}
