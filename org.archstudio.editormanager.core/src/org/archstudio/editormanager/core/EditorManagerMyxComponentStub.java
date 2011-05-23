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
import org.archstudio.editors.IFocusEditorListener;

/**
 * Abstract Myx brick: "org.archstudio.editormanager.core.EditorManagerMyxComponent"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class EditorManagerMyxComponentStub extends
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
	 * Myx interface editorManager: <code>IN_EDITOR_MANAGER</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_EDITOR_MANAGER = MyxUtils
			.createName("editorManager");
	/**
	 * Myx interface focuseEditorEvents: <code>OUT_FOCUSE_EDITOR_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_FOCUSE_EDITOR_EVENTS = MyxUtils
			.createName("focuseEditorEvents");
	/**
	 * Myx interface preferences: <code>OUT_PREFERENCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils
			.createName("preferences");

	/**
	 * Service object(s) for editorManager: <code>editorManager</code>
	 *
	 * @see #IN_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for focuseEditorEvents: <code>focuseEditorEvents</code>
	 *
	 * @see #OUT_FOCUSE_EDITOR_EVENTS
	 * @generated
	 */
	protected org.archstudio.editors.IFocusEditorListener focuseEditorEvents = null;
	/**
	 * Service object(s) for preferences: <code>preferences</code>
	 *
	 * @see #OUT_PREFERENCES
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
	 * Returns the service object(s) for <code>focuseEditorEvents</code>
	 *
	 * @see #OUT_FOCUSE_EDITOR_EVENTS
	 * @generated
	 */
	public org.archstudio.editors.IFocusEditorListener getFocuseEditorEvents() {
		return focuseEditorEvents;
	}

	/**
	 * Returns the service object(s) for <code>preferences</code>
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
		if (interfaceName.equals(OUT_FOCUSE_EDITOR_EVENTS)) {
			if (focuseEditorEvents != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			focuseEditorEvents = (org.archstudio.editors.IFocusEditorListener) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
			return;
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
		if (interfaceName.equals(OUT_FOCUSE_EDITOR_EVENTS)) {
			focuseEditorEvents = null;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			preferences = null;
			return;
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
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}