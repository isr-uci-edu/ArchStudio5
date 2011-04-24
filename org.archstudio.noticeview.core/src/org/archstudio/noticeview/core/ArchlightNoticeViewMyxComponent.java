package org.archstudio.noticeview.core;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.noticeadt.ArchlightNoticeADTEvent;
import org.archstudio.noticeadt.ArchlightNoticeADTListener;
import org.archstudio.noticeadt.IArchlightNoticeADT;
import org.archstudio.resources.IResources;

public class ArchlightNoticeViewMyxComponent extends AbstractMyxSimpleBrick implements ArchlightNoticeADTListener, IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_OUT_NOTICEADT = MyxUtils.createName("archlightnoticeadt");
	public static final IMyxName INTERFACE_NAME_OUT_RESOURCES = MyxUtils.createName("resources");
	public static final IMyxName INTERFACE_NAME_IN_NOTICEADTEVENTS = MyxUtils.createName("archlightnoticeadtevents");

	private MyxRegistry er = MyxRegistry.getSharedInstance();

	//protected static ArchlightNoticeViewMyxComponent sharedInstance = null;

	protected IArchlightNoticeADT noticeadt = null;
	protected IResources resources = null;

	public ArchlightNoticeViewMyxComponent() {
	}

	public void begin() {
		er.register(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_NOTICEADT)) {
			noticeadt = (IArchlightNoticeADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_RESOURCES)) {
			resources = (IResources) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_NOTICEADT)) {
			noticeadt = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_RESOURCES)) {
			resources = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public void end() {
		er.unregister(this);
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_NOTICEADTEVENTS)) {
			return this;
		}
		return null;
	}

	public void noticeADTChanged(ArchlightNoticeADTEvent evt) {
		for (Object o : er.getObjects(this)) {
			if (o instanceof ArchlightNoticeADTListener) {
				((ArchlightNoticeADTListener) o).noticeADTChanged(evt);
			}
		}
	}

}
