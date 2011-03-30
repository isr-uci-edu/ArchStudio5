package org.archstudio.xarchadt.common;

import java.util.Map;

public interface IXArchADTPackageMetadata {
	public String getNsURI();
	public String getNsPrefix();
	public Map<String, IXArchADTTypeMetadata> getTypeMetadata();
}
