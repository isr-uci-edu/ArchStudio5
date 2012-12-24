package org.archstudio.myx.osgi.conn;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;

/**
 * Abstract Myx brick: "OSGi Synch Event Pump Connector"
 * 
 * @generated
 */

/* package */abstract class OSGiSynchEventPumpConnectorStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick
		implements org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
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
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
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