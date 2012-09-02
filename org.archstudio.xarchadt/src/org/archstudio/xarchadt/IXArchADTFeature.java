package org.archstudio.xarchadt;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IXArchADTFeature extends java.io.Serializable {
	public enum FeatureType {
		ATTRIBUTE, ELEMENT_SINGLE, ELEMENT_MULTIPLE
	};

	public enum ValueType {
		OBJECT, STRING, ENUMERATION
	};

	public String getNsURI();

	public String getTypeName();

	public String getName();

	public FeatureType getType();

	public ValueType getValueType();

	public boolean isReference();
}
