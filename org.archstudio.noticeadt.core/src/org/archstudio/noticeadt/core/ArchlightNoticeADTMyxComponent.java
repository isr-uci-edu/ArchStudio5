package org.archstudio.noticeadt.core;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.noticeadt.common.ArchlightNoticeADTEvent;
import org.archstudio.noticeadt.common.ArchlightNoticeADTListener;

public class ArchlightNoticeADTMyxComponent extends AbstractMyxSimpleBrick implements ArchlightNoticeADTListener, IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_IN_NOTICEADT = MyxUtils.createName("archlightnoticeadt");
	public static final IMyxName INTERFACE_NAME_OUT_NOTICEADT_EVENTS = MyxUtils.createName("archlightnoticeadtevents");

	protected ArchlightNoticeADT noticeadt = null;
	protected ArchlightNoticeADTListener noticeadtListener = null;

	public ArchlightNoticeADTMyxComponent() {
	}

	public void init() {
		ArchlightNoticeADT noticeadtImpl = new ArchlightNoticeADT();
		this.noticeadt = noticeadtImpl;
		noticeadtImpl.addArchlightNoticeADTListener(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_NOTICEADT_EVENTS)) {
			noticeadtListener = (ArchlightNoticeADTListener) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_NOTICEADT_EVENTS)) {
			noticeadtListener = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_NOTICEADT)) {
			return noticeadt;
		}
		return null;
	}

	public synchronized void noticeADTChanged(ArchlightNoticeADTEvent evt) {
		if (noticeadtListener != null) {
			noticeadtListener.noticeADTChanged(evt);
		}
	}
}
