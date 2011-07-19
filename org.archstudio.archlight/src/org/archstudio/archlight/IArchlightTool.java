package org.archstudio.archlight;

import java.util.Collection;

import org.archstudio.xarchadt.ObjRef;

public interface IArchlightTool {
	public String getToolID();

	public void runTests(ObjRef documentRootRef, Collection<String> testUIDs);

	public void reloadTests();
}
