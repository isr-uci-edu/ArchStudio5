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
 * Abstract Myx brick: "org.archstudio.editormanager.core.EditorPrefsMyxComponent"
 * 
 * @generated
 */
@SuppressWarnings("unused")
public abstract class EditorPrefsMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor,
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
	 * Myx interface editorManager: <code>IN_EDITOR_MANAGER</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_EDITOR_MANAGER = MyxUtils
			.createName("editorManager");
	/**
	 * Myx interface preferences: <code>IN_PREFERENCES</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_PREFERENCES = MyxUtils
			.createName("preferences");

	/**
	 * Service object(s) for editorManager: <code>editorManager</code>
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for preferences: <code>preferences</code>
	 * 
	 * @see #IN_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;

	/**
	 * Returns the service object(s) for <code>editorManager</code>
	 * 
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
	}

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
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		throw new IllegalArgumentException("Unhandled interface connection: "
				+ interfaceName);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName,
			Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		throw new IllegalArgumentException(
				"Unhandled interface disconnection: " + interfaceName);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName,
			Object serviceObject) {
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
		if (interfaceName.equals(IN_PREFERENCES)) {
			if (preferences == null) {
				throw new NullPointerException("preferences");
			}
			return preferences;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}