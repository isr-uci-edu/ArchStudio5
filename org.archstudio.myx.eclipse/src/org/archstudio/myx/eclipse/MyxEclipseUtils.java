package org.archstudio.myx.eclipse;

import org.archstudio.myx.fw.MyxClassLoaders;

public class MyxEclipseUtils {
	
	private MyxEclipseUtils() {
	}

	public static void register() {
		MyxClassLoaders.addClassLoader(MyxEclipseUtils.class.getClassLoader());
	}
}
