package org.archstudio.graphlayout.common.gui;

class DataValidationException extends Exception {
	public DataValidationException() {
	}

	public DataValidationException(String message) {
		super(message);
	}

	public DataValidationException(Throwable cause) {
		super(cause);
	}

	public DataValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
