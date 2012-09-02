package org.archstudio.xarchadt;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IXArchADTSubstitutionHint {
	public enum HintType {
		EXTENSION, SUBSTITUTION_GROUP
	};

	public HintType getHintType();

	public String getSourceNsURI();

	public String getSourceTypeName();

	public String getTargetNsURI();

	public String getTargetTypeName();
}