package org.archstudio.schematron.core;

public class SchematronTestFileParseException extends Exception implements java.io.Serializable {
	private static final long serialVersionUID = 8632023381415105044L;

	public SchematronTestFileParseException(String message) {
		super(message);
	}

	public SchematronTestFileParseException(String message, Throwable t) {
		super(message, t);
	}
}
