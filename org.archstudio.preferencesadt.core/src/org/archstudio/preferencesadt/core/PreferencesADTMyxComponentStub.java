package org.archstudio.preferencesadt.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Preferences ADT Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class PreferencesADTMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx interface preferences: <code>IN_PREFERENCES</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_PREFERENCES = MyxUtils.createName("preferences");
	/**
	 * Myx interface propertyEvents: <code>OUT_PROPERTY_EVENTS</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_PROPERTY_EVENTS = MyxUtils.createName("propertyEvents");

	/**
	 * Service object(s) for preferences: <code>preferences</code>
	 * 
	 * @see #IN_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;
	/**
	 * Service object(s) for propertyEvents: <code>propertyEvents</code>
	 * 
	 * @see #OUT_PROPERTY_EVENTS
	 * @generated
	 */
	protected final Collection<org.eclipse.jface.util.IPropertyChangeListener> propertyEvents = new CopyOnWriteArrayList<org.eclipse.jface.util.IPropertyChangeListener>();

	/**
	 * Proxy to service objects for propertyEvents
	 *
	 * @see #OUT_PROPERTY_EVENTS
	 * @generated
	 */
	protected final org.eclipse.jface.util.IPropertyChangeListener propertyEventsProxy = (org.eclipse.jface.util.IPropertyChangeListener) Proxy
			.newProxyInstance(org.eclipse.jface.util.IPropertyChangeListener.class.getClassLoader(),
					new Class[] { org.eclipse.jface.util.IPropertyChangeListener.class }, new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.eclipse.jface.util.IPropertyChangeListener o : propertyEvents) {
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
	 * Returns the service object(s) for <code>preferences</code>
	 * 
	 * @see #IN_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		return preferences;
	}

	/**
	 * Returns the service object(s) for <code>propertyEvents</code>
	 *
	 * @see #OUT_PROPERTY_EVENTS
	 * @generated
	 */
	public Collection<org.eclipse.jface.util.IPropertyChangeListener> getPropertyEvents() {
		return propertyEvents;
	}

	/**
	 * Returns the proxy service object for the <code>propertyEvents</code> interface.
	 *
	 * @see #org.eclipse.jface.util.IPropertyChangeListener propertyEventsProxy
	 * @see #OUT_PROPERTY_EVENTS
	 * @generated
	 */
	public org.eclipse.jface.util.IPropertyChangeListener getPropertyEventsProxy() {
		return propertyEventsProxy;
	}

	/**
	 * Returns the service object(s) for <code>propertyEvents</code>
	 * 
	 * @see #OUT_PROPERTY_EVENTS
	 * @generated
	 */
	// TODO: Not sure what to do here

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_PROPERTY_EVENTS)) {
			propertyEvents.add((org.eclipse.jface.util.IPropertyChangeListener) serviceObject);
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
		if (interfaceName.equals(OUT_PROPERTY_EVENTS)) {
			propertyEvents.remove(serviceObject);
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
		if (interfaceName.equals(IN_PREFERENCES)) {
			if (preferences == null) {
				throw new NullPointerException("preferences");
			}
			return preferences;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}