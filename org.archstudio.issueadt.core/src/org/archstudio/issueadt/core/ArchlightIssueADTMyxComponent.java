package org.archstudio.issueadt.core;

import org.archstudio.issueadt.common.ArchlightIssueADTEvent;
import org.archstudio.issueadt.common.ArchlightIssueADTListener;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xarchadt.common.IXArchADTFileListener;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTFileEvent;

public class ArchlightIssueADTMyxComponent extends AbstractMyxSimpleBrick implements ArchlightIssueADTListener, IMyxDynamicBrick, IXArchADTFileListener {

	public static final IMyxName INTERFACE_NAME_IN_FILEEVENTS = MyxUtils.createName("fileevents");
	public static final IMyxName INTERFACE_NAME_IN_ISSUEADT = MyxUtils.createName("archlightissueadt");
	public static final IMyxName INTERFACE_NAME_OUT_ISSUEADT_EVENTS = MyxUtils.createName("archlightissueadtevents");

	protected ArchlightIssueADT issueadt = null;
	protected ArchlightIssueADTListener issueadtListener = null;

	public ArchlightIssueADTMyxComponent() {
	}

	public void init() {
		ArchlightIssueADT issueadtImpl = new ArchlightIssueADT();
		this.issueadt = issueadtImpl;
		issueadtImpl.addArchlightIssueADTListener(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_ISSUEADT_EVENTS)) {
			issueadtListener = (ArchlightIssueADTListener) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_ISSUEADT_EVENTS)) {
			issueadtListener = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_ISSUEADT)) {
			return issueadt;
		}
		else if (interfaceName.equals(INTERFACE_NAME_IN_FILEEVENTS)) {
			return this;
		}
		return null;
	}

	public synchronized void issueADTChanged(ArchlightIssueADTEvent evt) {
		if (issueadtListener != null) {
			issueadtListener.issueADTChanged(evt);
		}
	}

	public void removeIssues(ObjRef documentRootRef) {
		issueadt.removeIssues(issueadt.getAllIssues(documentRootRef));
	}

	public void handleXArchADTFileEvent(XArchADTFileEvent evt) {
		switch (evt.getEventType()) {
		case XARCH_CLOSED_EVENT:
			issueadt.removeIssues(issueadt.getAllIssues(evt.getRootElementRef()));
		}
	}
}
