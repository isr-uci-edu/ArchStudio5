package org.archstudio.myx.comp;

import org.archstudio.myx.fw.*;

public class MyxRuntimeComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick {
	public static final IMyxName INTERFACE_NAME_IN_MYXRUNTIME = MyxUtils.createName("myxruntime");

	protected IMyxImplementation myx;
	protected IMyxRuntime runtime = null;

	public MyxRuntimeComponent() {
		myx = MyxUtils.getDefaultImplementation();
	}

	public void init() {
		runtime = myx.createRuntime();
	}

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_MYXRUNTIME)) {
			return runtime;
		}
		return null;
	}

}
