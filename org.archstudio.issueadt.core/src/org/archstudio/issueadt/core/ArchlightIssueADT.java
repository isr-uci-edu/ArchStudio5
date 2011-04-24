package org.archstudio.issueadt.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.archlight.common.ArchlightIssue;
import org.archstudio.issueadt.common.ArchlightIssueADTEvent;
import org.archstudio.issueadt.common.ArchlightIssueADTListener;
import org.archstudio.issueadt.common.IArchlightIssueADT;
import org.archstudio.xarchadt.common.ObjRef;

public class ArchlightIssueADT implements IArchlightIssueADT {

	protected List<ArchlightIssue> issueList;

	public ArchlightIssueADT() {
		issueList = new ArrayList<ArchlightIssue>();
	}

	public synchronized List<? extends ArchlightIssue> getAllIssues() {
		return Collections.unmodifiableList(new ArrayList<ArchlightIssue>(issueList));
	}

	public synchronized List<? extends ArchlightIssue> getAllIssues(ObjRef documentRootRef) {
		List<ArchlightIssue> matchingList = new ArrayList<ArchlightIssue>();
		for (ArchlightIssue issue : issueList) {
			if ((issue.getDocumentRootRef() != null) && (issue.getDocumentRootRef().equals(documentRootRef))) {
				matchingList.add(issue);
			}
		}
		return Collections.unmodifiableList(matchingList);
	}

	public synchronized List<? extends ArchlightIssue> getAllIssues(String toolID) {
		List<ArchlightIssue> matchingList = new ArrayList<ArchlightIssue>();
		for (ArchlightIssue issue : issueList) {
			if ((issue.getToolID() != null) && (issue.getToolID().equals(toolID))) {
				matchingList.add(issue);
			}
		}
		return Collections.unmodifiableList(matchingList);
	}

	public synchronized List<? extends ArchlightIssue> getAllIssues(ObjRef documentRef, String toolID) {
		List<ArchlightIssue> matchingList = new ArrayList<ArchlightIssue>();
		for (ArchlightIssue issue : issueList) {
			if ((issue.getDocumentRootRef() != null) && (issue.getDocumentRootRef().equals(documentRef))) {
				if ((issue.getToolID() != null) && (issue.getToolID().equals(toolID))) {
					matchingList.add(issue);
				}
			}
		}
		return Collections.unmodifiableList(matchingList);
	}

	public synchronized List<? extends ArchlightIssue> getAllIssuesByTestUID(ObjRef documentRef, String testUID) {
		List<ArchlightIssue> matchingList = new ArrayList<ArchlightIssue>();
		for (ArchlightIssue issue : issueList) {
			if ((issue.getDocumentRootRef() != null) && (issue.getDocumentRootRef().equals(documentRef))) {
				if ((issue.getTestUID() != null) && (issue.getToolID().equals(testUID))) {
					matchingList.add(issue);
				}
			}
		}
		return Collections.unmodifiableList(matchingList);
	}

	public synchronized List<? extends ArchlightIssue> getAllIssues(ObjRef documentRef, String toolID, String testUID) {
		List<ArchlightIssue> matchingList = new ArrayList<ArchlightIssue>();
		for (ArchlightIssue issue : issueList) {
			if ((issue.getDocumentRootRef() != null) && (issue.getDocumentRootRef().equals(documentRef))) {
				if ((issue.getToolID() != null) && (issue.getToolID().equals(toolID))) {
					if ((issue.getTestUID() != null) && (issue.getTestUID().equals(testUID))) {
						matchingList.add(issue);
					}
				}
			}
		}
		return Collections.unmodifiableList(matchingList);
	}

	public void addIssues(Collection<? extends ArchlightIssue> issues) {
		issueList.addAll(issues);
		fireIssuesAdded(issues);
	}

	public void removeIssues(Collection<? extends ArchlightIssue> issues) {
		issueList.removeAll(issues);
		fireIssuesRemoved(issues);
	}

	protected List<ArchlightIssueADTListener> listeners = new CopyOnWriteArrayList<ArchlightIssueADTListener>();

	public void addArchlightIssueADTListener(ArchlightIssueADTListener l) {
		listeners.add(l);
	}

	public void removeArchlightIssueADTListener(ArchlightIssueADTListener l) {
		listeners.remove(l);
	}

	protected void fireIssuesAdded(Collection<? extends ArchlightIssue> issues) {
		fireEvent(ArchlightIssueADTEvent.EventType.ISSUES_ADDED, issues);
	}

	protected void fireIssuesRemoved(Collection<? extends ArchlightIssue> issues) {
		fireEvent(ArchlightIssueADTEvent.EventType.ISSUES_REMOVED, issues);
	}

	protected void fireEvent(ArchlightIssueADTEvent.EventType type, Collection<? extends ArchlightIssue> issues) {
		ArchlightIssueADTListener[] ls = (ArchlightIssueADTListener[]) listeners.toArray(new ArchlightIssueADTListener[listeners.size()]);
		ArchlightIssueADTEvent evt = new ArchlightIssueADTEvent(type, issues);
		for (int i = 0; i < ls.length; i++) {
			((ArchlightIssueADTListener) ls[i]).issueADTChanged(evt);
		}
	}

}
