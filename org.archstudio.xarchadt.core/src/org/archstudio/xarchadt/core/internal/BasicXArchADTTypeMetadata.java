package org.archstudio.xarchadt.core.internal;

import java.util.Collections;
import java.util.Map;

import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;

public class BasicXArchADTTypeMetadata implements IXArchADTTypeMetadata {

	private static final long serialVersionUID = 5091502840120062712L;

	private final String nsURI;
	private final String typeName;
	private final Map<String, IXArchADTFeature> features;
	private final boolean isAbstract;

	public BasicXArchADTTypeMetadata(String nsURI, String typeName, Map<String, IXArchADTFeature> features,
			boolean isAbstract) {
		super();
		this.nsURI = nsURI;
		this.typeName = typeName;
		this.features = Collections.unmodifiableMap(features);
		this.isAbstract = isAbstract;
	}

	public String getNsURI() {
		return nsURI;
	}

	public String getTypeName() {
		return typeName;
	}

	public Map<String, IXArchADTFeature> getFeatures() {
		return features;
	}

	public boolean isAbstract() {
		return isAbstract;
	}
}
