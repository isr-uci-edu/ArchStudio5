package org.archstudio.xarchadt.common;

import java.util.Map;

public interface IXArchADTPackageMetadata {
	public String getNsURI();
	public Map<String, IXArchADTTypeMetadata> getTypeMetadata();
}
