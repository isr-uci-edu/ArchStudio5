package org.archstudio.rootpreferences.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.resources.IResources;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Root Preferences Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
abstract class RootPreferencesMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	/**
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * @generated
	 */
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * @generated
	 */
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx interface resources: <code>OUT_RESOURCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils.createName("resources");
	/**
	 * Myx interface preferences: <code>OUT_PREFERENCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils.createName("preferences");

	/**
	 * Service object(s) for resources: <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;
	/**
	 * Service object(s) for preferences: <code>preferences</code>
	 *
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;

	/**
	 * Returns the service object(s) for <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		return resources;
	}

	/**
	 * Returns the service object(s) for <code>preferences</code>
	 *
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		return preferences;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
			return;
		}
		throw new IllegalArgumentException("Unhandled interface connection: " + interfaceName);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			resources = null;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			preferences = null;
			return;
		}
		throw new IllegalArgumentException("Unhandled interface disconnection: " + interfaceName);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	/**
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}