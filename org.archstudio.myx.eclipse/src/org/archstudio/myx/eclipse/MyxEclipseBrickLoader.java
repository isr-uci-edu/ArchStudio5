package org.archstudio.myx.eclipse;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickLoader;
import org.archstudio.myx.fw.IMyxClassManager;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxBrickLoadFailedException;
import org.archstudio.myx.fw.MyxBrickNotFoundException;
import org.archstudio.myx.fw.MyxJavaClassBrickDescription;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;

public class MyxEclipseBrickLoader implements IMyxBrickLoader {

	public static final String BRICK_EXTENSION_POINT_ID = "org.archstudio.myx.eclipse.brick";

	public IMyxBrickFactory load(IMyxBrickDescription brickDescription) throws MyxBrickNotFoundException, MyxBrickLoadFailedException,
	        MyxUnsupportedBrickDescriptionException {
		if (!(brickDescription instanceof MyxJavaClassBrickDescription)) {
			throw new MyxUnsupportedBrickDescriptionException(getClass().getCanonicalName() + " can only load brick descriptions with type "
			        + MyxJavaClassBrickDescription.class.getCanonicalName());
		}
		MyxJavaClassBrickDescription javaBrickDescription = (MyxJavaClassBrickDescription) brickDescription;
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement extension : reg.getConfigurationElementsFor(BRICK_EXTENSION_POINT_ID)) {
			String extensionMainClass = extension.getAttribute("class");
			if ((extensionMainClass != null) && (extensionMainClass.equals(javaBrickDescription.getMainBrickClassName()))) {
				return new MyxEclipseBrickFactory(extension);
			}
		}
		throw new MyxBrickNotFoundException("Can't find brick to load as Eclipse Brick: " + javaBrickDescription.getMainBrickClassName());
	}

	public void setClassManager(IMyxClassManager manager) {
	}

	public void setRuntime(IMyxRuntime runtime) {
	}
}
