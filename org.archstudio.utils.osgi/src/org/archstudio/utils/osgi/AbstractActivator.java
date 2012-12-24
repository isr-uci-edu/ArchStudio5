package org.archstudio.utils.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

// Use the following for subclasses
/*
 * 
 * public class Activator extends AbstractActivator {
 * 
 * private static Activator singleton;
 * 
 * public Activator() { singleton = this; }
 * 
 * public static final Activator getSingleton() { return singleton; } }
 */

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

	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		this.context = null;
	}
}
