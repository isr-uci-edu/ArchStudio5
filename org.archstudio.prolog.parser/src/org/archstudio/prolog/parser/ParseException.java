package org.archstudio.prolog.parser;

public class ParseException extends Exception {

	private static final long serialVersionUID = 8844078035546813958L;

	public ParseException() {
		super();
	}

	public ParseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ParseException(String arg0) {
		super(arg0);
	}

	public ParseException(Throwable arg0) {
		super(arg0);
	}

}
