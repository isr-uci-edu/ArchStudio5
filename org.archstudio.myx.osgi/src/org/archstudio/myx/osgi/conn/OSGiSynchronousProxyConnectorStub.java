package org.archstudio.myx.osgi.conn;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "OSGi Synch Proxy Connector"
 *
 * @generated
 */
/* package */@SuppressWarnings("unused")
abstract class OSGiSynchronousProxyConnectorStub extends org.archstudio.myx.java.conn.SynchronousProxyConnector
		implements org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}
}