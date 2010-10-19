package org.archstudio.util.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class ASCollections {

	private ASCollections() {
	};

	@SuppressWarnings("unchecked")
	public static final <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
		T[] array = (T[]) collection.toArray();
		Arrays.sort(array, comparator);
		return Arrays.asList(array);
	}

	public static final <T extends Comparable<T>> List<T> sort(Collection<T> collection) {
		return sort(Collections2.filter(collection, Predicates.notNull()), new Comparator<T>() {
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			};
		});
	}

	public static final <K extends Comparable<K>, V> List<Map.Entry<K, V>> sortByKey(Collection<Map.Entry<K, V>> entries) {
		return sort(Collections2.filter(entries, new Predicate<Map.Entry<K, V>>() {
			public boolean apply(Map.Entry<K, V> input) {
				return input.getKey() != null;
			};
		}), new Comparator<Map.Entry<K, V>>() {
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
	}


	public static final <K, V extends Comparable<V>> List<Map.Entry<K, V>> sortByValue(Collection<Map.Entry<K, V>> entries) {
		return sort(Collections2.filter(entries, new Predicate<Map.Entry<K, V>>() {
			public boolean apply(Map.Entry<K, V> input) {
				return input.getValue() != null;
			};
		}), new Comparator<Map.Entry<K, V>>() {
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
	}
}
