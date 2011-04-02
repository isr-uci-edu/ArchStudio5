package org.archstudio.xarchadt.common;

public class BasicXArchADTSubstitutionHint implements IXArchADTSubstitutionHint {
	
	protected String sourceNsURI;
	protected String sourceTypeName;
	
	protected String targetNsURI;
	protected String targetTypeName;
	
	public BasicXArchADTSubstitutionHint(String sourceNsURI, String sourceTypeName, String targetNsURI, String targetTypeName) {
		this.sourceNsURI = sourceNsURI;
		this.sourceTypeName = sourceTypeName;
		this.targetNsURI = targetNsURI;
		this.targetTypeName = targetTypeName;
	}

	@Override
	public String getSourceNsURI() {
		return sourceNsURI;
	}

	@Override
	public String getSourceTypeName() {
		return sourceTypeName;
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
