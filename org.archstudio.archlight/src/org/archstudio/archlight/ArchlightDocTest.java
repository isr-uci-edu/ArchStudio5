package org.archstudio.archlight;

public class ArchlightDocTest {

	protected String testUID;
	protected String testDescription;
	protected boolean isEnabled;

	public ArchlightDocTest(String testUID, String testDescription, boolean isEnabled) {
		this.testUID = testUID;
		this.testDescription = testDescription;
		this.isEnabled = isEnabled;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getTestUID() {
		return testUID;
	}

	public void setTestUID(String testUID) {
		this.testUID = testUID;
	}
}
