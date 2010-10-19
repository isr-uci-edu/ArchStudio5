/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html Contributors:
 * IBM Corporation - initial API and implementation
 ******************************************************************************/
/*******************************************************************************
 * This is a modified version of the original, which
 * <ul>
 * <li>supports generics</li>
 * <li>returns true/false indicating whether a listener was added/removed</li>
 * <li>implements Collection<T></li>
 * </ul>
 * Note: We should either use or backport the class in JDK 1.6 instead.
 ******************************************************************************/
package org.archstudio.sysutils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * <p>
 * This class is a thread safe list that is designed for storing lists of
 * listeners. The implementation is optimized for minimal memory footprint,
 * frequent reads and infrequent writes. Modification of the list is
 * synchronized and relatively expensive, while accessing the listeners is very
 * fast. Readers are given access to the underlying array data structure for
 * reading, with the trust that they will not modify the underlying array.
 * <p>
 * <a name="same">A listener list handles the <i>same</i> listener being added
 * multiple times, and tolerates removal of listeners that are the same as other
 * listeners in the list. For this purpose, listeners can be compared with each
 * other using either equality or identity, as specified in the list
 * constructor.
 * </p>
 * <p>
 * Use the <code>getListeners</code> method when notifying listeners. The
 * recommended code sequence for notifying all registered listeners of say,
 * <code>FooListener.eventHappened</code>, is:
 * 
 * <pre>
 * Object[] listeners = myListenerList.getListeners();
 * for(int i = 0; i &lt; listeners.length; ++i){
 * 	((FooListener)listeners[i]).eventHappened(event);
 * }
 * </pre>
 * 
 * </p>
 * <p>
 * This class can be used without OSGi running.
 * </p>
 * 
 * @since org.eclipse.equinox.common 3.2
 */
public class ListenerList<T>
    implements Collection<T>{

	/**
	 * The empty array singleton instance.
	 */
	private final T[] EmptyArray;

	/**
	 * Mode constant (value 0) indicating that listeners should be considered
	 * the <a href="#same">same</a> if they are equal.
	 */
	public static final int EQUALITY = 0;

	/**
	 * Mode constant (value 1) indicating that listeners should be considered
	 * the <a href="#same">same</a> if they are identical.
	 */
	public static final int IDENTITY = 1;

	/**
	 * Indicates the comparison mode used to determine if two listeners are
	 * equivalent
	 */
	protected final boolean identity;

	private final Class<? extends T> listenersClass;

	/**
	 * The list of listeners. Initially empty but initialized to an array of
	 * size capacity the first time a listener is added. Maintains invariant:
	 * listeners != null
	 */
	private volatile T[] listeners;

	/**
	 * Creates a listener list in which listeners are compared using equality.
	 */
	public ListenerList(Class<T> listenersClass){
		this(EQUALITY, listenersClass);
	}

	/**
	 * Creates a listener list using the provided comparison mode.
	 * 
	 * @param mode
	 *            The mode used to determine if listeners are the <a
	 *            href="#same">same</a>.
	 */
	@SuppressWarnings("unchecked")
	public ListenerList(int mode, Class<T> listenersClass){
		if(mode != EQUALITY && mode != IDENTITY){
			throw new IllegalArgumentException();
		}
		this.identity = mode == IDENTITY;
		this.listenersClass = listenersClass;
		this.EmptyArray = (T[])Array.newInstance(this.listenersClass, 0);
		this.listeners = EmptyArray;
	}

	/**
	 * Adds a listener to this list. This method has no effect if the <a
	 * href="#same">same</a> listener is already registered.
	 * 
	 * @param listener
	 *            the non-<code>null</code> listener to add
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean add(T listener){
		// This method is synchronized to protect against multiple threads adding 
		// or removing listeners concurrently. This does not block concurrent readers.
		if(listener == null){
			throw new IllegalArgumentException();
		}
		// check for duplicates 
		final int oldSize = listeners.length;
		for(int i = 0; i < oldSize; ++i){
			Object listener2 = listeners[i];
			if(identity ? listener == listener2 : listener.equals(listener2)){
				return false;
			}
		}
		// Thread safety: create new array to avoid affecting concurrent readers
		T[] newListeners = (T[])Array.newInstance(listenersClass, oldSize + 1);
		System.arraycopy(listeners, 0, newListeners, 0, oldSize);
		newListeners[oldSize] = listener;
		//atomic assignment
		this.listeners = newListeners;
		return true;
	}

	/**
	 * Returns an array containing all the registered listeners. The resulting
	 * array is unaffected by subsequent adds or removes. If there are no
	 * listeners registered, the result is an empty array. Use this method when
	 * notifying listeners, so that any modifications to the listener list
	 * during the notification will have no effect on the notification itself.
	 * <p>
	 * Note: Callers of this method <b>must not</b> modify the returned array.
	 * 
	 * @deprecated treat as Collection
	 * @return the list of registered listeners
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public T[] getListeners(){
		return listeners;
	}

	/**
	 * Returns whether this listener list is empty.
	 * 
	 * @return <code>true</code> if there are no registered listeners, and
	 *         <code>false</code> otherwise
	 */
	public boolean isEmpty(){
		return listeners.length == 0;
	}

	/**
	 * Removes a listener from this list. Has no effect if the <a
	 * href="#same">same</a> listener was not already registered.
	 * 
	 * @param listener
	 *            the non-<code>null</code> listener to remove
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean remove(Object listener){
		// This method is synchronized to protect against multiple threads adding 
		// or removing listeners concurrently. This does not block concurrent readers.
		if(listener == null){
			throw new IllegalArgumentException();
		}
		int oldSize = listeners.length;
		for(int i = 0; i < oldSize; ++i){
			Object listener2 = listeners[i];
			if(identity ? listener == listener2 : listener.equals(listener2)){
				if(oldSize == 1){
					listeners = EmptyArray;
				}
				else{
					// Thread safety: create new array to avoid affecting concurrent readers
					T[] newListeners = (T[])Array.newInstance(listenersClass, oldSize - 1);
					System.arraycopy(listeners, 0, newListeners, 0, i);
					System.arraycopy(listeners, i + 1, newListeners, i, oldSize - i - 1);
					//atomic assignment to field
					this.listeners = newListeners;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the number of registered listeners.
	 * 
	 * @return the number of registered listeners
	 */
	public int size(){
		return listeners.length;
	}

	public synchronized void clear(){
		listeners = EmptyArray;
	}

	public boolean addAll(Collection<? extends T> arg0){
		boolean modified = false;
		for(T t: arg0){
			modified = add(t) || modified;
		}
		return modified;
	}

	public boolean contains(Object arg0){
		for(T listener: listeners){
			if(identity ? listener == arg0 : listener.equals(arg0)){
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0){
		for(Object t: arg0){
			if(!contains(t)){
				return false;
			}
		}
		return true;
	}

	public Iterator<T> iterator(){
		return new Iterator<T>(){

			final T[] ts = listeners;
			int i = 0;
			T last = null;

			public boolean hasNext(){
				return i < listeners.length;
			}

			public T next(){
				return last = ts[i++];
			}

			public void remove(){
				ListenerList.this.remove(last);
			}
		};
	}

	public boolean removeAll(Collection<?> arg0){
		boolean modified = false;
		for(Object t: arg0){
			modified = remove(t) || modified;
		}
		return modified;
	}

	public boolean retainAll(Collection<?> arg0){
		boolean modified = false;
		for(T t: listeners){
			if(arg0.contains(t)){
				continue;
			}
			modified = remove(t) || modified;
		}
		return modified;
	}

	public Object[] toArray(){
		return listeners;
	}

	public <T>T[] toArray(T[] arg0){
		return Arrays.asList(listeners).toArray(arg0);
	}
}
