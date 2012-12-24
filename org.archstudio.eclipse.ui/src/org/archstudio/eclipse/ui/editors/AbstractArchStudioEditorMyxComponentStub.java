package org.archstudio.eclipse.ui.editors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Abstract ArchStudio Editor Component Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
/* package */abstract class AbstractArchStudioEditorMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.launcher.ILaunchable, org.archstudio.myx.fw.IMyxLifecycleProcessor,
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
	 * Myx name for the <code>resources</code> interface.
	 * 
	 * MyxGenInterface[name=resources,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .resources.IResources,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils.createName("resources");
	/**
	 * Myx name for the <code>editorManager</code> interface.
	 * 
	 * MyxGenInterface[name=editorManager,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .editormanager.IEditorManager,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_EDITOR_MANAGER = MyxUtils.createName("editorManager");
	/**
	 * Myx name for the <code>focusEditorEvents</code> interface.
	 * 
	 * MyxGenInterface[name=focusEditorEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =myxRegistry,generateGetter=true,className=org.archstudio
	 * .eclipse.ui.IFocusEditorListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FOCUS_EDITOR_EVENTS = MyxUtils.createName("focusEditorEvents");
	/**
	 * Myx name for the <code>fileManager</code> interface.
	 * 
	 * MyxGenInterface[name=fileManager,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .filemanager.IFileManager,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FILE_MANAGER = MyxUtils.createName("fileManager");
	/**
	 * Myx name for the <code>fileManagerEvents</code> interface.
	 * 
	 * MyxGenInterface[name=fileManagerEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =myxRegistry,generateGetter=true,className=org.archstudio
	 * .filemanager.IFileManagerListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_MANAGER_EVENTS = MyxUtils.createName("fileManagerEvents");
	/**
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * MyxGenInterface[name=xarch,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .xarchadt.IXArchADT,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * MyxGenInterface[name=fileEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =myxRegistry,generateGetter=true,className=org.archstudio
	 * .xarchadt.IXArchADTFileListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils.createName("fileEvents");
	/**
	 * Myx name for the <code>modelEvents</code> interface.
	 * 
	 * MyxGenInterface[name=modelEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =myxRegistry,generateGetter=true,className=org.archstudio
	 * .xarchadt.IXArchADTModelListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils.createName("modelEvents");
	/**
	 * Myx name for the <code>launcher</code> interface.
	 * 
	 * MyxGenInterface[name=launcher,direction=in,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .launcher.ILaunchable,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_LAUNCHER = MyxUtils.createName("launcher");

	/**
	 * Myx name for the <code>variabilityEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=variabilityEvents,direction=in,single=true,serviceObjectDelegate=myxRegistry,generateGetter=true,className=org.archstudio.xarchadt.variability.IXArchADTVariabilityListener,domain=top,description=null]
	public static final IMyxName IN_VARIABILITY_EVENTS = MyxUtils.createName("variabilityEvents");
	/**
	 * Service object(s) for the resources interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;
	/**
	 * Service object(s) for the editorManager interface.
	 * 
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object proxy for the focusEditorEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	protected final org.archstudio.eclipse.ui.IFocusEditorListener focusEditorEventsProxy = (org.archstudio.eclipse.ui.IFocusEditorListener) Proxy
			.newProxyInstance(org.archstudio.eclipse.ui.IFocusEditorListener.class.getClassLoader(),
					new Class[] { org.archstudio.eclipse.ui.IFocusEditorListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Service object(s) for the fileManager interface.
	 * 
	 * @see #OUT_FILE_MANAGER
	 * @generated
	 */
	protected org.archstudio.filemanager.IFileManager fileManager = null;
	/**
	 * Service object proxy for the fileManagerEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected final org.archstudio.filemanager.IFileManagerListener fileManagerEventsProxy = (org.archstudio.filemanager.IFileManagerListener) Proxy
			.newProxyInstance(org.archstudio.filemanager.IFileManagerListener.class.getClassLoader(),
					new Class[] { org.archstudio.filemanager.IFileManagerListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Service object(s) for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object proxy for the fileEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTFileListener fileEventsProxy = (org.archstudio.xarchadt.IXArchADTFileListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTFileListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTFileListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Service object proxy for the modelEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.IXArchADTModelListener modelEventsProxy = (org.archstudio.xarchadt.IXArchADTModelListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.IXArchADTModelListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.IXArchADTModelListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Service object proxy for the variabilityEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_VARIABILITY_EVENTS
	 * @generated
	 */
	protected final org.archstudio.xarchadt.variability.IXArchADTVariabilityListener variabilityEventsProxy = (org.archstudio.xarchadt.variability.IXArchADTVariabilityListener) Proxy
			.newProxyInstance(org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class.getClassLoader(),
					new Class[] { org.archstudio.xarchadt.variability.IXArchADTVariabilityListener.class },
					new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Returns the service object(s) for the <code>resources</code> interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		return resources;
	}

	/**
	 * Returns the service object(s) for the <code>editorManager</code>
	 * interface.
	 * 
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
	}

	/**
	 * Returns the service object(s) for the <code>focusEditorEvents</code>
	 * interface.
	 * 
	 * @see #IN_FOCUS_EDITOR_EVENTS
	 * @generated
	 */
	/**
	 * Returns the service object(s) for the <code>fileManager</code> interface.
	 * 
	 * @see #OUT_FILE_MANAGER
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManager getFileManager() {
		return fileManager;
	}

	/**
	 * Returns the service object(s) for the <code>fileManagerEvents</code>
	 * interface.
	 * 
	 * @see #IN_FILE_MANAGER_EVENTS
	 * @generated
	 */
	/**
	 * Returns the service object(s) for the <code>xarch</code> interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * Returns the service object(s) for the <code>fileEvents</code> interface.
	 * 
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	/**
	 * Returns the service object(s) for the <code>modelEvents</code> interface.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	/**
	 * Returns the service object(s) for the <code>launcher</code> interface.
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

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			editorManager = (org.archstudio.editormanager.IEditorManager) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_FILE_MANAGER)) {
			if (fileManager != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			fileManager = (org.archstudio.filemanager.IFileManager) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}
		throw new IllegalArgumentException("Unhandled interface connection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
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
		throw new IllegalArgumentException("Unhandled interface disconnection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	/**
	 * @generated
	 */

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
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}