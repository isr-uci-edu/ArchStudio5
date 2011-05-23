package org.archstudio.myxgen;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxBrickLoader;
import org.archstudio.myx.fw.IMyxClassManager;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxBrickCreationException;
import org.archstudio.myx.fw.MyxBrickLoadFailedException;
import org.archstudio.myx.fw.MyxBrickNotFoundException;
import org.archstudio.myx.fw.MyxJavaClassBrickFactory;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.myxgen.extension.BrickExtension;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class MyxGenEclipseBrickLoader implements IMyxBrickLoader {

	private final IMyxBrickFactory factory = new IMyxBrickFactory() {
		@Override
		public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
				IMyxBrickInitializationData initializationData) throws MyxBrickCreationException {
			try {
				BrickExtension brickExtension = (BrickExtension) brickDescription;
				String bundleName = brickExtension.getPluginModel().getBundleDescription().getSymbolicName();
				Bundle bundle = Platform.getBundle(bundleName);
				String mainBrickClassName = brickExtension.getClassName();

				Class<?> c = bundle.loadClass(mainBrickClassName);
				return new MyxJavaClassBrickFactory(c).create(name, brickDescription, initializationData);
			}
			catch (Exception e) {
				throw new MyxBrickCreationException(e);
			}
		}
	};

	@Override
	public IMyxBrickFactory load(IMyxBrickDescription brickDescription) throws MyxBrickNotFoundException,
			MyxBrickLoadFailedException, MyxUnsupportedBrickDescriptionException {
		return brickDescription instanceof BrickExtension ? factory : null;
	}

	@Override
	public void setRuntime(IMyxRuntime runtime) {
	}

	@Override
	public void setClassManager(IMyxClassManager manager) {
	}
}
