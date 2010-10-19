package org.archstudio.archipelago.core;

public interface IArchipelagoEventBus{

	public void addArchipelagoEventListener(IArchipelagoEventListener l);
	public void removeArchipelagoEventListener(IArchipelagoEventListener l);

	public void fireArchipelagoEvent(IArchipelagoEvent evt);
}