package org.archstudio.graphlayout.gui;

class DataValidationException extends Exception {

	private static final long serialVersionUID = -4579036059817114941L;

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
