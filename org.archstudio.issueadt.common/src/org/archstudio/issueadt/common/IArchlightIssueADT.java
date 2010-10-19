package org.archstudio.issueadt.common;

import java.util.Collection;
import java.util.List;

import org.archstudio.archlight.common.ArchlightIssue;
import org.archstudio.xarchadt.common.ObjRef;

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