package org.archstudio.myx.fw;

public class MyxBasicBrickItems implements IMyxBrickItems {

	protected IMyxName brickName;
	protected IMyxRequiredServiceProvider requiredServiceProvider;
	protected IMyxInterfaceManager interfaceManager;
	protected IMyxBrickDescription brickDescription;
	protected IMyxBrickInitializationData initializationData;

	public MyxBasicBrickItems(IMyxName brickName, IMyxRequiredServiceProvider requiredServiceProvider,
			IMyxInterfaceManager interfaceManager, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) {
		this.brickName = brickName;
		this.requiredServiceProvider = requiredServiceProvider;
		this.interfaceManager = interfaceManager;
		this.brickDescription = brickDescription;
		this.initializationData = initializationData;
	}

	public IMyxInterfaceManager getInterfaceManager() {
		return interfaceManager;
	}

	public void setInterfaceManager(IMyxInterfaceManager interfaceManager) {
		this.interfaceManager = interfaceManager;
	}

	public IMyxRequiredServiceProvider getRequiredServiceProvider() {
		return requiredServiceProvider;
	}

	public void setRequiredServiceProvider(IMyxRequiredServiceProvider requiredServiceProvider) {
		this.requiredServiceProvider = requiredServiceProvider;
	}

	public IMyxName getBrickName() {
		return brickName;
	}

	public void setBrickName(IMyxName brickName) {
		this.brickName = brickName;
	}

	public IMyxBrickDescription getBrickDescription() {
		return brickDescription;
	}

	public void setBrickDescription(IMyxBrickDescription brickDescription) {
		this.brickDescription = brickDescription;
	}

	public IMyxBrickInitializationData getInitializationData() {
		return initializationData;
	}

	public void setInitializationData(IMyxBrickInitializationData initializationData) {
		this.initializationData = initializationData;
	}
}
