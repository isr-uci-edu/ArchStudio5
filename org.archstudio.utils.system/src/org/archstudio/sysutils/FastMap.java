package org.archstudio.sysutils;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@SuppressWarnings("unchecked")
public final class FastMap<K, V> implements Map<K, V> {

	private static int[] powersOf2 = new int[31];
	static {
		for (int i = 0; i < powersOf2.length; i++) {
			powersOf2[i] = 1 << i;
		}
	}

	public static final <K, T> Collection<T> getCollection(FastMap<K, ? extends Collection<T>> map, Object key) {
		Entry<K, ?> entry = map.getEntry(key);
		if (entry == null) {
			return Collections.emptyList();
		}
		return (Collection<T>) entry.getValue();
	}

	public static final <K, T> Collection<T> removeCollection(FastMap<K, ? extends Collection<T>> map, Object key) {
		Entry<K, ?> entry = map.removeEntry(key);
		if (entry == null) {
			return Collections.emptyList();
		}
		return (Collection<T>) entry.getValue();
	}

	public static final <K, T> List<T> createList(FastMap<K, List<T>> map, K key) {
		Entry<K, List<T>> entry = map.createEntry(key);
		if (entry.getValue() == null) {
			entry.setValue(Lists.<T> newArrayListWithCapacity(4));
		}
		return entry.getValue();
	}

	public static final <K, T> Set<T> createSet(FastMap<K, Set<T>> map, K key) {
		Entry<K, Set<T>> entry = map.createEntry(key);
		if (entry.getValue() == null) {
			entry.setValue(Sets.<T> newHashSetWithExpectedSize(4));
		}
		return entry.getValue();
	}

	public static final class FastEntry<K, V> implements Entry<K, V> {
		private final K key;
		private FastEntry<K, V> next;
		private V value;

		FastEntry(K key, V value, FastEntry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = value;
			this.value = value;
			return oldValue;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (key == null ? 0 : key.hashCode());
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
			FastEntry<K, V> other = (FastEntry<K, V>) obj;
			if (key == null) {
				if (other.key != null) {
					return false;
				}
			}
			else if (!key.equals(other.key)) {
				return false;
			}
			return true;
		}

	}

	private class EntrySet extends AbstractSet<Entry<K, V>> {

		@Override
		public int size() {
			return size;
		}

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return entries(table).iterator();
		}

	}

	private class KeySet extends AbstractSet<K> {

		Function<Entry<K, V>, K> toKey = new Function<Entry<K, V>, K>() {
			@Override
			public K apply(Entry<K, V> input) {
				return input.getKey();
			}
		};

		@Override
		public int size() {
			return size;
		}

		@Override
		public Iterator<K> iterator() {
			return Iterators.transform(entries(table).iterator(), toKey);
		}

	}

	private class ValuesCollection extends AbstractCollection<V> {

		Function<Entry<K, V>, V> toValue = new Function<Entry<K, V>, V>() {
			@Override
			public V apply(Entry<K, V> input) {
				return input.getValue();
			}
		};

		@Override
		public int size() {
			return size;
		}

		@Override
		public Iterator<V> iterator() {
			return Iterators.transform(entries(table).iterator(), toValue);
		}
	}

	private FastEntry<K, V>[] table;
	private int size, capacity, threshold, mask;
	private final float loadFactor;
	private final EntrySet entrySet = new EntrySet();
	private final KeySet keySet = new KeySet();
	private final ValuesCollection valuesCollection = new ValuesCollection();
	private final boolean identity;

	public FastMap(boolean identity) {
		this(identity, 32, 0.75f);
	}

	public FastMap(boolean identity, int initialCapacity) {
		this(identity, initialCapacity, 0.75f);
	}

	public FastMap(boolean identity, int initialCapacity, float loadFactor) {
		checkArgument(initialCapacity > 0);
		checkArgument(loadFactor > 0);
		initialCapacity = SystemUtils.ceilB(initialCapacity / loadFactor) + 1;
		this.identity = identity;
		this.loadFactor = loadFactor;
		int capacityIndex = Arrays.binarySearch(powersOf2, Math.max(16, initialCapacity));
		if (capacityIndex < 0) {
			capacityIndex = -capacityIndex - 1;
		}
		capacity = powersOf2[capacityIndex];
		mask = capacity - 1;
		threshold = (int) (capacity * loadFactor);
		table = new FastEntry[capacity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private final int getIndex(Object key) {
		return key != null ? key.hashCode() & mask : 0;
	}

	public Entry<K, V> getEntry(Object key) {
		int index = getIndex(key);
		for (FastEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
			if (entry.key == key) {
				return entry;
			}
		}
		if (!identity) {
			for (FastEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
				if (SystemUtils.nullEquals(entry.key, key)) {
					return entry;
				}
			}
		}
		return null;
	}

	public Entry<K, V> getEntryByValue(Object value) {
		for (FastEntry<K, V> entry : table) {
			if (entry != null) {
				if (SystemUtils.nullEquals(entry.value, value)) {
					return entry;
				}
			}
		}
		return null;
	}

	public Entry<K, V> createEntry(K key) {
		int index = getIndex(key);
		for (FastEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
			if (entry.key == key) {
				return entry;
			}
		}
		if (!identity) {
			for (FastEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
				if (SystemUtils.nullEquals(entry.key, key)) {
					return entry;
				}
			}
		}
		if (++size > threshold) {
			rehash();
			index = getIndex(key);
		}
		return table[index] = new FastEntry<K, V>(key, null, table[index]);
	}

	public Entry<K, V> removeEntry(Object key) {
		int index = getIndex(key);
		FastEntry<K, V> previousEntry;
		previousEntry = null;
		for (FastEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
			if (entry.key == key) {
				if (previousEntry != null) {
					previousEntry.next = entry.next;
				}
				else {
					table[index] = entry.next;
				}
				return entry;
			}
			previousEntry = entry;
		}
		if (!identity) {
			previousEntry = null;
			for (FastEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
				if (SystemUtils.nullEquals(entry.key, key)) {
					if (previousEntry != null) {
						previousEntry.next = entry.next;
					}
					else {
						table[index] = entry.next;
					}
					return entry;
				}
				previousEntry = entry;
			}
		}
		return null;
	}

	private void rehash() {
		FastEntry<K, V>[] oldTable = this.table;
		capacity <<= 1;
		mask = capacity - 1;
		threshold = (int) (capacity * loadFactor);
		table = new FastEntry[capacity];
		for (FastEntry<K, V> entry : oldTable) {
			while (entry != null) {
				createEntry(entry.key).setValue(entry.value);
				entry = entry.next;
			}
		}
	}

	@Override
	public boolean containsKey(Object key) {
		return getEntry(key) != null;
	}

	@Override
	public boolean containsValue(Object value) {
		return getEntryByValue(value) != null;
	}

	@Override
	public V get(Object key) {
		Entry<K, V> entry = getEntry(key);
		return entry != null ? entry.getValue() : null;
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = createEntry(key);
		V oldValue = entry.getValue();
		entry.setValue(value);
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		Entry<K, V> entry = removeEntry(key);
		return entry != null ? entry.getValue() : null;
	}

	@Override
	public void clear() {
		table = new FastEntry[capacity];
		size = 0;
	}

	public Iterable<Entry<K, V>> entriesAndClear() {
		FastEntry<K, V>[] oldTable = table;
		table = new FastEntry[capacity];
		size = 0;
		return entries(oldTable);
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return entrySet;
	}

	@Override
	public Set<K> keySet() {
		return keySet;
	}

	@Override
	public Collection<V> values() {
		return valuesCollection;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	private static <K, V> Iterable<Entry<K, V>> entries(final FastEntry<K, V>[] table) {
		return new Iterable<Entry<K, V>>() {
			@Override
			public Iterator<Entry<K, V>> iterator() {
				return new AbstractIterator<Entry<K, V>>() {

					int index = 0;
					FastEntry<K, V> entry = null;

					@Override
					protected Entry<K, V> computeNext() {
						if (entry != null) {
							if ((entry = entry.next) != null) {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(SystemUtils.simpleName(getClass()));
		for (Map.Entry<K, V> entry : SystemUtils.sortedByKey(entrySet())) {
			sb.append("\n").append(entry.getKey()).append(" = ").append(entry.getValue());
		}
		return sb.toString();
	}
}
