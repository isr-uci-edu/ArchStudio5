package org.archstudio.dblgen.common;

public class PackageComputeException extends Exception {

	public PackageComputeException(Exception cause) {
		super("Error computing packages", cause);
	}
}
