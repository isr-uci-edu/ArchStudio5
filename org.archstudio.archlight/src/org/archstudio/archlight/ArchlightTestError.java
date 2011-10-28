package org.archstudio.archlight;

public class ArchlightTestError extends ArchlightNotice {

	private static final long serialVersionUID = -7323228260070733932L;

	protected String testUID;

	public ArchlightTestError(String testUID, String toolID, String message, String additionalDetail, Throwable error) {
		super(toolID, message, additionalDetail, error);
		this.testUID = testUID;
	}

	public String getTestUID() {
		return testUID;
	}

	public void setTestUID(String testUID) {
		this.testUID = testUID;
	}
}
