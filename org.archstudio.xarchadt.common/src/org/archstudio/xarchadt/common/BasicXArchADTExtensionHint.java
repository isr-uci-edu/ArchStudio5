package org.archstudio.xarchadt.common;

public class BasicXArchADTExtensionHint implements IXArchADTExtensionHint {
	
	protected String extensionNsURI;
	protected String extensionTypeName;
	
	protected String targetNsURI;
	protected String targetTypeName;
	
	public BasicXArchADTExtensionHint(String extensionNsURI, String extensionTypeName, String targetNsURI, String targetTypeName) {
		this.extensionNsURI = extensionNsURI;
		this.extensionTypeName = extensionTypeName;
		this.targetNsURI = targetNsURI;
		this.targetTypeName = targetTypeName;
	}

	@Override
	public String getExtensionNsURI() {
		return extensionNsURI;
	}

	@Override
	public String getExtensionTypeName() {
		return extensionTypeName;
	}

	@Override
	public String getTargetNsURI() {
		return targetNsURI;
	}

	@Override
	public String getTargetTypeName() {
		return targetTypeName;
	}
}
