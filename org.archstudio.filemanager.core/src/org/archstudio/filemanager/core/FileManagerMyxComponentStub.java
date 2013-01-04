package org.archstudio.filemanager.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "File Manager Impl" MyxGen extension in the org.archstudio.filemanager.core plugin.
 */

/**
 * Abstract Myx brick: File Manager Impl
 * 
 * @generated
 */
public abstract class FileManagerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.xarchadt.IXArchADTModelListener,
		org.archstudio.xarchadt.IXArchADTFileListener, org.archstudio.filemanager.IFileManager {

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
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");

	/**
	 * Service object for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;

	/**
	 * Returns the service object(s) for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		if (xarch == null) {
			throw new NullPointerException("Uninitialized service object: xarch");
		}
		return xarch;
	}

	/**
	 * Myx name for the <code>modelEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils.createName("modelEvents");

	/**
	 * Returns the service object(s) for the modelEvents interface.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEvents() {
		return this;
	}

	/**
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils.createName("fileEvents");

	/**
	 * Returns the service object(s) for the fileEvents interface.
	 * 
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTFileListener getFileEvents() {
		return this;
	}

	/**
	 * Myx name for the <code>fileManager</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_MANAGER = MyxUtils.createName("fileManager");

	/**
	 * Returns the service object(s) for the fileManager interface.
	 * 
	 * @see #IN_FILE_MANAGER
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManager getFileManager() {
		return this;
	}

	/**
	 * Myx name for the <code>fileManagerEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FILE_MANAGER_EVENTS = MyxUtils.createName("fileManagerEvents");

	/**
	 * Service objects for the fileManagerEvents interface.
	 * 
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected final java.util.Collection<org.archstudio.filemanager.IFileManagerListener> fileManagerEvents = new java.util.concurrent.CopyOnWriteArrayList<org.archstudio.filemanager.IFileManagerListener>();

	/**
	 * Service object proxy for the fileManagerEvents interface. Calls to this proxy object are automatically delegated
	 * to all connections on the interface
	 * 
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected final org.archstudio.filemanager.IFileManagerListener fileManagerEventsProxy = (org.archstudio.filemanager.IFileManagerListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.filemanager.IFileManagerListener.class.getClassLoader(),
					new Class[] { org.archstudio.filemanager.IFileManagerListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.filemanager.IFileManagerListener o : fileManagerEvents) {
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
	 * Returns the service object(s) for the fileManagerEvents interface.
	 * 
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManagerListener getFileManagerEvents() {
		return fileManagerEventsProxy;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @see #IN_FILE_EVENTS
	 * @see #IN_FILE_MANAGER
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_MODEL_EVENTS)) {
			return this;
		}
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			return this;
		}
		if (interfaceName.equals(IN_FILE_MANAGER)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_XARCH
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: xarch");
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_FILE_MANAGER_EVENTS)) {
			fileManagerEvents.add((org.archstudio.filemanager.IFileManagerListener) serviceObject);
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_XARCH
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch == null) {
				throw new IllegalStateException("A connection was never made on interface: xarch");
			}
			xarch = null;
			return;
		}

		if (interfaceName.equals(OUT_FILE_MANAGER_EVENTS)) {
			fileManagerEvents.remove(serviceObject);
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
