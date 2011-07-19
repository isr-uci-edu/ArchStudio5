package org.archstudio.myxgen.eclipse;

import org.archstudio.myx.osgi.MyxOSGiBrickDescription;

public class MyxGenBrickDescription extends MyxOSGiBrickDescription {

	protected final String myxGenBrickID;

	public MyxGenBrickDescription(String mainBrickClassName, String osgiBundleName, String myxGenBrickID) {
		super(mainBrickClassName, osgiBundleName);
		this.myxGenBrickID = myxGenBrickID;
	}

	public String getMyxGenBrickID() {
		return myxGenBrickID;
	}

}
