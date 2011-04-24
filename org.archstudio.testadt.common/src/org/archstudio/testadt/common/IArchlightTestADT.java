package org.archstudio.testadt.common;

import java.util.Collection;
import java.util.List;

import org.archstudio.archlight.common.ArchlightTest;

public interface IArchlightTestADT{

	public List<? extends ArchlightTest> getAllTests();
	public List<? extends ArchlightTest> getAllTests(String toolID);
	public ArchlightTest getTest(String testUID);
	public void addTests(Collection<? extends ArchlightTest> tests);
	public void removeTests(Collection<? extends ArchlightTest> tests);

}