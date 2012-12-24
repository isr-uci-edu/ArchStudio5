package org.archstudio.launcher.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Launcher Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class LauncherMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx interface results: <code>OUT_RESULTS</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESULTS = MyxUtils.createName("results");
	/**
	 * Myx interface launcher: <code>OUT_LAUNCHER</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_LAUNCHER = MyxUtils.createName("launcher");
	/**
	 * Myx interface resources: <code>OUT_RESOURCES</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils.createName("resources");

	/**
	 * Service object(s) for results: <code>results</code>
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	protected org.archstudio.myx.java.conn.IMultiwayResults results = null;
	/**
	 * Service object(s) for launcher: <code>launcher</code>
	 * 
	 * @see #OUT_LAUNCHER
	 * @generated
	 */
	protected org.archstudio.launcher.ILaunchable launcher = null;
	/**
	 * Service object(s) for resources: <code>resources</code>
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;

	/**
	 * Returns the service object(s) for <code>results</code>
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	public org.archstudio.myx.java.conn.IMultiwayResults getResults() {
		return results;
	}

	/**
	 * Returns the service object(s) for <code>launcher</code>
	 * 
	 * @see #OUT_LAUNCHER
	 * @generated
	 */
	public org.archstudio.launcher.ILaunchable getLauncher() {
		return launcher;
	}

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
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESULTS)) {
			if (results != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			results = (org.archstudio.myx.java.conn.IMultiwayResults) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_LAUNCHER)) {
			if (launcher != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			launcher = (org.archstudio.launcher.ILaunchable) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}
		throw new IllegalArgumentException("Unhandled interface connection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESULTS)) {
			results = null;
			return;
		}
		if (interfaceName.equals(OUT_LAUNCHER)) {
			launcher = null;
			return;
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			resources = null;
			return;
		}
		throw new IllegalArgumentException("Unhandled interface disconnection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	/**
	 * @generated
	 */

	public Object getServiceObject(IMyxName interfaceName) {
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}