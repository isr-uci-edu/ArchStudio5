package org.archstudio.xarchadt.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicXArchADTFactoryMetadata implements IXArchADTFactoryMetadata{

	private final String factoryName;
	private final List<IXArchADTFactoryElementMetadata> factoryElementMetadata;
	
	public BasicXArchADTFactoryMetadata(String factoryName, List<IXArchADTFactoryElementMetadata> factoryElementMetadata) {
	    super();
	    this.factoryName = factoryName;
	    this.factoryElementMetadata = Collections.unmodifiableList(new ArrayList<IXArchADTFactoryElementMetadata>(factoryElementMetadata));
    }

	public String getFactoryName() {
    	return factoryName;
    }

	public List<IXArchADTFactoryElementMetadata> getFactoryElementMetadata() {
    	return factoryElementMetadata;
    }
	
}
