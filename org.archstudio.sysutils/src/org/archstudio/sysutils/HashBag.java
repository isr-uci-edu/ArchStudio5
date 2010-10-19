package org.archstudio.sysutils;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * An unordered collection that may contain duplicate elements.
 */
public class HashBag<E>
	extends AbstractCollection<E>{

	Map<E, int[]> s = new HashMap<E, int[]>();

	@Override
	public int size(){
		int size = 0;
		for(int[] v: s.values()){
			size += v[0];
		}
		return size;
	}

	@Override
	public boolean isEmpty(){
		return s.isEmpty();
	}

	@Override
	public void clear(){
		s.clear();
	}

	@SuppressWarnings("unchecked")
	private int[] get(Object e, boolean create){
		int[] count = s.get(e);
		if(count == null && create){
			s.put((E)e, count = new int[]{0});
		}
		return count;
	}

	@Override
	public Iterator<E> iterator(){
		return new Iterator<E>(){

			Iterator<Map.Entry<E, int[]>> i = s.entrySet().iterator();
			Map.Entry<E, int[]> e = i.hasNext() ? i.next() : null;
			int c = e != null ? e.getValue()[0] : 0;
			boolean removable = c > 0;

			public boolean hasNext(){
				return c > 0 || i.hasNext();
			}

			public E next(){
				if(c-- > 0){
					removable = true;
					return e.getKey();
				}
				if(i.hasNext()){
					e = i.next();
					c = e.getValue()[0];
					return next();
				}
				throw new NoSuchElementException();
			}

			public void remove(){
				if(removable){
					removable = false;
					if(--e.getValue()[0] == 0){
						i.remove();
					}
				}
				throw new IllegalStateException();
			}
		};
	}

	@Override
	public boolean add(E e){
		int[] count = get(e, true);
		count[0]++;
		return true;
	}

	@Override
	public boolean contains(Object o){
		int[] count = get(o, false);
		return count != null && count[0] > 0;
	}

	@Override
	public boolean remove(Object o){
		int[] count = get(o, false);
		if(count != null && count[0] > 0){
			if(--count[0] <= 0){
				s.remove(o);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c){
		return s.keySet().retainAll(c);
	}

	public Set<E> uniqueSet(){
		return s.keySet();
	}
	
	//	public static void main(String[] args){
	//		HashBag<String> b = new HashBag<String>();
	//		System.err.println(b + " " + b.size());
	//		b.add("A");
	//		System.err.println(b + " " + b.size());
	//		b.add("A");
	//		System.err.println(b + " " + b.size());
	//		b.add("B");
	//		System.err.println(b + " " + b.size());
	//		b.add("B");
	//		System.err.println(b + " " + b.size());
	//		b.add("B");
	//		System.err.println(b + " " + b.size());
	//		b.remove("A");
	//		System.err.println(b + " " + b.size());
	//		b.remove("A");
	//		System.err.println(b + " " + b.size());
	//		b.remove("A");
	//		System.err.println(b + " " + b.size());
	//		b.remove("B");
	//		System.err.println(b + " " + b.size());
	//		b.remove("B");
	//		System.err.println(b + " " + b.size());
	//		b.remove("B");
	//		System.err.println(b + " " + b.size());
	//		b.remove("B");
	//		System.err.println(b + " " + b.size());
	//	}
}