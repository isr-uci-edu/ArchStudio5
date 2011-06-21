package org.archstudio.myx.osgi;

import org.archstudio.myx.java.MyxJavaClassBrickDescription;

public class MyxOSGiBrickDescription extends MyxJavaClassBrickDescription {

	protected final String osgiBundleName;

	public MyxOSGiBrickDescription(String mainBrickClassName, String osgiBundleName) {
		super(mainBrickClassName);
		this.osgiBundleName = osgiBundleName;
	}

	public String getOsgiBundleName() {
		return osgiBundleName;
	}
}
