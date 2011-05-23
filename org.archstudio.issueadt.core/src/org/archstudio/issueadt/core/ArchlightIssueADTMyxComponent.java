package org.archstudio.issueadt.core;

import org.archstudio.xarchadt.XArchADTFileEvent;

/**
 * Myx brick: "Archlight Issue Impl"
 * 
 * @see org.archstudio.issueadt.core.ArchlightIssueADTMyxComponentStub
 * @generated
 */
public class ArchlightIssueADTMyxComponent extends
		org.archstudio.issueadt.core.ArchlightIssueADTMyxComponentStub {

	@Override
	public void init() {
		issues = new ArchlightIssueADT();
		((ArchlightIssueADT) issues).addArchlightIssueADTListener(issueEvents);
	}

	@Override
	public void handleXArchADTFileEvent(XArchADTFileEvent evt) {
		switch (evt.getEventType()) {
		case XARCH_CLOSED_EVENT:
			issues.removeIssues(issues.getAllIssues(evt.getRootElementRef()));
		}
	}
}