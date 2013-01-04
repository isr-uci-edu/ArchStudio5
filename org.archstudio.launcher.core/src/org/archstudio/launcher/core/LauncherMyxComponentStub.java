package org.archstudio.launcher.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Launcher Impl" MyxGen extension in the org.archstudio.launcher.core plugin.
 */

/**
 * Abstract Myx brick: Launcher Impl
 * 
 * @generated
 */
public abstract class LauncherMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * The registry of objects for this brick.
	 * 
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * Register this brick instance with the registry.
	 * 
	 * @generated
	 */
	@Override
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
	 * @generated
	 */
	@Override
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx name for the <code>results</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESULTS = MyxUtils.createName("results");

	/**
	 * Service object for the results interface.
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	protected org.archstudio.myx.java.conn.IMultiwayResults results = null;

	/**
	 * Returns the service object(s) for the results interface.
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	public org.archstudio.myx.java.conn.IMultiwayResults getResults() {
		if (results == null) {
			throw new NullPointerException("Uninitialized service object: results");
		}
		return results;
	}

	/**
	 * Myx name for the <code>launcher</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_LAUNCHER = MyxUtils.createName("launcher");

	/**
	 * Service object for the launcher interface.
	 * 
	 * @see #OUT_LAUNCHER
	 * @generated
	 */
	protected org.archstudio.launcher.ILaunchable launcher = null;

	/**
	 * Returns the service object(s) for the launcher interface.
	 * 
	 * @see #OUT_LAUNCHER
	 * @generated
	 */
	public org.archstudio.launcher.ILaunchable getLauncher() {
		if (launcher == null) {
			throw new NullPointerException("Uninitialized service object: launcher");
		}
		return launcher;
	}

	/**
	 * Myx name for the <code>resources</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils.createName("resources");

	/**
	 * Service object for the resources interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;

	/**
	 * Returns the service object(s) for the resources interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		if (resources == null) {
			throw new NullPointerException("Uninitialized service object: resources");
		}
		return resources;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_RESULTS
	 * @see #OUT_LAUNCHER
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_RESULTS)) {
			if (results != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: results");
			}
			results = (org.archstudio.myx.java.conn.IMultiwayResults) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_LAUNCHER)) {
			if (launcher != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: launcher");
			}
			launcher = (org.archstudio.launcher.ILaunchable) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: resources");
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_RESULTS
	 * @see #OUT_LAUNCHER
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_RESULTS)) {
			if (results == null) {
				throw new IllegalStateException("A connection was never made on interface: results");
			}
			results = null;
			return;
		}

		if (interfaceName.equals(OUT_LAUNCHER)) {
			if (launcher == null) {
				throw new IllegalStateException("A connection was never made on interface: launcher");
			}
			launcher = null;
			return;
		}

		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources == null) {
				throw new IllegalStateException("A connection was never made on interface: resources");
			}
			resources = null;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}
}
