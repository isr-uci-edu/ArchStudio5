package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasAssemblyData extends IThing {
	public static final String ASSEMBLY_KIND_PROPERTY_NAME = "assemblyKind";
	public static final String INDEX_VALUE_PROPERTY_NAME = "assemblyChangedValue";

	public String getAssemblyKind();

	public int getIndexValue();

}
