package org.archstudio.issueadt.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.archstudio.archlight.common.ArchlightIssue;

public class ArchlightIssueADTEvent implements java.io.Serializable {
	public enum EventType {
		ISSUES_ADDED, ISSUES_REMOVED
	}

	protected EventType type;
	protected List<? extends ArchlightIssue> issues;

	public <T extends ArchlightIssue> ArchlightIssueADTEvent(EventType type, Collection<T> issues) {
		this.type = type;
		this.issues = Collections.unmodifiableList(new ArrayList<T>(issues));
	}

	public List<? extends ArchlightIssue> getIssues() {
		return issues;
	}

	public EventType getEventType() {
		return type;
	}

}
