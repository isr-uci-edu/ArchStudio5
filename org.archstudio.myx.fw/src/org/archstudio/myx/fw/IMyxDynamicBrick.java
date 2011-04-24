package org.archstudio.myx.fw;

public interface IMyxDynamicBrick extends IMyxBrick {
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject);

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject);

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject);
}
