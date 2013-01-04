package org.archstudio.aimlauncher.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "AIM Launcher Component Impl" MyxGen extension in the org.archstudio.aimlauncher.core plugin.
 */

/**
 * Abstract Myx brick: AIM Launcher Component Impl
 * 
 * @generated
 */
public abstract class AIMLauncherMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * A constructor from the super class...
	 * 
	 * @generated
	 */
	protected AIMLauncherMyxComponentStub(java.lang.String editorName, java.lang.String eclipseEditorID,
			boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

	/**
	 * Myx name for the <code>aim</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_AIM = MyxUtils.createName("aim");

	/**
	 * Service object for the aim interface.
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	protected org.archstudio.aim.IAIM aim = null;

	/**
	 * Returns the service object(s) for the aim interface.
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	public org.archstudio.aim.IAIM getAim() {
		if (aim == null) {
			throw new NullPointerException("Uninitialized service object: aim");
		}
		return aim;
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
	 * @see #OUT_AIM
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_AIM)) {
			if (aim != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: aim");
			}
			aim = (org.archstudio.aim.IAIM) serviceObject;
			return;
		}

		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_AIM)) {
			if (aim == null) {
				throw new IllegalStateException("A connection was never made on interface: aim");
			}
			aim = null;
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
