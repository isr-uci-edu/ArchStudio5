package org.archstudio.xarchadt.variability.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "XArch ADT Variability Impl"
 * 
 * @generated
 */
/* package */@SuppressWarnings("unused")
abstract class XArchADTVariabilityMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=fileEvents,direction=out,single=false,serviceObjectDelegate=events,generateGetter=true,className=org.archstudio.xarchadt.IXArchADTFileListener,domain=top,description=null]
	public static final IMyxName OUT_FILE_EVENTS = MyxUtils.createName("fileEvents");
	/**
	 * Myx name for the <code>modelEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=modelEvents,direction=out,single=false,serviceObjectDelegate=events,generateGetter=true,className=org.archstudio.xarchadt.IXArchADTModelListener,domain=top,description=null]
	public static final IMyxName OUT_MODEL_EVENTS = MyxUtils.createName("modelEvents");
	/**
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=xarch,direction=in,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.xarchadt.IXArchADT,domain=top,description=null]
	public static final IMyxName IN_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx name for the <code>variabilityEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=variabilityEvents,direction=out,single=false,serviceObjectDelegate=events,generateGetter=true,className=org.archstudio.xarchadt.variability.IXArchADTVariabilityListener,domain=top,description=null]
	public static final IMyxName OUT_VARIABILITY_EVENTS = MyxUtils.createName("variabilityEvents");

	/**
	 * Service object(s) for the fileEvents interface.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.xarchadt.IXArchADTFileListener> fileEvents = new CopyOnWriteArrayList<org.archstudio.xarchadt.IXArchADTFileListener>();
	/**
	 * Service object proxy for the fileEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTFileListener fileEventsProxy = (org.archstudio.xarchadt.IXArchADTFileListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTFileListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTFileListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.xarchadt.IXArchADTFileListener o : fileEvents) {
								try {
									method.invoke(o, args);
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}
							return null;
						}
					});
	/**
	 * Service object(s) for the modelEvents interface.
	 * 
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.xarchadt.IXArchADTModelListener> modelEvents = new CopyOnWriteArrayList<org.archstudio.xarchadt.IXArchADTModelListener>();
	/**
	 * Service object proxy for the modelEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTModelListener modelEventsProxy = (org.archstudio.xarchadt.IXArchADTModelListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTModelListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTModelListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.xarchadt.IXArchADTModelListener o : modelEvents) {
								try {
									method.invoke(o, args);
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}
							return null;
						}
					});
	/**
	 * Service object(s) for the xarch interface.
	 * 
	 * @see #IN_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for the variabilityEvents interface.
	 * 
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.xarchadt.variability.IXArchADTVariabilityListener> variabilityEvents = new CopyOnWriteArrayList<org.archstudio.xarchadt.variability.IXArchADTVariabilityListener>();
	/**
	 * Service object proxy for the variabilityEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.variability.IXArchADTVariabilityListener variabilityEventsProxy = (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class },
					new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener o : variabilityEvents) {
								try {
									method.invoke(o, args);
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}
							return null;
						}
					});

	/**
	 * Returns the service object(s) for the <code>fileEvents</code> interface.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.xarchadt.IXArchADTFileListener> getFileEvents() {
		return fileEvents;
	}

	/**
	 * Returns the proxy service object for the <code>fileEvents</code>
	 * interface.
	 * 
	 * @see #org fileEventsProxy
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTFileListener getFileEventsProxy() {
		return fileEventsProxy;
	}

	/**
	 * Returns the service object(s) for the <code>modelEvents</code> interface.
	 * 
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.xarchadt.IXArchADTModelListener> getModelEvents() {
		return modelEvents;
	}

	/**
	 * Returns the proxy service object for the <code>modelEvents</code>
	 * interface.
	 * 
	 * @see #org modelEventsProxy
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEventsProxy() {
		return modelEventsProxy;
	}

	/**
	 * Returns the service object(s) for the <code>xarch</code> interface.
	 * 
	 * @see #IN_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * Returns the service object(s) for the <code>variabilityEvents</code>
	 * interface.
	 * 
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.xarchadt.variability.IXArchADTVariabilityListener> getVariabilityEvents() {
		return variabilityEvents;
	}

	/**
	 * Returns the proxy service object for the <code>variabilityEvents</code>
	 * interface.
	 * 
	 * @see #org variabilityEventsProxy
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.variability.IXArchADTVariabilityListener getVariabilityEventsProxy() {
		return variabilityEventsProxy;
	}

	/**
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_FILE_EVENTS)) {
			fileEvents.add((org.archstudio.xarchadt.IXArchADTFileListener) serviceObject);
			return;
		}
		if (interfaceName.equals(OUT_MODEL_EVENTS)) {
			modelEvents.add((org.archstudio.xarchadt.IXArchADTModelListener) serviceObject);
			return;
		}
		if (interfaceName.equals(OUT_VARIABILITY_EVENTS)) {
			variabilityEvents.add((org.archstudio.xarchadt.variability.IXArchADTVariabilityListener) serviceObject);
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
		if (interfaceName.equals(OUT_FILE_EVENTS)) {
			fileEvents.remove(serviceObject);
			return;
		}
		if (interfaceName.equals(OUT_MODEL_EVENTS)) {
			modelEvents.remove(serviceObject);
			return;
		}
		if (interfaceName.equals(OUT_VARIABILITY_EVENTS)) {
			variabilityEvents.remove(serviceObject);
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
		if (interfaceName.equals(IN_XARCH)) {
			if (xarch == null) {
				throw new NullPointerException("xarch");
			}
			return xarch;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}