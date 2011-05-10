package org.archstudio.myxgen.core;

import org.archstudio.eclipse.AbstractActivator;

public class Activator extends AbstractActivator {

	private static Activator singleton = null;

	public Activator() {
		singleton = this;
	}

	public static final Activator getSingleton() {
		return singleton;
	}
}
