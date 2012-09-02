package org.archstudio.xarchadt;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IXArchADTTypeMetadata extends java.io.Serializable {
	public String getNsURI();

	public String getTypeName();

	public Map<String, IXArchADTFeature> getFeatures();

	public boolean isAbstract();
}
