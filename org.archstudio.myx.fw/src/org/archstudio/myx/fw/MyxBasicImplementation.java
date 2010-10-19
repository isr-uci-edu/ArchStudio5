package org.archstudio.myx.fw;

public class MyxBasicImplementation implements IMyxImplementation {
	protected MyxBasicImplementation() {
	}

	public IMyxRuntime createRuntime() {
		return new MyxBasicRuntime();
	}

}
