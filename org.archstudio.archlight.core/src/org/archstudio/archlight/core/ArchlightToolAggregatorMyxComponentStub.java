package org.archstudio.archlight.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.conn.IMultiwayProgressListener;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.conn.IMultiwayResults;
import org.archstudio.archlight.IArchlightTool;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "Myx Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ArchlightToolAggregatorMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.archlight.IArchlightTool,
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
	 * Myx interface results: <code>OUT_RESULTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_RESULTS = MyxUtils.createName("results");
	/**
	 * Myx interface archlightTools: <code>OUT_ARCHLIGHT_TOOLS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_ARCHLIGHT_TOOLS = MyxUtils
			.createName("archlightTools");
	/**
	 * Myx interface archlightTool: <code>IN_ARCHLIGHT_TOOL</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_ARCHLIGHT_TOOL = MyxUtils
			.createName("archlightTool");
	/**
	 * Myx interface progress: <code>IN_PROGRESS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_PROGRESS = MyxUtils.createName("progress");
	/**
	 * Service object(s) for results: <code>results</code>
	 *
	 * @see #OUT_RESULTS
	 * @generated
	 */
	protected org.archstudio.myx.conn.IMultiwayResults results = null;

	/**
	 * Service object(s) for archlightTools: <code>archlightTools</code>
	 *
	 * @see #OUT_ARCHLIGHT_TOOLS
	 * @generated
	 */
	protected org.archstudio.archlight.IArchlightTool archlightTools = null;
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
	 * @see #OUT_RESULTS
	 * @generated
	 */
	public org.archstudio.myx.conn.IMultiwayResults getResults() {
		return results;
	}

	/**
	 * Returns the service object(s) for <code>archlightTools</code>
	 *
	 * @see #OUT_ARCHLIGHT_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getArchlightTools() {
		return archlightTools;
	}

	/**
	 * Returns the service object(s) for <code>archlightTool</code>
	 *
	 * @see #IN_ARCHLIGHT_TOOL
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getArchlightTool() {
		return this;
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
		if (interfaceName.equals(OUT_RESULTS)) {
			if (results != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			results = (org.archstudio.myx.conn.IMultiwayResults) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_ARCHLIGHT_TOOLS)) {
			if (archlightTools != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			archlightTools = (org.archstudio.archlight.IArchlightTool) serviceObject;
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
		if (interfaceName.equals(OUT_RESULTS)) {
			results = null;
			return;
		}
		if (interfaceName.equals(OUT_ARCHLIGHT_TOOLS)) {
			archlightTools = null;
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
		if (interfaceName.equals(IN_ARCHLIGHT_TOOL)) {
			return this;
		}
		if (interfaceName.equals(IN_PROGRESS)) {
			if (progress == null) {
				throw new NullPointerException("progress");
			}
			return progress;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}