package org.archstudio.myx.fw;

import java.util.Collection;

public interface IMyxInterfaceManager {
	public void addInterface(IMyxName interfaceName, IMyxInterfaceDescription interfaceDescription,
			EMyxInterfaceDirection interfaceDirection);

	public void removeInterface(IMyxName interfaceName);

	public Collection<? extends IMyxName> getAllInterfaceNames();

	public IMyxInterfaceDescription getInterfaceDescription(IMyxName interfaceName);

	public EMyxInterfaceDirection getInterfaceDirection(IMyxName interfaceName);
}