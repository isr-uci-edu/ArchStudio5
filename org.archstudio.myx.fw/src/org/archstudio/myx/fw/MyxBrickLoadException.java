package org.archstudio.myx.fw;

import java.util.List;

public class MyxBrickLoadException extends Exception {

	private static final long serialVersionUID = -5199522433909252367L;

	protected List<? extends Throwable> causes;

	public MyxBrickLoadException(String brickName, String reason, List<? extends Throwable> causes) {
		super("Error loading brick: " + brickName + "; reason given was: " + (reason == null ? "[none]" : reason));
		this.causes = causes;
		if (causes != null && causes.size() > 0) {
			this.initCause(causes.get(causes.size() - 1));
		}
	}

	public List<? extends Throwable> getCauses() {
		return causes;
	}

}
