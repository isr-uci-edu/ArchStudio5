package org.archstudio.xarchadt.common;

public class BasicXArchADTSubstitutionHint implements IXArchADTSubstitutionHint {
	
	protected String extensionNsURI;
	protected String extensionTypeName;
	
	protected String targetNsURI;
	protected String targetTypeName;
	
	public BasicXArchADTSubstitutionHint(String extensionNsURI, String extensionTypeName, String targetNsURI, String targetTypeName) {
		this.extensionNsURI = extensionNsURI;
		this.extensionTypeName = extensionTypeName;
		this.targetNsURI = targetNsURI;
		this.targetTypeName = targetTypeName;
	}

	@Override
	public String getSourceNsURI() {
		return extensionNsURI;
	}

	@Override
	public String getSourceTypeName() {
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
