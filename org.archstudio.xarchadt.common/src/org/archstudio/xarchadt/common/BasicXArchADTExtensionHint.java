package org.archstudio.xarchadt.common;

public class BasicXArchADTExtensionHint implements IXArchADTExtensionHint {
	
	protected String extensionNsURI;
	protected String extensionTypeName;
	
	protected String targetNsURIName;
	protected String targetTypeName;
	
	public BasicXArchADTExtensionHint(String extensionNsURI, String extensionTypeName, String targetNsURI, String targetTypeName) {
		this.extensionNsURI = extensionNsURI;
		this.extensionTypeName = extensionTypeName;
		this.targetNsURIName = targetNsURI;
		this.targetTypeName = targetTypeName;
	}

	public String getExtensionFactoryName() {
		return extensionNsURI;
	}

	public String getExtensionTypeName() {
		return extensionTypeName;
	}

	public String getTargetNsURI() {
		return targetNsURIName;
	}

	public String getTargetTypeName() {
		return targetTypeName;
	}

	@Override
	public String toString() {
		return "BasicXArchADTExtensionHint [extensionFactoryName="
			+ extensionNsURI + ", extensionTypeName="
			+ extensionTypeName + ", targetFactoryName="
			+ targetNsURIName + ", targetTypeName=" + targetTypeName
			+ "]";
	}

	@Override
	public String getExtensionNsURI() {
		// TODO Auto-generated method stub
		return null;
	}
}
