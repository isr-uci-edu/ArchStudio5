/*
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.archstudio.sysutils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;

/**
 * A modified version of a hash map originally authored by the attributed authors.
 * 
 * @author Justin Couch
 * @author Alex Chaffee (alex@apache.org)
 * @author Stephen Colebourne
 * @author Nathan Sweet
 */
@SuppressWarnings("unchecked")
public final class FastLongMap<V> {

	public static final <T> List<T> get(FastLongMap<List<T>> map, long key) {
		FastLongMap.Entry<List<T>> entry = map.getEntry(key);
		if (entry == null) {
			return Collections.emptyList();
		}
		return entry.getValue();
	}

	public static final <T> List<T> createList(FastLongMap<List<T>> map, long key) {
		FastLongMap.Entry<List<T>> entry = map.createEntry(key);
		if (entry.getValue() == null) {
			entry.setValue(Lists.<T> newArrayList());
		}
		return entry.getValue();
	}

	public static final class Entry<T> {
		private final long key;
		private T value;
		private Entry<T> next;

		Entry(long key, T value, Entry<T> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public long getKey() {
			return key;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (key ^ key >>> 32);
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
			Entry<?> other = (Entry<?>) obj;
			if (key != other.key) {
				return false;
			}
			return true;
		}

	}

	private Entry<V>[] table;
	private int size, mask, capacity, threshold;
	private float loadFactor;

	public FastLongMap() {
		this(16, 0.75f);
	}

	public FastLongMap(int initialCapacity) {
		this(initialCapacity, 0.75f);
	}

	public FastLongMap(int initialCapacity, float loadFactor) {
		if (initialCapacity > 1 << 30) {
			throw new IllegalArgumentException("initialCapacity is too large.");
		}
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("initialCapacity must be greater than zero.");
		}
		if (loadFactor <= 0) {
			throw new IllegalArgumentException("initialCapacity must be greater than zero.");
		}
		this.loadFactor = loadFactor;
		capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}
		this.threshold = (int) (capacity * loadFactor);
		this.table = new Entry[capacity];
		this.mask = capacity - 1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean containsKey(long key) {
		int index = (int) (key ^ key >>> 32) & mask;
		for (Entry<V> e = table[index]; e != null; e = e.next) {
			if (e.key == key) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(Object value) {
		Entry<V>[] table = this.table;
		for (int i = table.length - 1; i >= 0; i--) {
			for (Entry<V> e = table[i]; e != null; e = e.next) {
				if (e.value.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public V get(long key) {
		int index = (int) (key ^ key >>> 32) & mask;
		for (Entry<V> e = table[index]; e != null; e = e.next) {
			if (e.key == key) {
				return e.value;
			}
		}
		return null;
	}

	public V put(long key, V value) {
		Entry<V> entry = createEntry(key);
		V oldValue = entry.value;
		entry.value = value;
		return oldValue;
	}

	public Entry<V> createEntry(long key) {
		Entry<V> entry = getEntry(key);
		if (entry == null) {
			int index = (int) (key ^ key >>> 32) & mask;
			entry = table[index] = new Entry<V>(key, null, table[index]);
			if (size++ >= threshold) {
				rehash();
			}
		}
		return entry;
	}

	public Entry<V> getEntry(long key) {
		Entry<V>[] table = this.table;
		int index = (int) (key ^ key >>> 32) & mask;
		// Check if key already exists.
		for (Entry<V> entry = table[index]; entry != null; entry = entry.next) {
			if (entry.key != key) {
				continue;
			}
			return entry;
		}
		return null;
	}

	public Entry<V> removeEntry(long key) {
		int index = (int) (key ^ key >>> 32) & mask;
		Entry<V> prev = table[index];
		Entry<V> e = prev;
		while (e != null) {
			Entry<V> next = e.next;
			if (e.key == key) {
				size--;
				if (prev == e) {
					table[index] = next;
				}
				else {
					prev.next = next;
				}
				return e;
			}
			prev = e;
			e = next;
		}
		return null;
	}

	public V remove(long key) {
		Entry<V> entry = removeEntry(key);
		if (entry != null) {
			return entry.value;
		}
		return null;
	}

	private void rehash() {
		int newCapacity = 2 * capacity;
		Entry<V>[] newTable = new Entry[newCapacity];
		int newMask = newCapacity - 1;
		for (Entry<V> element : table) {
			Entry<V> e = element;
			if (e == null) {
				continue;
			}
			do {
				Entry<V> next = e.next;
				int index = (int) (e.key ^ e.key >>> 32) & newMask;
				e.next = newTable[index];
				newTable[index] = e;
				e = next;
			} while (e != null);
		}
		this.table = newTable;
		capacity = newCapacity;
		threshold = (int) (newCapacity * loadFactor);
		mask = capacity - 1;
	}

	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}

	public Iterable<Entry<V>> entriesAndClear() {
		Entry<V>[] oldTable = new Entry[table.length];
		System.arraycopy(table, 0, oldTable, 0, table.length);
		clear();
		return entries(oldTable);
	}

	public Iterable<Entry<V>> entries() {
		return entries(table);
	}

	private static <V> Iterable<Entry<V>> entries(final Entry<V>[] table) {
		return new Iterable<Entry<V>>() {
			@Override
			public Iterator<Entry<V>> iterator() {
				return new AbstractIterator<Entry<V>>() {

					int index = 0;
					Entry<V> entry = null;

					@Override
					protected Entry<V> computeNext() {
						if (entry != null) {
							entry = entry.next;
							if (entry != null) {
								return entry;
							}
						}
						while (index < table.length) {
							if ((entry = table[index++]) != null) {
								return entry;
							}
						}
						return endOfData();
					}
				};
			}
		};
	}
}
