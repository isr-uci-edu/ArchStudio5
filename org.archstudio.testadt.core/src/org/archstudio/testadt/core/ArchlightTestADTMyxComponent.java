package org.archstudio.testadt.core;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.testadt.common.ArchlightTestADTEvent;
import org.archstudio.testadt.common.ArchlightTestADTListener;

public class ArchlightTestADTMyxComponent extends AbstractMyxSimpleBrick implements ArchlightTestADTListener, IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_IN_TESTADT = MyxUtils.createName("archlighttestadt");
	public static final IMyxName INTERFACE_NAME_OUT_TESTADT_EVENTS = MyxUtils.createName("archlighttestadtevents");

	protected ArchlightTestADT testadt = null;
	protected ArchlightTestADTListener testadtListener = null;

	public ArchlightTestADTMyxComponent() {
	}

	public void init() {
		ArchlightTestADT testadtImpl = new ArchlightTestADT();
		this.testadt = testadtImpl;
		testadtImpl.addArchlightTestADTListener(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_TESTADT_EVENTS)) {
			testadtListener = (ArchlightTestADTListener) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_TESTADT_EVENTS)) {
			testadtListener = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_TESTADT)) {
			return testadt;
		}
		return null;
	}

	public synchronized void testADTChanged(ArchlightTestADTEvent evt) {
		if (testadtListener != null) {
			testadtListener.testADTChanged(evt);
		}
	}
}
