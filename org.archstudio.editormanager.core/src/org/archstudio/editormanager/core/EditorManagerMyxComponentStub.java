package org.archstudio.editormanager.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Editor Manager Myx Component"
 * 
 * @generated
 */
abstract class EditorManagerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>editorManager</code> interface.
	 * 
	 * MyxGenInterface[name=editorManager,direction=in,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .editormanager.IEditorManager,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_EDITOR_MANAGER = MyxUtils.createName("editorManager");
	/**
	 * Myx name for the <code>focusEditorEvents</code> interface.
	 * 
	 * MyxGenInterface[name=focusEditorEvents,direction=out,single=true,
	 * serviceObjectDelegate
	 * =events,generateGetter=true,className=org.archstudio.
	 * editors.IFocusEditorListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FOCUS_EDITOR_EVENTS = MyxUtils.createName("focusEditorEvents");
	/**
	 * Myx name for the <code>preferences</code> interface.
	 * 
	 * MyxGenInterface[name=preferences,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.eclipse.jface
	 * .preference.IPreferenceStore,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils.createName("preferences");

	/**
	 * Service object(s) for the editorManager interface.
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for the focusEditorEvents interface.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected org.archstudio.eclipse.ui.IFocusEditorListener focusEditorEvents = null;
	/**
	 * Service object proxy for the focusEditorEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected final org.archstudio.eclipse.ui.IFocusEditorListener focusEditorEventsProxy = (org.archstudio.eclipse.ui.IFocusEditorListener) Proxy
			.newProxyInstance(org.archstudio.eclipse.ui.IFocusEditorListener.class.getClassLoader(),
					new Class[] { org.archstudio.eclipse.ui.IFocusEditorListener.class }, new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							org.archstudio.eclipse.ui.IFocusEditorListener o = focusEditorEvents;
							if (o != null) {
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
	 * Service object(s) for the preferences interface.
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;

	/**
	 * Returns the service object(s) for the <code>editorManager</code>
	 * interface.
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
	}

	/**
	 * Returns the service object(s) for the <code>focusEditorEvents</code>
	 * interface.
	 * 
	 * @see #OUT_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	public org.archstudio.eclipse.ui.IFocusEditorListener getFocusEditorEvents() {
		return focusEditorEvents;
	}

	/**
	 * Returns the service object(s) for the <code>preferences</code> interface.
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		return preferences;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_FOCUS_EDITOR_EVENTS)) {
			if (focusEditorEvents != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			focusEditorEvents = (org.archstudio.eclipse.ui.IFocusEditorListener) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
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
		if (interfaceName.equals(OUT_FOCUS_EDITOR_EVENTS)) {
			focusEditorEvents = null;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			preferences = null;
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
		if (interfaceName.equals(IN_EDITOR_MANAGER)) {
			if (editorManager == null) {
				throw new NullPointerException("editorManager");
			}
			return editorManager;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}