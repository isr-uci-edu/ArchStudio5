package org.archstudio.myx.fw;

import java.util.List;

public class MyxInvalidPathException extends IllegalArgumentException {

	public MyxInvalidPathException(List<? extends IMyxName> path) {
		super("Invalid path: " + MyxUtils.pathToString(path));
	}

}
