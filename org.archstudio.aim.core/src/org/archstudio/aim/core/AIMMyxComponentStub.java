package org.archstudio.aim.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "AIM Myx Component Impl"
 * 
 * @generated
 */
abstract class AIMMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	/**
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * @generated
	 */
	@Override
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * @generated
	 */
	@Override
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx interface aim: <code>IN_AIM</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_AIM = MyxUtils.createName("aim");
	/**
	 * Myx interface myxRuntime: <code>OUT_MYX_RUNTIME</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_MYX_RUNTIME = MyxUtils.createName("myxRuntime");
	/**
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");

	/**
	 * Service object(s) for aim: <code>aim</code>
	 * 
	 * @see #IN_AIM
	 * @generated
	 */
	protected org.archstudio.aim.IAIM aim = null;
	/**
	 * Service object(s) for myxRuntime: <code>myxRuntime</code>
	 * 
	 * @see #OUT_MYX_RUNTIME
	 * @generated
	 */
	protected org.archstudio.myx.fw.IMyxRuntime myxRuntime = null;
	/**
	 * Service object(s) for xarch: <code>xarch</code>
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;

	/**
	 * Returns the service object(s) for <code>aim</code>
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	public org.archstudio.aim.IAIM getAim() {
		return aim;
	}

	/**
	 * Returns the service object(s) for <code>myxRuntime</code>
	 * 
	 * @see #OUT_MYX_RUNTIME
	 * @generated
	 */
	public org.archstudio.myx.fw.IMyxRuntime getMyxRuntime() {
		return myxRuntime;
	}

	/**
	 * Returns the service object(s) for <code>xarch</code>
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_MYX_RUNTIME)) {
			if (myxRuntime != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			myxRuntime = (org.archstudio.myx.fw.IMyxRuntime) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
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
		if (interfaceName.equals(OUT_MYX_RUNTIME)) {
			myxRuntime = null;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
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
		if (interfaceName.equals(IN_AIM)) {
			if (aim == null) {
				throw new NullPointerException("aim");
			}
			return aim;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}