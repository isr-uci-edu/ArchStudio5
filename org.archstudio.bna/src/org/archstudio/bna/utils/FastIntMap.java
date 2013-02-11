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

package org.archstudio.bna.utils;

import java.util.Iterator;

/**
 * A hash map using primitive ints as keys rather than objects.
 * 
 * @author Justin Couch
 * @author Alex Chaffee (alex@apache.org)
 * @author Stephen Colebourne
 * @author Nathan Sweet
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class FastIntMap<V> implements Iterable<FastIntMap.Entry<V>> {
	Entry[] table;
	private final float loadFactor;
	private int size, mask, capacity, threshold;

	/**
	 * Same as: FastIntMap(16, 0.75f);
	 */
	public FastIntMap() {
		this(16, 0.75f);
	}

	/**
	 * Same as: FastIntMap(initialCapacity, 0.75f);
	 */
	public FastIntMap(int initialCapacity) {
		this(initialCapacity, 0.75f);
	}

	public FastIntMap(int initialCapacity, float loadFactor) {
		if (initialCapacity > 1 << 30) {
			throw new IllegalArgumentException("initialCapacity is too large.");
		}
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("initialCapacity must be greater than zero.");
		}
		if (loadFactor <= 0) {
			throw new IllegalArgumentException("initialCapacity must be greater than zero.");
		}
		capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}
		this.loadFactor = loadFactor;
		this.threshold = (int) (capacity * loadFactor);
		this.table = new Entry[capacity];
		this.mask = capacity - 1;
	}

	public V put(int key, V value) {
		Entry[] table = this.table;
		int index = key & mask;
		// Check if key already exists.
		for (Entry e = table[index]; e != null; e = e.next) {
			if (e.key != key) {
				continue;
			}
			Object oldValue = e.value;
			e.value = value;
			return (V) oldValue;
		}
		table[index] = new Entry(key, value, table[index]);
		if (size++ >= threshold) {
			rehash();
		}
		return null;
	}

	private void rehash() {
		int newCapacity = 2 * capacity;
		Entry[] newTable = new Entry[newCapacity];
		int newMask = newCapacity - 1;
		for (Entry element : table) {
			Entry e = element;
			if (e == null) {
				continue;
			}
			do {
				Entry next = e.next;
				int index = e.key & newMask;
				e.next = newTable[index];
				newTable[index] = e;
				e = next;
			} while (e != null);
		}
		this.table = newTable;
		capacity = newCapacity;
		threshold *= 2;
		mask = capacity - 1;
	}

	public V get(int key) {
		int index = key & mask;
		for (Entry e = table[index]; e != null; e = e.next) {
			if (e.key == key) {
				return (V) e.value;
			}
		}
		return null;
	}

	public boolean containsValue(Object value) {
		Entry[] table = this.table;
		for (int i = table.length - 1; i >= 0; i--) {
			for (Entry e = table[i]; e != null; e = e.next) {
				if (e.value.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsKey(int key) {
		int index = key & mask;
		for (Entry e = table[index]; e != null; e = e.next) {
			if (e.key == key) {
				return true;
			}
		}
		return false;
	}

	public V remove(int key) {
		int index = key & mask;
		Entry prev = table[index];
		Entry e = prev;
		while (e != null) {
			Entry next = e.next;
			if (e.key == key) {
				size--;
				if (prev == e) {
					table[index] = next;
				}
				else {
					prev.next = next;
				}
				return (V) e.value;
			}
			prev = e;
			e = next;
		}
		return null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		Entry[] table = this.table;
		for (int index = table.length - 1; index >= 0; index--) {
			table[index] = null;
		}
		size = 0;
	}

	@Override
	public EntryIterator iterator() {
		return new EntryIterator();
	}

	public class EntryIterator implements Iterator<Entry<V>> {
		private int nextIndex;
		private Entry<V> current;

		EntryIterator() {
			reset();
		}

		public void reset() {
			current = null;
			// Find first bucket.
			Entry[] table = FastIntMap.this.table;
			int i;
			for (i = table.length - 1; i >= 0; i--) {
				if (table[i] != null) {
					break;
				}
			}
			nextIndex = i;
		}

		@Override
		public boolean hasNext() {
			if (nextIndex >= 0) {
				return true;
			}
			Entry e = current;
			return e != null && e.next != null;
		}

		@Override
		public Entry<V> next() {
			// Next entry in current bucket.
			Entry e = current;
			if (e != null) {
				e = e.next;
				if (e != null) {
					current = e;
					return e;
				}
			}
			// Use the bucket at nextIndex and find the next nextIndex.
			Entry[] table = FastIntMap.this.table;
			int i = nextIndex;
			e = current = table[i];
			while (--i >= 0) {
				if (table[i] != null) {
					break;
				}
			}
			nextIndex = i;
			return e;
		}

		@Override
		public void remove() {
			FastIntMap.this.remove(current.key);
		}
	}

	static public class Entry<T> {
		final int key;
		T value;
		Entry next;

		Entry(int key, T value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public int getKey() {
			return key;
		}

		public T getValue() {
			return value;
		}
	}
}
