package org.archstudio.xarchadt.common;

import java.util.List;

public interface IXArchADTFactoryMetadata {
	public String getFactoryName();
	public List<IXArchADTFactoryElementMetadata> getFactoryElementMetadata();
}
