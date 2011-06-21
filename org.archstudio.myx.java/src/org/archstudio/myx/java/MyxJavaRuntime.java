package org.archstudio.myx.java;

import org.archstudio.myx.fw.MyxBasicRuntime;
import org.archstudio.myx.fw.MyxBrickLoaderException;
import org.archstudio.myx.fw.MyxUtils;

public class MyxJavaRuntime extends MyxBasicRuntime {

	public MyxJavaRuntime() {
		try {
			addBrickLoader(MyxUtils.createName(MyxJavaClassBrickLoader.class.getName()), //
					MyxJavaClassBrickLoader.class, null);
		}
		catch (MyxBrickLoaderException mble) {
			throw new RuntimeException("This shouldn't happen.", mble);
		}
	}

}
