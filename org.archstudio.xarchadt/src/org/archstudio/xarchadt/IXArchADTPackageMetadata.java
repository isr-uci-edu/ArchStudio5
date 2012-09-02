package org.archstudio.xarchadt;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IXArchADTPackageMetadata {
	public String getNsURI();

	public Map<String, IXArchADTTypeMetadata> getTypeMetadata();
}
