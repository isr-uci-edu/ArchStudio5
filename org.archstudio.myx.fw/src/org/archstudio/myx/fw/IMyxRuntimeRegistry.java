package org.archstudio.myx.fw;

public interface IMyxRuntimeRegistry {
	public void addRuntime(String name, IMyxRuntime runtime);

	public void addLJMRuntime(String name, String host, int port);

	public void removeRuntime(String name);
}
