package org.archstudio.archipelago.core;

import java.util.ArrayList;
import java.util.List;

public class DefaultArchipelagoEventBus implements IArchipelagoEventBus{
	public List<IArchipelagoEventListener> listenerList = new ArrayList<IArchipelagoEventListener>();
	public IArchipelagoEventListener[] listeners = new IArchipelagoEventListener[0];
	
	public synchronized void addArchipelagoEventListener(IArchipelagoEventListener l){
		listenerList.add(l);
		listeners = listenerList.toArray(new IArchipelagoEventListener[listenerList.size()]);
	}
	
	public synchronized void removeArchipelagoEventListener(IArchipelagoEventListener l){
		listenerList.remove(l);
		listeners = listenerList.toArray(new IArchipelagoEventListener[listenerList.size()]);
	}
	
	public void fireArchipelagoEvent(IArchipelagoEvent evt){
		IArchipelagoEventListener[] listeners = this.listeners;
		for(int i = 0; i < listeners.length; i++){
			listeners[i].handleArchipelagoEvent(evt);
		}
	}
	
}
