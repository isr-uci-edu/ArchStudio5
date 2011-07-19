package org.archstudio.myx.fw;

public interface IMyxBrickFactory {
	public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickCreationException;
}
