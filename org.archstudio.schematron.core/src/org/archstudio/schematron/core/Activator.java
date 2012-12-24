package org.archstudio.schematron.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	static Activator plugin = null;

	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		plugin = this;
		super.start(context);
	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	public static Activator getDefault() {
		return plugin;
	}

}
