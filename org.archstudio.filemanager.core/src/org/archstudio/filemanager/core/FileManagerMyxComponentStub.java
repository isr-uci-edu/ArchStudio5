package org.archstudio.filemanager.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.filemanager.IFileManager;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;

/**
 * Abstract Myx brick: "File Manager Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class FileManagerMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.xarchadt.IXArchADTModelListener,
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.filemanager.IFileManager,
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
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx interface modelEvents: <code>IN_MODEL_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils
			.createName("modelEvents");
	/**
	 * Myx interface fileEvents: <code>IN_FILE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils
			.createName("fileEvents");
	/**
	 * Myx interface fileManager: <code>IN_FILE_MANAGER</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_FILE_MANAGER = MyxUtils
			.createName("fileManager");
	/**
	 * Myx interface fileManagerEvents: <code>OUT_FILE_MANAGER_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_FILE_MANAGER_EVENTS = MyxUtils
			.createName("fileManagerEvents");
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
	 * Service object(s) for fileManagerEvents: <code>fileManagerEvents</code>
	 *
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected org.archstudio.filemanager.IFileManagerListener fileManagerEvents = null;

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
	 * Returns the service object(s) for <code>modelEvents</code>
	 *
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEvents() {
		return this;
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
	 * Returns the service object(s) for <code>fileManager</code>
	 *
	 * @see #IN_FILE_MANAGER
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManager getFileManager() {
		return this;
	}

	/**
	 * Returns the service object(s) for <code>fileManagerEvents</code>
	 *
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManagerListener getFileManagerEvents() {
		return fileManagerEvents;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
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
		if (interfaceName.equals(OUT_FILE_MANAGER_EVENTS)) {
			if (fileManagerEvents != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			fileManagerEvents = (org.archstudio.filemanager.IFileManagerListener) serviceObject;
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
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
			return;
		}
		if (interfaceName.equals(OUT_FILE_MANAGER_EVENTS)) {
			fileManagerEvents = null;
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
		if (interfaceName.equals(IN_MODEL_EVENTS)) {
			return this;
		}
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			if (fileEvents == null) {
				throw new NullPointerException("fileEvents");
			}
			return fileEvents;
		}
		if (interfaceName.equals(IN_FILE_MANAGER)) {
			return this;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}