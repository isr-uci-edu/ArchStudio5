package org.archstudio.myxgen.jet;

import org.archstudio.utils.osgi.AbstractActivator;

public class Activator extends AbstractActivator {

	private static Activator singleton;

	public Activator() {
		singleton = this;
	}

	public static final Activator getSingleton() {
		return singleton;
	}
}
