package org.archstudio.xarchadt.common;

import java.util.List;

public interface IXArchADTTypeMetadata extends java.io.Serializable {

	public String getInstanceTypeName();
	public String getInstanceClassName();
	public Class<?> getInstanceClass();
	public List<IXArchADTFeature> getFeatures(); 
	
}
