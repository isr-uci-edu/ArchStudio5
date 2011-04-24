package org.archstudio.xarchadt;

public interface IXArchADTSubstitutionHint {
	public enum HintType {
		EXTENSION,
		SUBSTITUTION_GROUP
	};
	
	public HintType getHintType();

	public String getSourceNsURI();
	public String getSourceTypeName();
	
	public String getTargetNsURI();
	public String getTargetTypeName();
}