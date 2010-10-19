package org.archstudio.myx.fw;

public class MyxBrickNotFoundException extends Exception {
	public MyxBrickNotFoundException(String brickName) {
		super("Can't find brick implementation for " + brickName);
	}

	public MyxBrickNotFoundException(String brickName, Throwable cause) {
		super("Can't find brick implementation for " + brickName, cause);
	}
}
