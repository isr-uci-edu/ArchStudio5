package org.archstudio.schematron.core;

public class SchematronTestException extends Exception {

	protected String testUID;

	public SchematronTestException() {
		super();
	}

	public SchematronTestException(String message) {
		super(message);
	}

	public SchematronTestException(String message, Throwable cause) {
		super(message, cause);
	}

	public SchematronTestException(Throwable cause) {
		super(cause);
	}

	public void setTestUID(String testUID) {
		this.testUID = testUID;
	}

	public String getTestUID() {
		return testUID;
	}
}
