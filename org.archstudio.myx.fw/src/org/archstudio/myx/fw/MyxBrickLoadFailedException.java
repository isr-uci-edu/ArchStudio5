package org.archstudio.myx.fw;

public class MyxBrickLoadFailedException extends Exception {

	private static final long serialVersionUID = 6592712041485825992L;

	public MyxBrickLoadFailedException(String brickName, Throwable cause) {
		super("Brick load failed for brick: " + brickName, cause);
	}

}
