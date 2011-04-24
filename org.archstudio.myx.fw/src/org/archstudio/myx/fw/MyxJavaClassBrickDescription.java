package org.archstudio.myx.fw;

public class MyxJavaClassBrickDescription implements IMyxBrickDescription {

	protected String mainBrickClassName;

	public MyxJavaClassBrickDescription(String mainBrickClassName) {
		this.mainBrickClassName = mainBrickClassName;
	}

	public String getMainBrickClassName() {
		return mainBrickClassName;
	}

}
