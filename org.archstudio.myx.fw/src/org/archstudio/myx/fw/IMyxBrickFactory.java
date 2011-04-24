package org.archstudio.myx.fw;

public interface IMyxBrickFactory {
	public IMyxBrick create(IMyxName name, IMyxBrickInitializationData initializationData) throws MyxBrickCreationException;
}
