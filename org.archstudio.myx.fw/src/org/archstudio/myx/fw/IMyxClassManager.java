package org.archstudio.myx.fw;

public interface IMyxClassManager {
	public Class<?> classForName(String className) throws ClassNotFoundException;
}
