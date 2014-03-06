package org.archstudio.eclipse.ui.editors;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Abstract ArchStudio Editor Component Impl" MyxGen extension in the org.archstudio.eclipse.ui
 * plugin.
 */

/**
 * Abstract Myx brick: Abstract ArchStudio Editor Component Impl
 * 
 * @generated
 */
public abstract class AbstractArchStudioEditorMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick
		implements org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.launcher.ILaunchable {

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
	 * Myx name for the <code>resources</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils.createName("resources");

	/**
	 * Service object for the resources interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;

	/**
	 * Returns the service object(s) for the resources interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		if (resources == null) {
			throw new NullPointerException("Uninitialized service object: resources");
		}
		return resources;
	}

	/**
	 * Myx name for the <code>editorManager</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_EDITOR_MANAGER = MyxUtils.createName("editorManager");

	/**
	 * Service object for the editorManager interface.
	 * 
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;

	/**
	 * Returns the service object(s) for the editorManager interface.
	 * 
	 * @see #OUT_EDITOR_MANAGER
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
	public static final IMyxName IN_FOCUS_EDITOR_EVENTS = MyxUtils.createName("focusEditorEvents");

	/**
	 * Service object proxy for the focusEditorEvents interface. Calls to this proxy object are automatically delegated
	 * to all service objects in the MyxRegistry of type org.archstudio.eclipse.ui.IFocusEditorListener.
	 * 
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected final org.archstudio.eclipse.ui.IFocusEditorListener focusEditorEventsProxy = (org.archstudio.eclipse.ui.IFocusEditorListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.eclipse.ui.IFocusEditorListener.class.getClassLoader(),
					new Class[] { org.archstudio.eclipse.ui.IFocusEditorListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.eclipse.ui.IFocusEditorListener o : myxRegistry.getObjects(
									AbstractArchStudioEditorMyxComponentStub.this,
									org.archstudio.eclipse.ui.IFocusEditorListener.class)) {
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
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	public org.archstudio.eclipse.ui.IFocusEditorListener getFocusEditorEvents() {
		return focusEditorEventsProxy;
	}

	/**
	 * Myx name for the <code>fileManager</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FILE_MANAGER = MyxUtils.createName("fileManager");

	/**
	 * Service object for the fileManager interface.
	 * 
	 * @see #OUT_FILE_MANAGER
	 * @generated
	 */
	protected org.archstudio.filemanager.IFileManager fileManager = null;

	/**
	 * Returns the service object(s) for the fileManager interface.
	 * 
	 * @see #OUT_FILE_MANAGER
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManager getFileManager() {
		if (fileManager == null) {
			throw new NullPointerException("Uninitialized service object: fileManager");
		}
		return fileManager;
	}

	/**
	 * Myx name for the <code>fileManagerEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_MANAGER_EVENTS = MyxUtils.createName("fileManagerEvents");

	/**
	 * Service object proxy for the fileManagerEvents interface. Calls to this proxy object are automatically delegated
	 * to all service objects in the MyxRegistry of type org.archstudio.filemanager.IFileManagerListener.
	 * 
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected final org.archstudio.filemanager.IFileManagerListener fileManagerEventsProxy = (org.archstudio.filemanager.IFileManagerListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.filemanager.IFileManagerListener.class.getClassLoader(),
					new Class[] { org.archstudio.filemanager.IFileManagerListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.filemanager.IFileManagerListener o : myxRegistry.getObjects(
									AbstractArchStudioEditorMyxComponentStub.this,
									org.archstudio.filemanager.IFileManagerListener.class)) {
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
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManagerListener getFileManagerEvents() {
		return fileManagerEventsProxy;
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
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils.createName("fileEvents");

	/**
	 * Service object proxy for the fileEvents interface. Calls to this proxy object are automatically delegated to all
	 * service objects in the MyxRegistry of type org.archstudio.xarchadt.IXArchADTFileListener.
	 * 
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTFileListener fileEventsProxy = (org.archstudio.xarchadt.IXArchADTFileListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTFileListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTFileListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.xarchadt.IXArchADTFileListener o : myxRegistry.getObjects(
									AbstractArchStudioEditorMyxComponentStub.this,
									org.archstudio.xarchadt.IXArchADTFileListener.class)) {
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
	 * @see #IN_FILE_EVENTS
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
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils.createName("modelEvents");

	/**
	 * Service object proxy for the modelEvents interface. Calls to this proxy object are automatically delegated to all
	 * service objects in the MyxRegistry of type org.archstudio.xarchadt.IXArchADTModelListener.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTModelListener modelEventsProxy = (org.archstudio.xarchadt.IXArchADTModelListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTModelListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTModelListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.xarchadt.IXArchADTModelListener o : myxRegistry.getObjects(
									AbstractArchStudioEditorMyxComponentStub.this,
									org.archstudio.xarchadt.IXArchADTModelListener.class)) {
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
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEvents() {
		return modelEventsProxy;
	}

	/**
	 * Myx name for the <code>launcher</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_LAUNCHER = MyxUtils.createName("launcher");

	/**
	 * Returns the service object(s) for the launcher interface.
	 * 
	 * @see #IN_LAUNCHER
	 * @generated
	 */
	public org.archstudio.launcher.ILaunchable getLauncher() {
		return this;
	}

	/**
	 * Myx name for the <code>variabilityEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_VARIABILITY_EVENTS = MyxUtils.createName("variabilityEvents");

	/**
	 * Service object proxy for the variabilityEvents interface. Calls to this proxy object are automatically delegated
	 * to all service objects in the MyxRegistry of type
	 * org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.
	 * 
	 * @see #IN_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.variability.IXArchADTVariabilityListener variabilityEventsProxy = (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener o : myxRegistry
									.getObjects(AbstractArchStudioEditorMyxComponentStub.this,
											org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class)) {
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
	 * @see #IN_VARIABILITY_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.variability.IXArchADTVariabilityListener getVariabilityEvents() {
		return variabilityEventsProxy;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @see #IN_FILE_EVENTS
	 * @see #IN_MODEL_EVENTS
	 * @see #IN_LAUNCHER
	 * @see #IN_VARIABILITY_EVENTS
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_FOCUS_EDITOR_EVENTS)) {
			return focusEditorEventsProxy;
		}
		if (interfaceName.equals(IN_FILE_MANAGER_EVENTS)) {
			return fileManagerEventsProxy;
		}
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			return fileEventsProxy;
		}
		if (interfaceName.equals(IN_MODEL_EVENTS)) {
			return modelEventsProxy;
		}
		if (interfaceName.equals(IN_LAUNCHER)) {
			return this;
		}
		if (interfaceName.equals(IN_VARIABILITY_EVENTS)) {
			return variabilityEventsProxy;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_RESOURCES
	 * @see #OUT_EDITOR_MANAGER
	 * @see #OUT_FILE_MANAGER
	 * @see #OUT_XARCH
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: resources");
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: editorManager");
			}
			editorManager = (org.archstudio.editormanager.IEditorManager) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_FILE_MANAGER)) {
			if (fileManager != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: fileManager");
			}
			fileManager = (org.archstudio.filemanager.IFileManager) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: xarch");
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_RESOURCES
	 * @see #OUT_EDITOR_MANAGER
	 * @see #OUT_FILE_MANAGER
	 * @see #OUT_XARCH
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources == null) {
				throw new IllegalStateException("A connection was never made on interface: resources");
			}
			resources = null;
			return;
		}

		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager == null) {
				throw new IllegalStateException("A connection was never made on interface: editorManager");
			}
			editorManager = null;
			return;
		}

		if (interfaceName.equals(OUT_FILE_MANAGER)) {
			if (fileManager == null) {
				throw new IllegalStateException("A connection was never made on interface: fileManager");
			}
			fileManager = null;
			return;
		}

		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch == null) {
				throw new IllegalStateException("A connection was never made on interface: xarch");
			}
			xarch = null;
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
