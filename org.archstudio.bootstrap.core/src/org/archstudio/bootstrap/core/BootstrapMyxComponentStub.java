package org.archstudio.bootstrap.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Bootstrap Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class BootstrapMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx interface aim: <code>OUT_AIM</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_AIM = MyxUtils.createName("aim");

	/**
	 * Service object(s) for xarch: <code>xarch</code>
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for aim: <code>aim</code>
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	protected org.archstudio.aim.IAIM aim = null;

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
	 * Returns the service object(s) for <code>aim</code>
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	public org.archstudio.aim.IAIM getAim() {
		return aim;
	}

	/**
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_AIM)) {
			if (aim != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			aim = (org.archstudio.aim.IAIM) serviceObject;
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
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
			return;
		}
		if (interfaceName.equals(OUT_AIM)) {
			aim = null;
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