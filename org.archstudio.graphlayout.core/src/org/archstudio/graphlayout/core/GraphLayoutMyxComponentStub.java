package org.archstudio.graphlayout.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Graph Layout Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class GraphLayoutMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx interface preferences: <code>OUT_PREFERENCES</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils.createName("preferences");
	/**
	 * Myx interface graphLayout: <code>IN_GRAPH_LAYOUT</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_GRAPH_LAYOUT = MyxUtils.createName("graphLayout");
	/**
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");

	/**
	 * Service object(s) for preferences: <code>preferences</code>
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;
	/**
	 * Service object(s) for graphLayout: <code>graphLayout</code>
	 * 
	 * @see #IN_GRAPH_LAYOUT
	 * @generated
	 */
	protected org.archstudio.graphlayout.IGraphLayout graphLayout = null;
	/**
	 * Service object(s) for xarch: <code>xarch</code>
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;

	/**
	 * Returns the service object(s) for <code>preferences</code>
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		return preferences;
	}

	/**
	 * Returns the service object(s) for <code>graphLayout</code>
	 * 
	 * @see #IN_GRAPH_LAYOUT
	 * @generated
	 */
	public org.archstudio.graphlayout.IGraphLayout getGraphLayout() {
		return graphLayout;
	}

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
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
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
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			preferences = null;
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
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	/**
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_GRAPH_LAYOUT)) {
			if (graphLayout == null) {
				throw new NullPointerException("graphLayout");
			}
			return graphLayout;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}