package org.archstudio.myx.fw;

public class MyxBrickNotFoundException extends Exception {

	private static final long serialVersionUID = -6736166925374617607L;

	public MyxBrickNotFoundException(String brickName) {
		super("Can't find brick implementation for " + brickName);
	}

	public MyxBrickNotFoundException(String brickName, Throwable cause) {
		super("Can't find brick implementation for " + brickName, cause);
	}
}
