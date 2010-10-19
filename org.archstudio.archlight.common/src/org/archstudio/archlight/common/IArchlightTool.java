package org.archstudio.archlight.common;

import java.util.Collection;

import org.archstudio.xarchadt.common.ObjRef;

public interface IArchlightTool{
	public String getToolID();
	public void runTests(ObjRef documentRootRef, Collection<String> testUIDs);
	public void reloadTests();
}
