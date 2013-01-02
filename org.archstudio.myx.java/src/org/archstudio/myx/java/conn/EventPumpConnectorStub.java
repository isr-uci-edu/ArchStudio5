package org.archstudio.myx.java.conn;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL
 * BE OVERWRITTEN.
 * 
 * To modify, update the "Event Pump Impl" MyxGen extension in the
 * org.archstudio.myx.java plugin.
 */

/**
 * Abstract Myx brick: Event Pump Impl
 * 
 * @generated
 */
public abstract class EventPumpConnectorStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>in</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_IN = MyxUtils.createName("in");

	/**
	 * Service object for the in interface.
	 * 
	 * @see #IN_IN
	 * @generated
	 */
	protected java.lang.Object in = null;

	/**
	 * Returns the service object(s) for the in interface.
	 * 
	 * @see #IN_IN
	 * @generated
	 */
	public java.lang.Object getIn() {
		if (in == null) {
			throw new NullPointerException("Uninitialized service object: in");
		}
		return in;
	}

	/**
	 * Myx name for the <code>out</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_OUT = MyxUtils.createName("out");

	/**
	 * Service objects for the out interface.
	 * 
	 * @see #OUT_OUT
	 * @generated
	 */
	protected final java.util.Collection<java.lang.Object> out = new java.util.concurrent.CopyOnWriteArrayList<java.lang.Object>();

	/**
	 * Returns the service object(s) for the out interface.
	 * 
	 * @see #OUT_OUT
	 * @generated
	 */
	public java.util.Collection<java.lang.Object> getOut() {
		return out;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_IN
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_IN)) {
			if (in == null) {
				throw new NullPointerException("Uninitialized service object: in");
			}
			return in;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_OUT
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_OUT)) {
			out.add((java.lang.Object) serviceObject);
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_OUT
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_OUT)) {
			out.remove(serviceObject);
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
