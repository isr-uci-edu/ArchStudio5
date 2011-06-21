package org.archstudio.myx.java;

import org.archstudio.myx.fw.IMyxBrickDescription;

public class MyxJavaClassBrickDescription implements IMyxBrickDescription {

	protected String mainBrickClassName;

	public MyxJavaClassBrickDescription(String mainBrickClassName) {
		this.mainBrickClassName = mainBrickClassName;
	}

	public String getMainBrickClassName() {
		return mainBrickClassName;
	}

}
