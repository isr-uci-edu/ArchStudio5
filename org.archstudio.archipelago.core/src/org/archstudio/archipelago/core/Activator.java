package org.archstudio.archipelago.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	private static Activator activator = null;

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		activator = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		activator = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return activator;
	}

}
