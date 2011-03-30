package org.archstudio.xarchadt.common;

public class BasicXArchADTFeature implements IXArchADTFeature {

	private final String name;
	private final FeatureType featureType;
	private final boolean reference;
	private final String factoryName;
	private final String featureClassName;
	private final Class<?> featureClass;
	
	public BasicXArchADTFeature(String name, FeatureType featureType, boolean reference, String factoryName, String featureClassName, Class<?> featureClass) {
	    this.name = name;
	    this.featureType = featureType;
	    this.reference = reference;
	    this.factoryName = factoryName;
	    this.featureClassName = featureClassName;
	    this.featureClass = featureClass;
    }

	public String getName() {
    	return name;
    }

	public FeatureType getType() {
    	return featureType;
    }

	public boolean isReference() {
    	return reference;
    }
	
	public String getFactoryName() {
		return factoryName;
	}

	public String getFeatureClassName() {
    	return featureClassName;
    }
	
	public Class<?> getFeatureClass() {
		return featureClass;
	}
}
