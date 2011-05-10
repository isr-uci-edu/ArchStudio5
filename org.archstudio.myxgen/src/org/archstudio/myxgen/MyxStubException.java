package org.archstudio.myxgen;

public class MyxStubException extends Exception {

	private static final long serialVersionUID = -1085504646526449012L;

	public MyxStubException(Exception cause) {
		super("Error computing packages", cause);
	}
}
