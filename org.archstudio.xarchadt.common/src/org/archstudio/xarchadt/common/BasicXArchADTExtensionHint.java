package org.archstudio.xarchadt.common;

public class BasicXArchADTExtensionHint implements IXArchADTExtensionHint {
	protected String extensionFactoryName;
	protected String extensionTypeName;
	
	protected String targetFactoryName;
	protected String targetTypeName;
	
	public BasicXArchADTExtensionHint(String extensionFactoryName, String extensionTypeName, String targetFactoryName, String targetTypeName) {
		this.extensionFactoryName = extensionFactoryName;
		this.extensionTypeName = extensionTypeName;
		this.targetFactoryName = targetFactoryName;
		this.targetTypeName = targetTypeName;
	}

	public String getExtensionFactoryName() {
		return extensionFactoryName;
	}

	public String getExtensionTypeName() {
		return extensionTypeName;
	}

	public String getTargetFactoryName() {
		return targetFactoryName;
	}

	public String getTargetTypeName() {
		return targetTypeName;
	}

	@Override
	public String toString() {
		return "BasicXArchADTExtensionHint [extensionFactoryName="
			+ extensionFactoryName + ", extensionTypeName="
			+ extensionTypeName + ", targetFactoryName="
			+ targetFactoryName + ", targetTypeName=" + targetTypeName
			+ "]";
	}
}
