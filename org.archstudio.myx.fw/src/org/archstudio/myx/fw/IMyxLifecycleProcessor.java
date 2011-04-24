package org.archstudio.myx.fw;

public interface IMyxLifecycleProcessor {
	public enum Operation {
		INIT, BEGIN, END, DESTROY
	};

	public void init();

	public void begin();

	public void end();

	public void destroy();
}
