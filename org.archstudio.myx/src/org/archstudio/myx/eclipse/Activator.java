package org.archstudio.myx.eclipse;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	// The shared instance
	private static Activator plugin;

	BundleContext bundleContext;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		this.bundleContext = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		this.bundleContext = null;
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}
}
