package org.archstudio.graphlayout.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	static Activator plugin = null;

	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		Activator.plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		Activator.plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}
}
