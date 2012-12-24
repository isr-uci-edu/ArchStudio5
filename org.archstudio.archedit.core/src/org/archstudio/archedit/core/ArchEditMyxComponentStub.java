package org.archstudio.archedit.core;

import org.archstudio.myx.fw.IMyxName;

/**
 * Abstract Myx brick: "ArchEdit Component Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class ArchEditMyxComponentStub extends org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent
		implements org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	public ArchEditMyxComponentStub(String editorName, String eclipseEditorID, boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

	/**
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
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