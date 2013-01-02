package org.archstudio.aim.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL
 * BE OVERWRITTEN.
 * 
 * To modify, update the "AIM Myx Component Impl" MyxGen extension in the
 * org.archstudio.aim.core plugin.
 */

/**
 * Abstract Myx brick: AIM Myx Component Impl
 * 
 * @generated
 */
public abstract class AIMMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
	 * @generated
	 */
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx name for the <code>aim</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_AIM = MyxUtils.createName("aim");

	/**
	 * Service object for the aim interface.
	 * 
	 * @see #IN_AIM
	 * @generated
	 */
	protected org.archstudio.aim.IAIM aim = null;

	/**
	 * Returns the service object(s) for the aim interface.
	 * 
	 * @see #IN_AIM
	 * @generated
	 */
	public org.archstudio.aim.IAIM getAim() {
		if (aim == null) {
			throw new NullPointerException("Uninitialized service object: aim");
		}
		return aim;
	}

	/**
	 * Myx name for the <code>myxRuntime</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_MYX_RUNTIME = MyxUtils.createName("myxRuntime");

	/**
	 * Service object for the myxRuntime interface.
	 * 
	 * @see #OUT_MYX_RUNTIME
	 * @generated
	 */
	protected org.archstudio.myx.fw.IMyxRuntime myxRuntime = null;

	/**
	 * Returns the service object(s) for the myxRuntime interface.
	 * 
	 * @see #OUT_MYX_RUNTIME
	 * @generated
	 */
	public org.archstudio.myx.fw.IMyxRuntime getMyxRuntime() {
		if (myxRuntime == null) {
			throw new NullPointerException("Uninitialized service object: myxRuntime");
		}
		return myxRuntime;
	}

	/**
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");

	/**
	 * Service object for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;

	/**
	 * Returns the service object(s) for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		if (xarch == null) {
			throw new NullPointerException("Uninitialized service object: xarch");
		}
		return xarch;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_AIM
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_AIM)) {
			if (aim == null) {
				throw new NullPointerException("Uninitialized service object: aim");
			}
			return aim;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_MYX_RUNTIME
	 * @see #OUT_XARCH
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_MYX_RUNTIME)) {
			if (myxRuntime != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: myxRuntime");
			}
			myxRuntime = (org.archstudio.myx.fw.IMyxRuntime) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: xarch");
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_MYX_RUNTIME
	 * @see #OUT_XARCH
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_MYX_RUNTIME)) {
			if (myxRuntime == null) {
				throw new IllegalStateException("A connection was never made on interface: myxRuntime");
			}
			myxRuntime = null;
			return;
		}

		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch == null) {
				throw new IllegalStateException("A connection was never made on interface: xarch");
			}
			xarch = null;
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
