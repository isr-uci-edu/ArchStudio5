package org.archstudio.xarchadt.common;

import java.util.Collections;
import java.util.Map;

public class BasicXArchADTTypeMetadata implements IXArchADTTypeMetadata {

	private static final long serialVersionUID = 5091502840120062712L;

	private final String nsURI;
	private final String nsPrefix;
	private final String typeName;
	private final Map<String, IXArchADTFeature> features;

	@Deprecated
	private final Class<?> elementClass;
	@Deprecated
	private final Class<?> instanceClass;

	public BasicXArchADTTypeMetadata(String nsURI, String nsPrefix, String typeName,
			Map<String, IXArchADTFeature> features, Class<?> elementClass, Class<?> instanceClass) {
		super();
		this.nsURI = nsURI;
		this.nsPrefix = nsPrefix;
		this.typeName = typeName;
		this.features = Collections.unmodifiableMap(features);
		this.elementClass = elementClass;
		this.instanceClass = instanceClass;
	}

	public String getNsURI() {
		return nsURI;
	}

	public String getNsPrefix() {
		return nsPrefix;
	}

	public String getTypeName() {
		return typeName;
	}

	public Map<String, IXArchADTFeature> getFeatures() {
		return features;
	}

	@Override
	public Class<?> getElementClass() {
		return elementClass;
	}

	@Override
	public String getInstanceTypeName() {
		return null;
	}

	@Override
	public Class<?> getInstanceClass() {
		return instanceClass;
	}
}
