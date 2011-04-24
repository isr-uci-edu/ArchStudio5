package org.archstudio.xarchadt;

import java.util.Map;

public interface IXArchADTPackageMetadata {
	public String getNsURI();
	public Map<String, IXArchADTTypeMetadata> getTypeMetadata();
}
