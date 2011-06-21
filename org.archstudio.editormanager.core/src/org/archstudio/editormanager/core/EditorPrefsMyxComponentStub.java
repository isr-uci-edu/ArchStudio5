package org.archstudio.editormanager.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.editormanager.IEditorManager;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Editor Prefs Myx Component"
 *
 * @generated
 */
@SuppressWarnings("unused")
abstract class EditorPrefsMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>editorManager</code> interface.
	 *
	 * MyxGenInterface[name=editorManager,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.editormanager.IEditorManager,description=null]
	 * @generated
	 */
	public static final IMyxName OUT_EDITOR_MANAGER = MyxUtils.createName("editorManager");
	/**
	 * Myx name for the <code>preferences</code> interface.
	 *
	 * MyxGenInterface[name=preferences,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.eclipse.jface.preference.IPreferenceStore,description=null]
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils.createName("preferences");

	/**
	 * Service object(s) for the editorManager interface.
	 *
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for the preferences interface.
	 *
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;

	/**
	 * Returns the service object(s) for the <code>editorManager</code> interface.
	 *
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
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
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			editorManager = (org.archstudio.editormanager.IEditorManager) serviceObject;
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
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			editorManager = null;
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
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}