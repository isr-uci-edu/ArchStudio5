package org.archstudio.editormanager.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Editor Manager Myx Component" MyxGen extension in the org.archstudio.editormanager.core
 * plugin.
 */

/**
 * Abstract Myx brick: Editor Manager Myx Component
 * 
 * @generated
 */
public abstract class EditorManagerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

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
	@Override
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
	 * @generated
	 */
	@Override
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx name for the <code>editorManager</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_EDITOR_MANAGER = MyxUtils.createName("editorManager");

	/**
	 * Service object for the editorManager interface.
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;

	/**
	 * Returns the service object(s) for the editorManager interface.
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		if (editorManager == null) {
			throw new NullPointerException("Uninitialized service object: editorManager");
		}
		return editorManager;
	}

	/**
	 * Myx name for the <code>focusEditorEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FOCUS_EDITOR_EVENTS = MyxUtils.createName("focusEditorEvents");

	/**
	 * Service object for the focusEditorEvents interface.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected org.archstudio.eclipse.ui.IFocusEditorListener focusEditorEvents = null;

	/**
	 * Service object proxy for the focusEditorEvents interface. Calls to this proxy object are automatically delegated
	 * to all connections on the interface
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected final org.archstudio.eclipse.ui.IFocusEditorListener focusEditorEventsProxy = (org.archstudio.eclipse.ui.IFocusEditorListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.eclipse.ui.IFocusEditorListener.class.getClassLoader(),
					new Class[] { org.archstudio.eclipse.ui.IFocusEditorListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							org.archstudio.eclipse.ui.IFocusEditorListener o = focusEditorEvents;
							if (o == null) {
								throw new NullPointerException("focusEditorEvents");
							}
							else {
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
	 * Returns the service object(s) for the focusEditorEvents interface.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	public org.archstudio.eclipse.ui.IFocusEditorListener getFocusEditorEvents() {
		return focusEditorEventsProxy;
	}

	/**
	 * Myx name for the <code>preferences</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils.createName("preferences");

	/**
	 * Service object for the preferences interface.
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;

	/**
	 * Returns the service object(s) for the preferences interface.
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		if (preferences == null) {
			throw new NullPointerException("Uninitialized service object: preferences");
		}
		return preferences;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_EDITOR_MANAGER)) {
			if (editorManager == null) {
				throw new NullPointerException("Uninitialized service object: editorManager");
			}
			return editorManager;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_FOCUS_EDITOR_EVENTS)) {
			if (focusEditorEvents != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: focusEditorEvents");
			}
			focusEditorEvents = (org.archstudio.eclipse.ui.IFocusEditorListener) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: preferences");
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_FOCUS_EDITOR_EVENTS)) {
			if (focusEditorEvents == null) {
				throw new IllegalStateException("A connection was never made on interface: focusEditorEvents");
			}
			focusEditorEvents = null;
			return;
		}

		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences == null) {
				throw new IllegalStateException("A connection was never made on interface: preferences");
			}
			preferences = null;
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
