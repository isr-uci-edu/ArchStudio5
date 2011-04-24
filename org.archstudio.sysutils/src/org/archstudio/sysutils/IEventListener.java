package org.archstudio.sysutils;

public interface IEventListener<T>{

	public void handleEvent(T evt);
}