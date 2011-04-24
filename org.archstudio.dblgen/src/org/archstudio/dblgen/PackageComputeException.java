package org.archstudio.dblgen;

public class PackageComputeException extends Exception {

	public PackageComputeException(Exception cause) {
		super("Error computing packages", cause);
	}
}
