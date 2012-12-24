package org.archstudio.myx.java.conn;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Event Pump Impl"
 * 
 * @generated
 */

/* package */abstract class EventPumpConnectorStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>in</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=in,direction=in,single=true,serviceObjectDelegate=variable,generateGetter=true,className=java.lang.Object,description=null]
	public static final IMyxName IN_IN = MyxUtils.createName("in");
	/**
	 * Myx name for the <code>out</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=out,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=java.lang.Object,description=null]
	public static final IMyxName OUT_OUT = MyxUtils.createName("out");

	/**
	 * Service object(s) for the in interface.
	 * 
	 * @see #IN_IN
	 * @generated
	 */
	protected java.lang.Object in = null;
	/**
	 * Service object(s) for the out interface.
	 * 
	 * @see #OUT_OUT
	 * @generated
	 */
	protected final Collection<java.lang.Object> out = new CopyOnWriteArrayList<java.lang.Object>();

	/**
	 * Returns the service object(s) for the <code>in</code> interface.
	 * 
	 * @see #IN_IN
	 * @generated
	 */
	public java.lang.Object getIn() {
		return in;
	}

	/**
	 * Returns the service object(s) for the <code>out</code> interface.
	 * 
	 * @see #OUT_OUT
	 * @generated
	 */
	public Collection<java.lang.Object> getOut() {
		return out;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_OUT)) {
			out.add(serviceObject);
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
		if (interfaceName.equals(OUT_OUT)) {
			out.remove(serviceObject);
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
		if (interfaceName.equals(IN_IN)) {
			if (in == null) {
				throw new NullPointerException("in");
			}
			return in;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}