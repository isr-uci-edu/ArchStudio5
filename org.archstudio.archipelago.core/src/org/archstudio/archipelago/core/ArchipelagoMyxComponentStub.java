package org.archstudio.archipelago.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Archipelago Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class ArchipelagoMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	protected ArchipelagoMyxComponentStub(String editorName, String eclipseEditorID, boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

	/**
	 * Myx interface graphLayout: <code>OUT_GRAPH_LAYOUT</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_GRAPH_LAYOUT = MyxUtils.createName("graphLayout");
	/**
	 * Service object(s) for graphLayout: <code>graphLayout</code>
	 * 
	 * @see #OUT_GRAPH_LAYOUT
	 * @generated
	 */
	protected org.archstudio.graphlayout.IGraphLayout graphLayout = null;

	/**
	 * Returns the service object(s) for <code>graphLayout</code>
	 * 
	 * @see #OUT_GRAPH_LAYOUT
	 * @generated
	 */
	public org.archstudio.graphlayout.IGraphLayout getGraphLayout() {
		return graphLayout;
	}

	/**
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_GRAPH_LAYOUT)) {
			if (graphLayout != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			graphLayout = (org.archstudio.graphlayout.IGraphLayout) serviceObject;
			return;
		}
		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_GRAPH_LAYOUT)) {
			graphLayout = null;
			return;
		}
		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}
}