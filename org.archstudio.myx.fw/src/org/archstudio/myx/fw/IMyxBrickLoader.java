package org.archstudio.myx.fw;

public interface IMyxBrickLoader {

	public IMyxBrickFactory load(IMyxBrickDescription brickDescription) throws MyxBrickNotFoundException, MyxBrickLoadFailedException,
	        MyxUnsupportedBrickDescriptionException;

	public void setRuntime(IMyxRuntime runtime);

	public void setClassManager(IMyxClassManager manager);
}
