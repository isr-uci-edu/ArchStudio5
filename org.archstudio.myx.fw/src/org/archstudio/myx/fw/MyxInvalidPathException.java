package org.archstudio.myx.fw;

import java.util.List;

public class MyxInvalidPathException extends IllegalArgumentException {

	private static final long serialVersionUID = 7379950843270019295L;

	public MyxInvalidPathException(List<? extends IMyxName> path) {
		super("Invalid path: " + MyxUtils.pathToString(path));
	}

}
