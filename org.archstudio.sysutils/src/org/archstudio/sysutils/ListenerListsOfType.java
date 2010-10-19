package org.archstudio.sysutils;

import java.util.HashMap;
import java.util.Map;

public class ListenerListsOfType<T>
    extends ListenerList<T>{

	@SuppressWarnings("unchecked")
	private final Map<Class, ListenerList> classToListenerListMap = new HashMap<Class, ListenerList>();

	public ListenerListsOfType(Class<T> listenersClass){
		super(listenersClass);
	}

	public ListenerListsOfType(int mode, Class<T> listenersClass){
		super(mode, listenersClass);
	}

	@SuppressWarnings("unchecked")
	private void updateClassToListenerListMap(Object listener, boolean isAdding){
		for(Map.Entry<Class, ListenerList> entry: classToListenerListMap.entrySet()){
			Class instanceOf = entry.getKey();
			if(instanceOf.isAssignableFrom(listener.getClass())){
				ListenerList listenerList = entry.getValue();
				if(isAdding){
					listenerList.add(listener);
				}
				else{
					listenerList.remove(listener);
				}
			}
		}
	}

	@Override
	public synchronized boolean add(T listener){
		if(super.add(listener)){
			updateClassToListenerListMap(listener, true);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean remove(Object listener){
		if(super.remove(listener)){
			updateClassToListenerListMap(listener, false);
			return true;
		}
		return false;
	}

	@Override
	public synchronized void clear(){
		classToListenerListMap.clear();
		super.clear();
	}

	@SuppressWarnings("unchecked")
	public synchronized <S>S[] getListeners(Class<S> instanceOf){
		ListenerList listenerList = classToListenerListMap.get(instanceOf);
		if(listenerList == null){
			classToListenerListMap.put(instanceOf, listenerList = new ListenerList(identity ? IDENTITY : EQUALITY, instanceOf));
			for(T listener: getListeners()){
				if(instanceOf.isAssignableFrom(listener.getClass())){
					listenerList.add(listener);
				}
			}
		}
		return (S[])listenerList.getListeners();
	}
}
