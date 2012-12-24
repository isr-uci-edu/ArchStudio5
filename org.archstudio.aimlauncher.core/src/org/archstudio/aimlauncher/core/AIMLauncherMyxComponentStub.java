package org.archstudio.aimlauncher.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "AIM Launcher Component Impl"
 * 
 * @generated
 */
abstract class AIMLauncherMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	protected AIMLauncherMyxComponentStub(String editorName, String eclipseEditorID, boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

	/**
	 * Myx interface aim: <code>OUT_AIM</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_AIM = MyxUtils.createName("aim");
	/**
	 * Service object(s) for aim: <code>aim</code>
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	protected org.archstudio.aim.IAIM aim = null;

	/**
	 * Returns the service object(s) for <code>aim</code>
	 * 
	 * @see #OUT_AIM
	 * @generated
	 */
	public org.archstudio.aim.IAIM getAim() {
		return aim;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_AIM)) {
			if (aim != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			aim = (org.archstudio.aim.IAIM) serviceObject;
			return;
		}
		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_AIM)) {
			aim = null;
			return;
		}
		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}
}