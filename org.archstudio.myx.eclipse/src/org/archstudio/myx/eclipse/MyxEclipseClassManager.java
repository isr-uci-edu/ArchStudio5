package org.archstudio.myx.eclipse;

import java.util.HashMap;
import java.util.Map;

import org.archstudio.myx.fw.IMyxClassManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * This is a Myx class manager that can load classes from Myx-Eclipse plugins.
 * This is necessary because otherwise various classes are not available to all
 * bricks when they are being created. For example, if an EventPumpConnector is
 * created that proxies some interface class declared in a plugin, the
 * EventPumpConnector and the Myx framework will have no way to access that
 * plugin. This iterates through available Myx Brick plugins and uses their
 * classloaders to load classes.
 */
public class MyxEclipseClassManager implements IMyxClassManager {

	private Map<String, Class<?>> cache = new HashMap<String, Class<?>>();

	@Override
	public Class<?> classForName(String className) throws ClassNotFoundException {
		Class<?> cachedClass = cache.get(className);
		if (cachedClass != null) {
			return cachedClass;
		}

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg != null) {
			// The Extension Registry can be null if we're running outside of Eclipse,
			// as happens in, e.g., org.archstudio.description.Main
			for (IConfigurationElement configurationElement : reg.getConfigurationElementsFor(MyxEclipseBrickLoader.BRICK_EXTENSION_POINT_ID)) {
				String brickClassName = configurationElement.getAttribute("class");
				if (brickClassName != null) {
					String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
					try {
						Class<?> foundClass = Platform.getBundle(bundleName).loadClass(className);
						cache.put(className, foundClass);
						return foundClass;
					}
					catch (ClassNotFoundException cnfe) {
					}
				}
			}
		}
		throw new ClassNotFoundException("MyxEclipseClassManager can't find class " + className);
	}
}
