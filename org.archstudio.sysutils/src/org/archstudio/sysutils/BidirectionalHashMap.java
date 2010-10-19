package org.archstudio.sysutils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BidirectionalHashMap<K, V>
	implements BidirectionalMap<K, V>{

	static final int DEFAULT_INITIAL_CAPACITY = 16;
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	final private Map<K, V> m;
	final private Map<V, K> rm;

	public BidirectionalHashMap(){
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public BidirectionalHashMap(int initialCapacity){
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	public BidirectionalHashMap(int initialCapacity, float loadFactor){
		m = new HashMap<K, V>(initialCapacity, loadFactor);
		rm = new HashMap<V, K>(initialCapacity, loadFactor);
	}

	public boolean containsKey(Object key){
		return m.containsKey(key);
	}

	public Set<Entry<K, V>> entrySet(){
		return m.entrySet();
	}

	public Collection<V> values(){
		return m.values();
	}

	public V get(Object key){
		return m.get(key);
	}

	public boolean isEmpty(){
		return m.isEmpty();
	}

	public Set<K> keySet(){
		return m.keySet();
	}

	public int size(){
		return m.size();
	}

	public void putAll(Map<? extends K, ? extends V> t){
		for(Map.Entry<? extends K, ? extends V> e: t.entrySet()){
			put(e.getKey(), e.getValue());
		}
	}

	public boolean containsValue(Object value){
		return rm.containsKey(value);
	}

	public K getKey(Object value){
		return rm.get(value);
	}

	public void clear(){
		m.clear();
		rm.clear();
	}

	public V put(K key, V value){
		K oldKey = rm.put(value, key);
		V oldValue = m.put(key, value);
		if(!key.equals(oldKey)){
			m.remove(oldKey);
		}
		if(!value.equals(oldValue)){
			rm.remove(oldValue);
		}
		return oldValue;
	}

	public V remove(Object key){
		V oldValue = m.remove(key);
		rm.remove(oldValue);
		return oldValue;
	}

	public K removeValue(Object value){
		K oldKey = rm.remove(value);
		m.remove(oldKey);
		return oldKey;
	}
}
