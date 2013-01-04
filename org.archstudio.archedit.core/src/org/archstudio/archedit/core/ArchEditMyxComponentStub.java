package org.archstudio.archedit.core;

import org.archstudio.myx.fw.IMyxName;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "ArchEdit Component Impl" MyxGen extension in the org.archstudio.archedit.core plugin.
 */

/**
 * Abstract Myx brick: ArchEdit Component Impl
 * 
 * @generated
 */
public abstract class ArchEditMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * A constructor from the super class...
	 * 
	 * @generated
	 */
	protected ArchEditMyxComponentStub(java.lang.String editorName, java.lang.String eclipseEditorID,
			boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
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
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
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
