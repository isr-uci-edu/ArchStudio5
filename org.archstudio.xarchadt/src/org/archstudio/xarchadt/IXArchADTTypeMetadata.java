package org.archstudio.xarchadt;

import java.util.Map;

public interface IXArchADTTypeMetadata extends java.io.Serializable {
	public String getNsURI();
	public String getTypeName();
	public Map<String, IXArchADTFeature> getFeatures();
	public boolean isAbstract();
}
