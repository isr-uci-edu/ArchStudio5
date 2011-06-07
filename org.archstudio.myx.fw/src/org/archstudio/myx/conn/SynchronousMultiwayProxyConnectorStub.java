package org.archstudio.myx.conn;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.conn.IMultiwayProgressListener;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import java.lang.Object;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.conn.IMultiwayResults;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Synchronous Multiway Proxy Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
abstract class SynchronousMultiwayProxyConnectorStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
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
	 * Myx interface results: <code>IN_RESULTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_RESULTS = MyxUtils.createName("results");
	/**
	 * Myx interface out: <code>IN_OUT</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_OUT = MyxUtils.createName("out");
	/**
	 * Myx interface in: <code>IN_IN</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_IN = MyxUtils.createName("in");
	/**
	 * Myx interface progress: <code>IN_PROGRESS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_PROGRESS = MyxUtils.createName("progress");

	/**
	 * Service object(s) for results: <code>results</code>
	 *
	 * @see #IN_RESULTS
	 * @generated
	 */
	protected org.archstudio.myx.conn.IMultiwayResults results = null;
	/**
	 * Service object(s) for out: <code>out</code>
	 *
	 * @see #IN_OUT
	 * @generated
	 */
	protected java.lang.Object out = null;
	/**
	 * Service object(s) for in: <code>in</code>
	 *
	 * @see #IN_IN
	 * @generated
	 */
	protected java.lang.Object in = null;
	/**
	 * Service object(s) for progress: <code>progress</code>
	 *
	 * @see #IN_PROGRESS
	 * @generated
	 */
	protected org.archstudio.myx.conn.IMultiwayProgressListener progress = null;

	/**
	 * Returns the service object(s) for <code>results</code>
	 *
	 * @see #IN_RESULTS
	 * @generated
	 */
	public org.archstudio.myx.conn.IMultiwayResults getResults() {
		return results;
	}

	/**
	 * Returns the service object(s) for <code>out</code>
	 *
	 * @see #IN_OUT
	 * @generated
	 */
	public java.lang.Object getOut() {
		return out;
	}

	/**
	 * Returns the service object(s) for <code>in</code>
	 *
	 * @see #IN_IN
	 * @generated
	 */
	public java.lang.Object getIn() {
		return in;
	}

	/**
	 * Returns the service object(s) for <code>progress</code>
	 *
	 * @see #IN_PROGRESS
	 * @generated
	 */
	public org.archstudio.myx.conn.IMultiwayProgressListener getProgress() {
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
		if (interfaceName.equals(IN_RESULTS)) {
			if (results == null) {
				throw new NullPointerException("results");
			}
			return results;
		}
		if (interfaceName.equals(IN_OUT)) {
			if (out == null) {
				throw new NullPointerException("out");
			}
			return out;
		}
		if (interfaceName.equals(IN_IN)) {
			if (in == null) {
				throw new NullPointerException("in");
			}
			return in;
		}
		if (interfaceName.equals(IN_PROGRESS)) {
			if (progress == null) {
				throw new NullPointerException("progress");
			}
			return progress;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}