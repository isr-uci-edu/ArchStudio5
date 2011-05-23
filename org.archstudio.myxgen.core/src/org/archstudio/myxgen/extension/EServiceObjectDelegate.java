package org.archstudio.myxgen.extension;

public enum EServiceObjectDelegate {
	none(false, false), variable(true, false), events(true, true), brick(false, false);

	protected final boolean needsVariable;
	protected final boolean needsProxy;

	private EServiceObjectDelegate(boolean needsVariable, boolean needsProxy) {
		this.needsVariable = needsVariable;
		this.needsProxy = needsProxy;
	}

	public boolean isNeedsVariable() {
		return needsVariable;
	}

	public boolean isNeedsProxy() {
		return needsProxy;
	}
}
