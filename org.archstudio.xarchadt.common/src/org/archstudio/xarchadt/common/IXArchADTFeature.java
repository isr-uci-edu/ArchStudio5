package org.archstudio.xarchadt.common;

public interface IXArchADTFeature extends java.io.Serializable {

	public enum FeatureType {
		ATTRIBUTE, //
		ELEMENT_SINGLE, //
		ELEMENT_MULTIPLE
	};

	public String getNsURI();

	public String getNsPrefix();

	public String getTypeName();

	public String getName();
	
	public FeatureType getType();

	public boolean isReference();

	/**
	 * @deprecated Prevents use of non-generated models, i.e., when there is no underlying Java class.
	 */
	@Deprecated
	public Class<?> getFeatureClass();
}
