package org.archstudio.editors;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.launcher.ILaunchable;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.filemanager.IFileManager;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.resources.IResources;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.editormanager.IEditorManager;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.editors.IFocusEditorListener;

/**
 * Abstract Myx brick: "Abstract ArchStudio Editor Component Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class AbstractArchstudioEditorMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.launcher.ILaunchable,
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
	 * Myx interface resources: <code>OUT_RESOURCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils
			.createName("resources");
	/**
	 * Myx interface editorManager: <code>OUT_EDITOR_MANAGER</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_EDITOR_MANAGER = MyxUtils
			.createName("editorManager");
	/**
	 * Myx interface focusEditorEvents: <code>IN_FOCUS_EDITOR_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_FOCUS_EDITOR_EVENTS = MyxUtils
			.createName("focusEditorEvents");
	/**
	 * Myx interface fileManager: <code>OUT_FILE_MANAGER</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_FILE_MANAGER = MyxUtils
			.createName("fileManager");
	/**
	 * Myx interface fileManagerEvents: <code>IN_FILE_MANAGER_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_FILE_MANAGER_EVENTS = MyxUtils
			.createName("fileManagerEvents");
	/**
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx interface fileEvents: <code>IN_FILE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils
			.createName("fileEvents");
	/**
	 * Myx interface modelEvents: <code>IN_MODEL_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils
			.createName("modelEvents");
	/**
	 * Myx interface launcher: <code>IN_LAUNCHER</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_LAUNCHER = MyxUtils.createName("launcher");

	/**
	 * Service object(s) for resources: <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;
	/**
	 * Service object(s) for editorManager: <code>editorManager</code>
	 *
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for focusEditorEvents: <code>focusEditorEvents</code>
	 *
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected org.archstudio.editors.IFocusEditorListener focusEditorEvents = null;
	/**
	 * Service object(s) for fileManager: <code>fileManager</code>
	 *
	 * @see #OUT_FILE_MANAGER
	 * @generated
	 */
	protected org.archstudio.filemanager.IFileManager fileManager = null;
	/**
	 * Service object(s) for fileManagerEvents: <code>fileManagerEvents</code>
	 *
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected org.archstudio.filemanager.IFileManagerListener fileManagerEvents = null;
	/**
	 * Service object(s) for xarch: <code>xarch</code>
	 *
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for fileEvents: <code>fileEvents</code>
	 *
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADTFileListener fileEvents = null;
	/**
	 * Service object(s) for modelEvents: <code>modelEvents</code>
	 *
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADTModelListener modelEvents = null;

	/**
	 * Returns the service object(s) for <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		return resources;
	}

	/**
	 * Returns the service object(s) for <code>editorManager</code>
	 *
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
	}

	/**
	 * Returns the service object(s) for <code>focusEditorEvents</code>
	 *
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	public org.archstudio.editors.IFocusEditorListener getFocusEditorEvents() {
		return focusEditorEvents;
	}

	/**
	 * Returns the service object(s) for <code>fileManager</code>
	 *
	 * @see #OUT_FILE_MANAGER
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManager getFileManager() {
		return fileManager;
	}

	/**
	 * Returns the service object(s) for <code>fileManagerEvents</code>
	 *
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManagerListener getFileManagerEvents() {
		return fileManagerEvents;
	}

	/**
	 * Returns the service object(s) for <code>xarch</code>
	 *
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * Returns the service object(s) for <code>fileEvents</code>
	 *
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTFileListener getFileEvents() {
		return fileEvents;
	}

	/**
	 * Returns the service object(s) for <code>modelEvents</code>
	 *
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEvents() {
		return modelEvents;
	}

	/**
	 * Returns the service object(s) for <code>launcher</code>
	 *
	 * @see #IN_LAUNCHER
	 * @generated
	 */
	public org.archstudio.launcher.ILaunchable getLauncher() {
		return this;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			editorManager = (org.archstudio.editormanager.IEditorManager) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_FILE_MANAGER)) {
			if (fileManager != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			fileManager = (org.archstudio.filemanager.IFileManager) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
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
		if (interfaceName.equals(OUT_RESOURCES)) {
			resources = null;
			return;
		}
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			editorManager = null;
			return;
		}
		if (interfaceName.equals(OUT_FILE_MANAGER)) {
			fileManager = null;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
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
		if (interfaceName.equals(IN_FOCUS_EDITOR_EVENTS)) {
			if (focusEditorEvents == null) {
				throw new NullPointerException("focusEditorEvents");
			}
			return focusEditorEvents;
		}
		if (interfaceName.equals(IN_FILE_MANAGER_EVENTS)) {
			if (fileManagerEvents == null) {
				throw new NullPointerException("fileManagerEvents");
			}
			return fileManagerEvents;
		}
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			if (fileEvents == null) {
				throw new NullPointerException("fileEvents");
			}
			return fileEvents;
		}
		if (interfaceName.equals(IN_MODEL_EVENTS)) {
			if (modelEvents == null) {
				throw new NullPointerException("modelEvents");
			}
			return modelEvents;
		}
		if (interfaceName.equals(IN_LAUNCHER)) {
			return this;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}