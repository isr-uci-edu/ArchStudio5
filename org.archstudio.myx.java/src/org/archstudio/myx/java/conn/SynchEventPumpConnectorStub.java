package org.archstudio.myx.java.conn;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Synch Event Pump Impl"
 * 
 * @generated
 */

/* package */abstract class SynchEventPumpConnectorStub extends org.archstudio.myx.java.conn.EventPumpConnector
		implements org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	/**
	 * Myx name for the <code>synch</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=synch,direction=out,single=false,serviceObjectDelegate=variable,generateGetter=true,className=java.lang.Object,description=null]
	public static final IMyxName OUT_SYNCH = MyxUtils.createName("synch");

	/**
	 * Service object(s) for the synch interface.
	 * 
	 * @see #OUT_SYNCH
	 * @generated
	 */
	protected final Collection<java.lang.Object> synch = new CopyOnWriteArrayList<java.lang.Object>();

	/**
	 * Returns the service object(s) for the <code>synch</code> interface.
	 * 
	 * @see #OUT_SYNCH
	 * @generated
	 */
	public Collection<java.lang.Object> getSynch() {
		return synch;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_SYNCH)) {
			synch.add(serviceObject);
			return;
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
		if (interfaceName.equals(OUT_SYNCH)) {
			synch.remove(serviceObject);
			return;
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