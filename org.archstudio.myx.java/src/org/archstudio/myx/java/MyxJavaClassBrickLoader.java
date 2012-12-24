package org.archstudio.myx.java;

import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickLoader;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxBrickLoadFailedException;
import org.archstudio.myx.fw.MyxBrickNotFoundException;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;

public class MyxJavaClassBrickLoader implements IMyxBrickLoader {

	public MyxJavaClassBrickLoader() {
	}

	public void setRuntime(IMyxRuntime runtime) {
	}

	public IMyxBrickFactory load(IMyxBrickDescription brickDescription) throws MyxBrickNotFoundException,
			MyxBrickLoadFailedException, MyxUnsupportedBrickDescriptionException {
		return brickDescription instanceof MyxJavaClassBrickDescription ? new MyxJavaClassBrickFactory() : null;
	}

}
