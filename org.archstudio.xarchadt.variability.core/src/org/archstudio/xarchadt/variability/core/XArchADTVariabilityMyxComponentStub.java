package org.archstudio.xarchadt.variability.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL
 * BE OVERWRITTEN.
 * 
 * To modify, update the "XArch ADT Variability Impl" MyxGen extension in the
 * org.archstudio.xarchadt.variability.core plugin.
 */

/**
 * Abstract Myx brick: XArch ADT Variability Impl
 * 
 * @generated
 */
public abstract class XArchADTVariabilityMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick
		implements org.archstudio.myx.fw.IMyxDynamicBrick {

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
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FILE_EVENTS = MyxUtils.createName("fileEvents");

	/**
	 * Service objects for the fileEvents interface.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	protected final java.util.Collection<org.archstudio.xarchadt.IXArchADTFileListener> fileEvents = new java.util.concurrent.CopyOnWriteArrayList<org.archstudio.xarchadt.IXArchADTFileListener>();

	/**
	 * Service object proxy for the fileEvents interface. Calls to this proxy
	 * object are automatically delegated to all connections on the interface
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTFileListener fileEventsProxy = (org.archstudio.xarchadt.IXArchADTFileListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTFileListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTFileListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
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
	 * Returns the service object(s) for the fileEvents interface.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTFileListener getFileEvents() {
		return fileEventsProxy;
	}

	/**
	 * Myx name for the <code>modelEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_MODEL_EVENTS = MyxUtils.createName("modelEvents");

	/**
	 * Service objects for the modelEvents interface.
	 * 
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	protected final java.util.Collection<org.archstudio.xarchadt.IXArchADTModelListener> modelEvents = new java.util.concurrent.CopyOnWriteArrayList<org.archstudio.xarchadt.IXArchADTModelListener>();

	/**
	 * Service object proxy for the modelEvents interface. Calls to this proxy
	 * object are automatically delegated to all connections on the interface
	 * 
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTModelListener modelEventsProxy = (org.archstudio.xarchadt.IXArchADTModelListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTModelListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTModelListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
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
	 * Returns the service object(s) for the modelEvents interface.
	 * 
	 * @see #OUT_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEvents() {
		return modelEventsProxy;
	}

	/**
	 * Myx name for the <code>variabilityEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_VARIABILITY_EVENTS = MyxUtils.createName("variabilityEvents");

	/**
	 * Service objects for the variabilityEvents interface.
	 * 
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final java.util.Collection<org.archstudio.xarchadt.variability.IXArchADTVariabilityListener> variabilityEvents = new java.util.concurrent.CopyOnWriteArrayList<org.archstudio.xarchadt.variability.IXArchADTVariabilityListener>();

	/**
	 * Service object proxy for the variabilityEvents interface. Calls to this
	 * proxy object are automatically delegated to all connections on the
	 * interface
	 * 
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.variability.IXArchADTVariabilityListener variabilityEventsProxy = (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
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
	 * Returns the service object(s) for the variabilityEvents interface.
	 * 
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.variability.IXArchADTVariabilityListener getVariabilityEvents() {
		return variabilityEventsProxy;
	}

	/**
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_XARCH = MyxUtils.createName("xarch");

	/**
	 * Service object for the xarch interface.
	 * 
	 * @see #IN_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;

	/**
	 * Returns the service object(s) for the xarch interface.
	 * 
	 * @see #IN_XARCH
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
	 * @see #IN_XARCH
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_XARCH)) {
			if (xarch == null) {
				throw new NullPointerException("Uninitialized service object: xarch");
			}
			return xarch;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @see #OUT_MODEL_EVENTS
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null)
			throw new NullPointerException(interfaceName.getName());

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

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_FILE_EVENTS
	 * @see #OUT_MODEL_EVENTS
	 * @see #OUT_VARIABILITY_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null)
			throw new NullPointerException(interfaceName.getName());

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
