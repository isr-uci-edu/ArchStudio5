package org.archstudio.archlight.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Myx Impl"
 * 
 * @generated
 */

/* package */abstract class ArchlightToolAggregatorMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor, org.archstudio.archlight.IArchlightTool,
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
	 * Myx name for the <code>results</code> interface.
	 * 
	 * MyxGenInterface[name=results,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .myx.java.conn.IMultiwayResults,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESULTS = MyxUtils.createName("results");
	/**
	 * Myx name for the <code>tools</code> interface.
	 * 
	 * MyxGenInterface[name=tools,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .archlight.IArchlightTool,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TOOLS = MyxUtils.createName("tools");
	/**
	 * Myx interface tool: <code>IN_TOOL</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_TOOL = MyxUtils.createName("tool");
	/**
	 * Myx name for the <code>progress</code> interface.
	 * 
	 * MyxGenInterface[name=progress,direction=in,single=true,
	 * serviceObjectDelegate
	 * =events,generateGetter=true,className=org.archstudio.
	 * myx.java.conn.IMultiwayProgressListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_PROGRESS = MyxUtils.createName("progress");

	/**
	 * Service object(s) for the results interface.
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	protected org.archstudio.myx.java.conn.IMultiwayResults results = null;
	/**
	 * Service object(s) for the tools interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	protected org.archstudio.archlight.IArchlightTool tools = null;
	/**
	 * Service object(s) for the progress interface.
	 * 
	 * @see #IN_PROGRESS
	 * @generated
	 */
	protected org.archstudio.myx.java.conn.IMultiwayProgressListener progress = null;

	/**
	 * Service object proxy for the progress interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_PROGRESS
	 * @generated
	 */
	protected final org.archstudio.myx.java.conn.IMultiwayProgressListener progressProxy = (org.archstudio.myx.java.conn.IMultiwayProgressListener) Proxy
			.newProxyInstance(org.archstudio.myx.java.conn.IMultiwayProgressListener.class.getClassLoader(),
					new Class[] { org.archstudio.myx.java.conn.IMultiwayProgressListener.class },
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							org.archstudio.myx.java.conn.IMultiwayProgressListener o = progress;
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
	 * Returns the service object(s) for the <code>results</code> interface.
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	public org.archstudio.myx.java.conn.IMultiwayResults getResults() {
		return results;
	}

	/**
	 * Returns the service object(s) for the <code>tools</code> interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTools() {
		return tools;
	}

	/**
	 * Returns the service object(s) for <code>tool</code>
	 * 
	 * @see #IN_TOOL
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTool() {
		return this;
	}

	/**
	 * Returns the service object(s) for the <code>progress</code> interface.
	 * 
	 * @see #IN_PROGRESS
	 * @generated
	 */
	public org.archstudio.myx.java.conn.IMultiwayProgressListener getProgress() {
		return progress;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_RESULTS)) {
			if (results != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			results = (org.archstudio.myx.java.conn.IMultiwayResults) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_TOOLS)) {
			if (tools != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			tools = (org.archstudio.archlight.IArchlightTool) serviceObject;
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
		if (interfaceName.equals(OUT_RESULTS)) {
			results = null;
			return;
		}
		if (interfaceName.equals(OUT_TOOLS)) {
			tools = null;
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
		if (interfaceName.equals(IN_TOOL)) {
			return this;
		}
		if (interfaceName.equals(IN_PROGRESS)) {
			return progressProxy;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}