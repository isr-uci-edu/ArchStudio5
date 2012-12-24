package org.archstudio.myx.fw;

public class MyxBasicImplementation implements IMyxImplementation {
	protected MyxBasicImplementation() {
	}

	@Override
	public IMyxRuntime createRuntime() {
		return new MyxBasicRuntime();
	}

}
