package org.archstudio.xarchadt.common;

public class BasicXArchADTFeature implements IXArchADTFeature {

	private static final long serialVersionUID = -4521981013812537309L;

	private final String nsURI;
	private final String typeName;
	
	private final String name;
	private final FeatureType featureType;
	private final ValueType valueType;
	private final boolean reference;

	public BasicXArchADTFeature(String name, String nsURI, String typeName, FeatureType featureType, ValueType valueType, boolean reference) {
		this.name = name;
		this.nsURI = nsURI;
		this.typeName = typeName;
		this.featureType = featureType;
		this.valueType = valueType;
		this.reference = reference;
	}

	public String getNsURI() {
		return nsURI;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getName() {
		return name;
	}
	
	public FeatureType getType() {
		return featureType;
	}
	
	public ValueType getValueType() {
		return valueType;
	}

	public boolean isReference() {
		return reference;
	}
}
