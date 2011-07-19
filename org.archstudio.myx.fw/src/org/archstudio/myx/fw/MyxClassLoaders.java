package org.archstudio.myx.fw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyxClassLoaders {

	private static Set<ClassLoader> classLoaderSet = Collections.synchronizedSet(new HashSet<ClassLoader>());

	private MyxClassLoaders() {
	}

	public static synchronized void addClassLoader(ClassLoader cl) {
		classLoaderSet.add(cl);
	}

	public static synchronized void removeClassLoader(ClassLoader cl) {
		classLoaderSet.remove(cl);
	}

	public static synchronized Collection<? extends ClassLoader> getClassLoaders() {
		return Collections.unmodifiableCollection(new ArrayList<ClassLoader>(classLoaderSet));
	}
}
