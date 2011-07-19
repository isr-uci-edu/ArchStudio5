package org.archstudio.myxgen;

public enum EServiceObjectDelegate {
	variable(true, false), brick(false, false), events(true, true), myxRegistry(false, true), none(false, false);

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
