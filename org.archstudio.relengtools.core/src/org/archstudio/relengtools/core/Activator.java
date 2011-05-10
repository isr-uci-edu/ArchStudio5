package org.archstudio.relengtools.core;

import org.archstudio.eclipse.AbstractActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractActivator {

	private static Activator singleton;

	public Activator() {
		singleton = this;
	}

	public static final Activator getSingleton() {
		return singleton;
	}
}
