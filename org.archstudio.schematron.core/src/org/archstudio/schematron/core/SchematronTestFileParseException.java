package org.archstudio.schematron.core;

public class SchematronTestFileParseException extends Exception implements java.io.Serializable {
	public SchematronTestFileParseException(String message) {
		super(message);
	}

	public SchematronTestFileParseException(String message, Throwable t) {
		super(message, t);
	}
}
