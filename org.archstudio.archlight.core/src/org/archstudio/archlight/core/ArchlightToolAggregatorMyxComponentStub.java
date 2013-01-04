package org.archstudio.archlight.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Myx Impl" MyxGen extension in the org.archstudio.archlight.core plugin.
 */

/**
 * Abstract Myx brick: Myx Impl
 * 
 * @generated
 */
public abstract class ArchlightToolAggregatorMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick
		implements org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.archlight.IArchlightTool {

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
	 * Myx name for the <code>results</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESULTS = MyxUtils.createName("results");

	/**
	 * Service object for the results interface.
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	protected org.archstudio.myx.java.conn.IMultiwayResults results = null;

	/**
	 * Returns the service object(s) for the results interface.
	 * 
	 * @see #OUT_RESULTS
	 * @generated
	 */
	public org.archstudio.myx.java.conn.IMultiwayResults getResults() {
		if (results == null) {
			throw new NullPointerException("Uninitialized service object: results");
		}
		return results;
	}

	/**
	 * Myx name for the <code>tools</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TOOLS = MyxUtils.createName("tools");

	/**
	 * Service object for the tools interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	protected org.archstudio.archlight.IArchlightTool tools = null;

	/**
	 * Returns the service object(s) for the tools interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTools() {
		if (tools == null) {
			throw new NullPointerException("Uninitialized service object: tools");
		}
		return tools;
	}

	/**
	 * Myx name for the <code>tool</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_TOOL = MyxUtils.createName("tool");

	/**
	 * Returns the service object(s) for the tool interface.
	 * 
	 * @see #IN_TOOL
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTool() {
		return this;
	}

	/**
	 * Myx name for the <code>progress</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_PROGRESS = MyxUtils.createName("progress");

	/**
	 * Service object for the progress interface.
	 * 
	 * @see #IN_PROGRESS
	 * @generated
	 */
	protected org.archstudio.myx.java.conn.IMultiwayProgressListener progress = null;

	/**
	 * Service object proxy for the progress interface. Calls to this proxy object are automatically delegated to all
	 * connections on the interface
	 * 
	 * @see #IN_PROGRESS
	 * @generated
	 */
	protected final org.archstudio.myx.java.conn.IMultiwayProgressListener progressProxy = (org.archstudio.myx.java.conn.IMultiwayProgressListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.myx.java.conn.IMultiwayProgressListener.class.getClassLoader(),
					new Class[] { org.archstudio.myx.java.conn.IMultiwayProgressListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							org.archstudio.myx.java.conn.IMultiwayProgressListener o = progress;
							if (o == null) {
								throw new NullPointerException("progress");
							}
							else {
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
	 * Returns the service object(s) for the progress interface.
	 * 
	 * @see #IN_PROGRESS
	 * @generated
	 */
	public org.archstudio.myx.java.conn.IMultiwayProgressListener getProgress() {
		return progressProxy;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_TOOL
	 * @see #IN_PROGRESS
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
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_RESULTS
	 * @see #OUT_TOOLS
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_RESULTS)) {
			if (results != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: results");
			}
			results = (org.archstudio.myx.java.conn.IMultiwayResults) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_TOOLS)) {
			if (tools != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: tools");
			}
			tools = (org.archstudio.archlight.IArchlightTool) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_RESULTS
	 * @see #OUT_TOOLS
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_RESULTS)) {
			if (results == null) {
				throw new IllegalStateException("A connection was never made on interface: results");
			}
			results = null;
			return;
		}

		if (interfaceName.equals(OUT_TOOLS)) {
			if (tools == null) {
				throw new IllegalStateException("A connection was never made on interface: tools");
			}
			tools = null;
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
