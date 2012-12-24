package org.archstudio.myx.osgi;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxBrickCreationException;
import org.archstudio.myx.java.MyxJavaClassBrickFactory;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class MyxOSGiBrickFactory extends MyxJavaClassBrickFactory implements IMyxBrickFactory {

	public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickCreationException {
		MyxOSGiBrickDescription desc = (MyxOSGiBrickDescription) brickDescription;
		return create(name, brickDescription, initializationData, desc.getOsgiBundleName(),
				desc.getMainBrickClassName());
	}

	protected IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData, String bundleName, String mainBrickClassName)
			throws MyxBrickCreationException {
		Bundle bundle = Platform.getBundle(bundleName);
		if (bundle == null) {
			throw new MyxBrickCreationException("Can't find brick bundle: " + bundleName);
		}
		Class<?> mainBrickClass;
		try {
			mainBrickClass = bundle.loadClass(mainBrickClassName);
		}
		catch (Throwable e) {
			throw new MyxBrickCreationException("Can't find brick class " + mainBrickClassName + " in bundle "
					+ bundleName, e);
		}
		return create(name, brickDescription, initializationData, mainBrickClass);
	}
}
