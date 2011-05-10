package org.archstudio.sysutils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Iterables;

public class FilterableCopyOnWriteArrayList<E> implements Collection<E> {

	public static final <E> FilterableCopyOnWriteArrayList<E> create() {
		return new FilterableCopyOnWriteArrayList<E>();
	}

	CopyOnWriteArrayList<E> list = new CopyOnWriteArrayList<E>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
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

	@Override
	public Object clone() {
		return list.clone();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public E get(int index) {
		return list.get(index);
	}

	public E set(int index, E element) {
		return list.set(index, element);
	}

	@Override
	public boolean add(E e) {
		return list.add(e);
	}

	public void add(int index, E element) {
		list.add(index, element);
	}

	public E remove(int index) {
		return list.remove(index);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	public boolean addIfAbsent(E e) {
		return list.addIfAbsent(e);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public int addAllAbsent(Collection<? extends E> c) {
		return list.addAllAbsent(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return list.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		return list.addAll(index, c);
	}

	@Override
	public String toString() {
		return list.toString();
	}

	@Override
	public boolean equals(Object o) {
		return list.equals(o);
	}

	@Override
	public int hashCode() {
		return list.hashCode();
	}

	@Override
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
		return list.subList(fromIndex, toIndex);
	}

	public <T> Iterable<T> filter(Class<T> ofType) {
		// TODO: cache these results 
		return Iterables.filter(list, ofType);
	}
}
