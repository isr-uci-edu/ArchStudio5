package org.archstudio.sysutils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class FilterableCopyOnWriteArrayList<E> implements List<E> {

	public static final <E> FilterableCopyOnWriteArrayList<E> create() {
		return new FilterableCopyOnWriteArrayList<E>();
	}

	CopyOnWriteArrayList<E> list = new CopyOnWriteArrayList<E>();
	int listModCount = 0;
	LoadingCache<Class<?>, List<?>> filteredListsCache = CacheBuilder.newBuilder().build(
			new CacheLoader<Class<?>, List<?>>() {

				public List<?> load(Class<?> ofType) throws Exception {
					return new CopyOnWriteArrayList<Object>(Lists.newArrayList(Iterables.filter(list, ofType)));
				}
			});

	int filteredListModCount = -1;

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int indexOf(E e, int index) {
		return list.indexOf(e, index);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public int lastIndexOf(E e, int index) {
		return list.lastIndexOf(e, index);
	}

	public Object clone() {
		return list.clone();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public E get(int index) {
		return list.get(index);
	}

	public E set(int index, E element) {
		try {
			return list.set(index, element);
		}
		finally {
			listModCount++;
		}
	}

	public boolean add(E e) {
		try {
			return list.add(e);
		}
		finally {
			listModCount++;
		}
	}

	public void add(int index, E element) {
		try {
			list.add(index, element);
		}
		finally {
			listModCount++;
		}
	}

	public E remove(int index) {
		try {
			return list.remove(index);
		}
		finally {
			listModCount++;
		}
	}

	public boolean remove(Object o) {
		try {
			return list.remove(o);
		}
		finally {
			listModCount++;
		}
	}

	public boolean addIfAbsent(E e) {
		try {
			return list.addIfAbsent(e);
		}
		finally {
			listModCount++;
		}
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		try {
			return list.removeAll(c);
		}
		finally {
			listModCount++;
		}
	}

	public boolean retainAll(Collection<?> c) {
		try {
			return list.retainAll(c);
		}
		finally {
			listModCount++;
		}
	}

	public int addAllAbsent(Collection<? extends E> c) {
		try {
			return list.addAllAbsent(c);
		}
		finally {
			listModCount++;
		}
	}

	public void clear() {
		try {
			list.clear();
		}
		finally {
			listModCount++;
		}
	}

	public boolean addAll(Collection<? extends E> c) {
		try {
			return list.addAll(c);
		}
		finally {
			listModCount++;
		}
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		try {
			return list.addAll(index, c);
		}
		finally {
			listModCount++;
		}
	}

	public String toString() {
		return list.toString();
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public Iterator<E> iterator() {
		return list.iterator();
	}

	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public <T> Iterable<T> filter(Class<T> ofType) {
		if (filteredListModCount != listModCount) {
			filteredListModCount = listModCount;
			filteredListsCache.invalidateAll();
		}
		return (Iterable<T>) filteredListsCache.getUnchecked(ofType);
	}
}
