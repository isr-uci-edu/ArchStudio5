package org.archstudio.myx.fw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyxBasicInterfaceManager implements IMyxInterfaceManager {

	protected final IMyxRuntime runtime;
	protected final List<? extends IMyxName> path;
	protected final IMyxName brickName;

	public MyxBasicInterfaceManager(IMyxRuntime runtime, List<? extends IMyxName> path, IMyxName brickName) {
		this.runtime = runtime;
		if (path == null) {
			this.path = Collections.emptyList();
		}
		else {
			this.path = Collections.unmodifiableList(new ArrayList<IMyxName>(path));
		}
		this.brickName = brickName;
	}

	public void addInterface(IMyxName interfaceName, IMyxInterfaceDescription interfaceDescription,
			EMyxInterfaceDirection interfaceDirection) {
		runtime.addInterface(path, brickName, interfaceName, interfaceDescription, interfaceDirection);
	}

	public void removeInterface(IMyxName interfaceName) {
		runtime.removeInterface(path, brickName, interfaceName);
	}

	public Collection<? extends IMyxName> getAllInterfaceNames() {
		return runtime.getAllInterfaceNames(path, brickName);
	}

	public IMyxInterfaceDescription getInterfaceDescription(IMyxName interfaceName) {
		return runtime.getInterfaceDescription(path, brickName, interfaceName);
	}

	public EMyxInterfaceDirection getInterfaceDirection(IMyxName interfaceName) {
		return runtime.getInterfaceDirection(path, brickName, interfaceName);
	}
}
