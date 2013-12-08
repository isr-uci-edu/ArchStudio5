package org.archstudio.xadl3;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class IconUtils {

	private static final Map<Class<?>, URL> typeToIcon16URL = new HashMap<>();
	static {
		if (Platform.isRunning()) {
			IExtensionRegistry reg = Platform.getExtensionRegistry();
			// The Extension Registry can be null if we're running outside of Eclipse
			for (IConfigurationElement configurationElement : reg
					.getConfigurationElementsFor("org.archstudio.xadl3.icon")) {
				String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
				String icon16Path = configurationElement.getAttribute("icon16");
				String typeName = configurationElement.getAttribute("type");
				try {
					Class<?> typeClass = Platform.getBundle(bundleName).loadClass(typeName);
					URL imageURL = Platform.getBundle(bundleName).getEntry(icon16Path);
					typeToIcon16URL.put(typeClass, imageURL);
				}
				catch (ClassNotFoundException cnfe) {
				}
			}
		}
	}

	public static final URL getIcon16URL(Class<?> forClass) {
		return typeToIcon16URL.get(forClass);
	}

}
