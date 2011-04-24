package org.archstudio.myx.fw;

public interface IMyxBrickItems {
	public IMyxName getBrickName();

	public IMyxBrickDescription getBrickDescription();

	public IMyxBrickInitializationData getInitializationData();

	public IMyxRequiredServiceProvider getRequiredServiceProvider();

	public IMyxInterfaceManager getInterfaceManager();

	public IMyxClassManager getClassManager();
}
