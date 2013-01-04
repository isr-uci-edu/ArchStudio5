package org.archstudio.graphlayout.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Graph Layout Impl" MyxGen extension in the org.archstudio.graphlayout.core plugin.
 */

/**
 * Abstract Myx brick: Graph Layout Impl
 * 
 * @generated
 */
public abstract class GraphLayoutMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

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
	 * Myx name for the <code>graphLayout</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_GRAPH_LAYOUT = MyxUtils.createName("graphLayout");

	/**
	 * Service object for the graphLayout interface.
	 * 
	 * @see #IN_GRAPH_LAYOUT
	 * @generated
	 */
	protected org.archstudio.graphlayout.IGraphLayout graphLayout = null;

	/**
	 * Returns the service object(s) for the graphLayout interface.
	 * 
	 * @see #IN_GRAPH_LAYOUT
	 * @generated
	 */
	public org.archstudio.graphlayout.IGraphLayout getGraphLayout() {
		if (graphLayout == null) {
			throw new NullPointerException("Uninitialized service object: graphLayout");
		}
		return graphLayout;
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
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_GRAPH_LAYOUT
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_GRAPH_LAYOUT)) {
			if (graphLayout == null) {
				throw new NullPointerException("Uninitialized service object: graphLayout");
			}
			return graphLayout;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_XARCH
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

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_XARCH
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
