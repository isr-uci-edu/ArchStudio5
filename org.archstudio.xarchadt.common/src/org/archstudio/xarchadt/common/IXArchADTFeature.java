package org.archstudio.xarchadt.common;

public interface IXArchADTFeature extends java.io.Serializable {

	public enum FeatureType {
		ATTRIBUTE,
		ELEMENT_SINGLE,
		ELEMENT_MULTIPLE
	};
	
	public String getName();
	public FeatureType getType();
	public boolean isReference();
	public String getFeatureClassName();
	public Class<?> getFeatureClass();
	
}
