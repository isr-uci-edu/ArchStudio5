package org.archstudio.dblgen;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static final String toBundleID(Class<?> clazz) {
		String id = clazz.getName();
		int idEnd = id.lastIndexOf('.');
		return id.substring(0, idEnd);
	}

	public static final String BUNDLE_ID = toBundleID(Activator.class);

	private static BundleContext context;

	public static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
}
