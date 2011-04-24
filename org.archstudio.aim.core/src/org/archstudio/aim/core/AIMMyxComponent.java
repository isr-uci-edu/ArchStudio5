package org.archstudio.aim.core;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxBrickLoaderException;
import org.archstudio.myx.fw.MyxClassManagerException;
import org.archstudio.myx.fw.MyxUtils;

public class AIMMyxComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_IN_AIM = MyxUtils.createName("aim");
	public static final IMyxName INTERFACE_NAME_OUT_MYXRUNTIME = MyxUtils.createName("myxruntime");
	public static final IMyxName INTERFACE_NAME_OUT_XARCH = MyxUtils.createName("xarch");

	protected AIMImpl aim;
	protected IMyxRuntime myxruntime = null;
	protected IXArchADT xarch = null;

	public AIMMyxComponent() {
	}

	public void init() {
		aim = new AIMImpl(xarch);
	}

	private void setupImpl() {
		aim.setXArch(xarch);
		aim.setMyxRuntime(myxruntime);
	}

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = (IXArchADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_MYXRUNTIME)) {
			myxruntime = (IMyxRuntime) serviceObject;
			try {
				myxruntime.addClassManager(MyxUtils.createName("EclipseClassManager"), "org.archstudio.myx.eclipse.MyxEclipseClassManager", null);
				myxruntime.addBrickLoader(MyxUtils.createName("EclipseBrickLoader"), "org.archstudio.myx.eclipse.MyxEclipseBrickLoader", null);
			}
			catch (MyxClassManagerException mcme) {
				// this shouldn't happen
				throw new RuntimeException(mcme);
			}
			catch (MyxBrickLoaderException mble) {
				// this shouldn't happen
				throw new RuntimeException(mble);
			}
		}
		setupImpl();
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_MYXRUNTIME)) {
			myxruntime = null;
		}
		setupImpl();
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_AIM)) {
			return aim;
		}
		return null;
	}

}
