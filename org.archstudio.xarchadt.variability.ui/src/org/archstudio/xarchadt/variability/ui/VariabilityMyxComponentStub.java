package org.archstudio.xarchadt.variability.ui;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Variability Myx Component"
 * 
 * @generated
 */
/* package */@SuppressWarnings("unused")
abstract class VariabilityMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=xarch,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.xarchadt.variability.IXArchADTVariability,domain=top,description=null]
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");

	/**
	 * Myx name for the <code>modelEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=modelEvents,direction=in,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.xarchadt.IXArchADTModelListener,domain=top,description=null]
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils.createName("modelEvents");

	/**
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=fileEvents,direction=in,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.xarchadt.IXArchADTFileListener,domain=top,description=null]
	public static final IMyxName IN_FILE_EVENTS = MyxUtils.createName("fileEvents");

	/**
	 * Myx name for the <code>variabilityEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=variabilityEvents,direction=in,single=true,serviceObjectDelegate=myxRegistry,generateGetter=true,className=org.archstudio.xarchadt.variability.IXArchADTVariabilityListener,domain=top,description=null]
	public static final IMyxName IN_VARIABILITY_EVENTS = MyxUtils.createName("variabilityEvents");

	/**
	 * Service object(s) for the xarch interface.
	 * 
	 * @see #IN_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.variability.IXArchADTVariability xarch = null;

	/**
	 * Service object proxy for the modelEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTModelListener modelEventsProxy = (org.archstudio.xarchadt.IXArchADTModelListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTModelListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTModelListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.xarchadt.IXArchADTModelListener o : myxRegistry.getObjects(
									VariabilityMyxComponentStub.this,
									org.archstudio.xarchadt.IXArchADTModelListener.class)) {
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
	 * Service object proxy for the fileEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTFileListener fileEventsProxy = (org.archstudio.xarchadt.IXArchADTFileListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTFileListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTFileListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.xarchadt.IXArchADTFileListener o : myxRegistry.getObjects(
									VariabilityMyxComponentStub.this,
									org.archstudio.xarchadt.IXArchADTFileListener.class)) {
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
	 * Service object proxy for the variabilityEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.variability.IXArchADTVariabilityListener variabilityEventsProxy = (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class },
					new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener o : myxRegistry
									.getObjects(VariabilityMyxComponentStub.this,
											org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class)) {
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
	 * Returns the service object(s) for the <code>xarch</code> interface.
	 * 
	 * @see #IN_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.variability.IXArchADTVariability getXarch() {
		return xarch;
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
			xarch = (org.archstudio.xarchadt.variability.IXArchADTVariability) serviceObject;
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
		if (interfaceName.equals(IN_MODEL_EVENTS)) {
			return modelEventsProxy;
		}
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			return fileEventsProxy;
		}
		if (interfaceName.equals(IN_VARIABILITY_EVENTS)) {
			return variabilityEventsProxy;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}