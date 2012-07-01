package org.archstudio.issueadt.core;

import org.archstudio.xarchadt.XArchADTFileEvent;
import org.archstudio.xarchadt.XArchADTFileEvent.EventType;

/**
 * Myx brick: "Archlight Issue Impl"
 * 
 * @see org.archstudio.issueadt.core.ArchlightIssueADTMyxComponentStub
 * @generated
 */
public class ArchlightIssueADTMyxComponent extends org.archstudio.issueadt.core.ArchlightIssueADTMyxComponentStub {

	@Override
	public void init() {
		issues = new ArchlightIssueADT();
		((ArchlightIssueADT) issues).addArchlightIssueADTListener(issueEventsProxy);
	}

	@Override
	public void handleXArchADTFileEvent(XArchADTFileEvent evt) {
		if (evt.getEventType() == EventType.XARCH_CLOSED_EVENT) {
			issues.removeIssues(issues.getAllIssues(evt.getRootElementRef()));
		}
	}
}