package org.archstudio.sysutils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ASCollections {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final int compare(Object o1, Object o2) {
		if (o1 == null)
			return o2 != null ? -1 : 0;
		if (o2 == null)
			return 1;

		if (o1 instanceof Comparable)
			return ((Comparable) o1).compareTo(o2);

		return o1.toString().compareTo(o2.toString());
	}

	private static class NonNullMapEntryKeyPredicate implements Predicate<Map.Entry<?, ?>> {
		@Override
		public boolean apply(Map.Entry<?, ?> input) {
			return input.getKey() != null;
		}
	}

	private static class NonNullMapEntryValuePredicate implements Predicate<Map.Entry<?, ?>> {
		@Override
		public boolean apply(Map.Entry<?, ?> input) {
			return input.getValue() != null;
		}
	}

	private static class GenericComparator<T> implements Comparator<T> {
		public int compare(T o1, T o2) {
			return ASCollections.compare(o1, o2);
		};
	}

	private static class MapEntryKeyComparator<K> implements Comparator<Map.Entry<K, ?>> {
		@Override
		public int compare(Map.Entry<K, ?> o1, Map.Entry<K, ?> o2) {
			return ASCollections.compare(o1.getKey(), o2.getKey());
		}
	}

	private static class MapEntryValueComparator<V> implements Comparator<Map.Entry<?, V>> {
		@Override
		public int compare(Map.Entry<?, V> o1, Map.Entry<?, V> o2) {
			return ASCollections.compare(o1.getValue(), o2.getValue());
		}
	}

	@SuppressWarnings("rawtypes")
	private static final GenericComparator genericComparator = new GenericComparator();
	private static final NonNullMapEntryKeyPredicate nonNullMapEntryKeyPredicate = new NonNullMapEntryKeyPredicate();
	private static final NonNullMapEntryValuePredicate nonNullMapEntryValuePredicate = new NonNullMapEntryValuePredicate();
	@SuppressWarnings("rawtypes")
	private static final MapEntryKeyComparator mapEntryKeyComparator = new MapEntryKeyComparator();
	@SuppressWarnings("rawtypes")
	private static final MapEntryValueComparator mapEntryValueComparator = new MapEntryValueComparator();

	private ASCollections() {
	};

	public static final <T> Iterable<T> sort(Iterable<T> iterable, Comparator<? super T> comparator) {
		List<T> list = Lists.newArrayList(iterable);
		Collections.sort(list, comparator);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static final <T> Iterable<T> sort(Iterable<T> iterable) {
		return sort(Iterables.filter(iterable, Predicates.notNull()), genericComparator);
	}

	@SuppressWarnings("unchecked")
	public static final <K, V> Iterable<Map.Entry<K, V>> sortByKey(Iterable<Map.Entry<K, V>> entries) {
		return sort(Iterables.filter(entries, nonNullMapEntryKeyPredicate), mapEntryKeyComparator);
	}

	@SuppressWarnings("unchecked")
	public static final <K, V> Iterable<Map.Entry<K, V>> sortByValue(Iterable<Map.Entry<K, V>> entries) {
		return sort(Iterables.filter(entries, nonNullMapEntryValuePredicate), mapEntryValueComparator);
	}
}
