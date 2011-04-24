package org.archstudio.issueadt;

import java.util.Collection;
import java.util.List;

import org.archstudio.archlight.ArchlightIssue;
import org.archstudio.xarchadt.ObjRef;

public interface IArchlightIssueADT {

	public List<? extends ArchlightIssue> getAllIssues();

	public List<? extends ArchlightIssue> getAllIssues(ObjRef documentRootRef);

	public List<? extends ArchlightIssue> getAllIssues(String toolID);

	public List<? extends ArchlightIssue> getAllIssues(ObjRef documentRootRef, String toolID);

	public List<? extends ArchlightIssue> getAllIssuesByTestUID(ObjRef documentRootRef, String testUID);

	public List<? extends ArchlightIssue> getAllIssues(ObjRef documentRootRef, String toolID, String testUID);

	public void addIssues(Collection<? extends ArchlightIssue> issues);

	public void removeIssues(Collection<? extends ArchlightIssue> issues);

}