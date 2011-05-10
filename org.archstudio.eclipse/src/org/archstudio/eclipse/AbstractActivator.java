package org.archstudio.eclipse;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public abstract class AbstractActivator implements BundleActivator {

	private static final String toBundleID(Class<?> clazz) {
		String id = clazz.getName();
		int idEnd = id.lastIndexOf('.');
		return id.substring(0, idEnd);
	}

	private final String id;
	private BundleContext context = null;

	public AbstractActivator() {
		this.id = toBundleID(this.getClass());
	}

	public String getId() {
		return id;
	}

	public BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		this.context = null;
	}
}
