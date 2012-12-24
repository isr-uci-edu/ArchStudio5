package org.archstudio.archlight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.xarchadt.ObjRef;

public class ArchlightTestResult {

	protected ObjRef documentRef;
	protected String testUID;
	protected List<ArchlightIssue> issues;

	public ArchlightTestResult(ObjRef documentRef, String testUID, List<ArchlightIssue> issues) {
		super();
		this.documentRef = documentRef;
		this.testUID = testUID;
		this.issues = Collections.unmodifiableList(new ArrayList<ArchlightIssue>(issues));
	}

	public ObjRef getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(ObjRef documentRef) {
		this.documentRef = documentRef;
	}

	public List<ArchlightIssue> getIssues() {
		return issues;
	}

	public void setIssues(List<ArchlightIssue> issues) {
		this.issues = Collections.unmodifiableList(new ArrayList<ArchlightIssue>(issues));
	}

	public String getTestUID() {
		return testUID;
	}

	public void setTestUID(String testUID) {
		this.testUID = testUID;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("ArchlightTestResult[");
		buf.append("testUID=").append(testUID).append(";");
		if (issues == null) {
			buf.append("issues=null;");
		}
		else {
			int i = 0;
			for (ArchlightIssue issue : issues) {
				buf.append("issues[").append(i++).append("]=").append(issue).append(";");
			}
		}
		buf.append("];");
		return buf.toString();
	}
}
