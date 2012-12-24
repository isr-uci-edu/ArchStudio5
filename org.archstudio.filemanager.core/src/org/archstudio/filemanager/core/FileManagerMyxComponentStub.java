package org.archstudio.filemanager.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "File Manager Impl B"
 * 
 * @generated
 */
abstract class FileManagerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.xarchadt.IXArchADTModelListener, org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor, org.archstudio.filemanager.IFileManager,
		org.archstudio.xarchadt.IXArchADTFileListener, org.archstudio.myx.fw.IMyxProvidedServiceProvider {

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
	 * Myx name for the <code>modelEvents</code> interface.
	 * 
	 * MyxGenInterface[name=modelEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =brick,generateGetter=true,className=org.archstudio.xarchadt
	 * .IXArchADTModelListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_MODEL_EVENTS = MyxUtils.createName("modelEvents");
	/**
	 * Myx name for the <code>fileEvents</code> interface.
	 * 
	 * MyxGenInterface[name=fileEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =brick,generateGetter=true,className=org.archstudio.xarchadt
	 * .IXArchADTFileListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils.createName("fileEvents");
	/**
	 * Myx name for the <code>fileManager</code> interface.
	 * 
	 * MyxGenInterface[name=fileManager,direction=in,single=true,
	 * serviceObjectDelegate
	 * =brick,generateGetter=true,className=org.archstudio.filemanager
	 * .IFileManager,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_FILE_MANAGER = MyxUtils.createName("fileManager");
	/**
	 * Myx name for the <code>fileManagerEvents</code> interface.
	 * 
	 * MyxGenInterface[name=fileManagerEvents,direction=out,single=false,
	 * serviceObjectDelegate
	 * =events,generateGetter=true,className=org.archstudio.
	 * filemanager.IFileManagerListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_FILE_MANAGER_EVENTS = MyxUtils.createName("fileManagerEvents");

	/**
	 * Service object(s) for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for the fileManagerEvents interface.
	 * 
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.filemanager.IFileManagerListener> fileManagerEvents = new CopyOnWriteArrayList<org.archstudio.filemanager.IFileManagerListener>();
	/**
	 * Service object proxy for the fileManagerEvents interface. Calls to the
	 * proxy object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	protected final org.archstudio.filemanager.IFileManagerListener fileManagerEventsProxy = (org.archstudio.filemanager.IFileManagerListener) Proxy
			.newProxyInstance(org.archstudio.filemanager.IFileManagerListener.class.getClassLoader(),
					new Class[] { org.archstudio.filemanager.IFileManagerListener.class }, new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Returns the service object(s) for the <code>xarch</code> interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * Returns the service object(s) for the <code>modelEvents</code> interface.
	 * 
	 * @see #IN_MODEL_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTModelListener getModelEvents() {
		return this;
	}

	/**
	 * Returns the service object(s) for the <code>fileEvents</code> interface.
	 * 
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTFileListener getFileEvents() {
		return this;
	}

	/**
	 * Returns the service object(s) for the <code>fileManager</code> interface.
	 * 
	 * @see #IN_FILE_MANAGER
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManager getFileManager() {
		return this;
	}

	/**
	 * Returns the service object(s) for the <code>fileManagerEvents</code>
	 * interface.
	 * 
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.filemanager.IFileManagerListener> getFileManagerEvents() {
		return fileManagerEvents;
	}

	/**
	 * Returns the proxy service object for the <code>fileManagerEvents</code>
	 * interface.
	 * 
	 * @see #org fileManagerEventsProxy
	 * @see #OUT_FILE_MANAGER_EVENTS
	 * @generated
	 */
	public org.archstudio.filemanager.IFileManagerListener getFileManagerEventsProxy() {
		return fileManagerEventsProxy;
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
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_FILE_MANAGER_EVENTS)) {
			fileManagerEvents.add((org.archstudio.filemanager.IFileManagerListener) serviceObject);
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
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
			return;
		}
		if (interfaceName.equals(OUT_FILE_MANAGER_EVENTS)) {
			fileManagerEvents.remove(serviceObject);
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
		if (interfaceName.equals(IN_MODEL_EVENTS)) {
			return this;
		}
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			return this;
		}
		if (interfaceName.equals(IN_FILE_MANAGER)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}