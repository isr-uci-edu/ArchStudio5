package org.archstudio.utils.metadata;

import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.Maps;

public class IconUtils {

	private static final Map<Class<?>, URL> typeToIcon16URL = Maps.newHashMap();
	static {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg != null) {
			for (IConfigurationElement configurationElement : reg
					.getConfigurationElementsFor("org.archstudio.metadata.icon")) {
				String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
				String icon16Path = configurationElement.getAttribute("icon16");
				for (IConfigurationElement type : configurationElement.getChildren("Type")) {
					String className = type.getAttribute("class");
					try {
						Class<?> typeClass = Platform.getBundle(bundleName).loadClass(className);
						URL imageURL = Platform.getBundle(bundleName).getEntry(icon16Path);
						typeToIcon16URL.put(typeClass, imageURL);
					}
					catch (ClassNotFoundException cnfe) {
					}
				}
			}
		}
	}

	public static final URL getIconForType(Class<?> forClass) {
		return typeToIcon16URL.get(forClass);
	}

}
