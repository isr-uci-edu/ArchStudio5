package org.archstudio.xarchadt.common;

public class BasicXArchADTFeature implements IXArchADTFeature {

	private static final long serialVersionUID = -4521981013812537309L;

	private final String nsURI;
	private final String nsPrefix;
	private final String typeName;
	
	private final String name;
	private final FeatureType xADLType;
	private final boolean reference;
	@Deprecated
	private final Class<?> featureClass;

	public BasicXArchADTFeature(String name, String nsURI, String nsPrefix, String typeName, FeatureType xADLType,
			boolean reference, Class<?> featureClass) {
		this.name = name;
		this.nsURI = nsURI;
		this.nsPrefix = nsPrefix;
		this.typeName = typeName;
		this.xADLType = xADLType;
		this.reference = reference;
		this.featureClass = featureClass;
	}

	public String getNsURI() {
		return nsURI;
	}

	@Override
	public String getNsPrefix() {
		return nsPrefix;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getName() {
		return name;
	}
	
	public FeatureType getType() {
		return xADLType;
	}

	public boolean isReference() {
		return reference;
	}

	public Class<?> getFeatureClass() {
		return featureClass;
	}
}
