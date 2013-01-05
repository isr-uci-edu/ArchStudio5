package org.archstudio.myx.java.conn;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Synch Event Pump Impl" MyxGen extension in the org.archstudio.myx.java plugin.
 */

/**
 * Abstract Myx brick: Synch Event Pump Impl
 * 
 * @generated
 */
public abstract class SynchEventPumpConnectorStub extends org.archstudio.myx.java.conn.EventPumpConnector implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * Myx name for the <code>synch</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_SYNCH = MyxUtils.createName("synch");

	/**
	 * Service objects for the synch interface.
	 * 
	 * @see #OUT_SYNCH
	 * @generated
	 */
	protected final java.util.Collection<java.lang.Object> synch = new java.util.concurrent.CopyOnWriteArrayList<java.lang.Object>();

	/**
	 * Returns the service object(s) for the synch interface.
	 * 
	 * @see #OUT_SYNCH
	 * @generated
	 */
	public java.util.Collection<java.lang.Object> getSynch() {
		return synch;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_SYNCH
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_SYNCH)) {
			synch.add((java.lang.Object) serviceObject);
			return;
		}

		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_SYNCH
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_SYNCH)) {
			synch.remove(serviceObject);
			return;
		}

		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}
}
