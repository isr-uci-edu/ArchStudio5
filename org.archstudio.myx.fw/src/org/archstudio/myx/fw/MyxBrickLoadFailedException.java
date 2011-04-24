package org.archstudio.myx.fw;

public class MyxBrickLoadFailedException extends Exception {

	public MyxBrickLoadFailedException(String brickName, Throwable cause) {
		super("Brick load failed for brick: " + brickName, cause);
	}

}
