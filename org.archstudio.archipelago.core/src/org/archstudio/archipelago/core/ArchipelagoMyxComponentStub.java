package org.archstudio.archipelago.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Archipelago Impl" MyxGen extension in the org.archstudio.archipelago.core plugin.
 */

/**
 * Abstract Myx brick: Archipelago Impl
 * 
 * @generated
 */
public abstract class ArchipelagoMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * A constructor from the super class...
	 * 
	 * @generated
	 */
	protected ArchipelagoMyxComponentStub(java.lang.String editorName, java.lang.String eclipseEditorID,
			boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

	/**
	 * Myx name for the <code>graphLayout</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_GRAPH_LAYOUT = MyxUtils.createName("graphLayout");

	/**
	 * Service object for the graphLayout interface.
	 * 
	 * @see #OUT_GRAPH_LAYOUT
	 * @generated
	 */
	protected org.archstudio.graphlayout.IGraphLayout graphLayout = null;

	/**
	 * Returns the service object(s) for the graphLayout interface.
	 * 
	 * @see #OUT_GRAPH_LAYOUT
	 * @generated
	 */
	public org.archstudio.graphlayout.IGraphLayout getGraphLayout() {
		if (graphLayout == null) {
			throw new NullPointerException("Uninitialized service object: graphLayout");
		}
		return graphLayout;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_GRAPH_LAYOUT
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_GRAPH_LAYOUT)) {
			if (graphLayout != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: graphLayout");
			}
			graphLayout = (org.archstudio.graphlayout.IGraphLayout) serviceObject;
			return;
		}

		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_GRAPH_LAYOUT
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_GRAPH_LAYOUT)) {
			if (graphLayout == null) {
				throw new IllegalStateException("A connection was never made on interface: graphLayout");
			}
			graphLayout = null;
			return;
		}

		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}
}
