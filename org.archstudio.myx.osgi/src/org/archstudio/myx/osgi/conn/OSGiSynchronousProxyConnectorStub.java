package org.archstudio.myx.osgi.conn;

import org.archstudio.myx.fw.IMyxName;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated.
 * ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the "OSGi Synch Proxy Connector" MyxGen 
 * extension in the org.archstudio.myx.osgi plugin.
 */

/**
 * Abstract Myx brick: OSGi Synch Proxy Connector
 * 
 * @generated
 */
public abstract class OSGiSynchronousProxyConnectorStub
extends org.archstudio.myx.java.conn.SynchronousProxyConnector
implements org.archstudio.myx.fw.IMyxDynamicBrick
{
	
	
	
	/**
	 * Returns service object(s) for IN interfaces.
	* @generated
	*/
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
	return super.getServiceObject(interfaceName);
	}

	/**
	* Update service objects based on connected OUT interfaces.
	* @generated
	*/
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null)
			throw new NullPointerException(interfaceName.getName());
	
	super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	* Update service objects based on disconnecting OUT interfaces.
	* @generated
	*/
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null)
			throw new NullPointerException(interfaceName.getName());
	
	super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	super.interfaceDisconnected(interfaceName, serviceObject);
	}
}
