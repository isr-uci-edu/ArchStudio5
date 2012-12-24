package org.archstudio.aim.core;

import org.archstudio.myx.fw.IMyxName;

/**
 * Myx brick: "AIM Myx Component Impl"
 * 
 * @see org.archstudio.aim.core.AIMMyxComponentStub
 * @generated
 */
public class AIMMyxComponent extends org.archstudio.aim.core.AIMMyxComponentStub {

	public AIMMyxComponent() {
		aim = new AIMImpl();
	}

	private void setupImpl() {
		((AIMImpl) aim).setXArch(xarch);
		((AIMImpl) aim).setMyxRuntime(myxRuntime);
	}

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceConnected(interfaceName, serviceObject);
		setupImpl();
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
		setupImpl();
	}
}