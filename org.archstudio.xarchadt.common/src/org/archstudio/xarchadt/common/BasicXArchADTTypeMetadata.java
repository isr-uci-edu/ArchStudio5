package org.archstudio.xarchadt.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicXArchADTTypeMetadata implements IXArchADTTypeMetadata {
	private final String factoryName;
	private final String instanceTypeName;
	private final String instanceClassName;
	private final Class<?> instanceClass;
	private final List<IXArchADTFeature> features;
	
	public BasicXArchADTTypeMetadata(String factoryName, String instanceTypeName, String instanceClassName, Class<?> instanceClass, List<IXArchADTFeature> features){ 
		this.factoryName = factoryName;
		this.instanceTypeName = instanceTypeName;
		this.instanceClassName = instanceClassName;
		this.instanceClass = instanceClass;
		this.features = Collections.unmodifiableList(new ArrayList<IXArchADTFeature>(features));
	}
	
	public String getFactoryName() {
		return factoryName;
	}
	
	public List<IXArchADTFeature> getFeatures(){
		return features;
	}

	public String getInstanceTypeName() {
    	return instanceTypeName;
    }

	public Class<?> getInstanceClass() {
    	return instanceClass;
    }
	
	public String getInstanceClassName() {
		return instanceClassName;
	}
}
