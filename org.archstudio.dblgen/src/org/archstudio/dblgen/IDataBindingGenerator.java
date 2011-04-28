package org.archstudio.dblgen;

import java.util.List;

public interface IDataBindingGenerator {
	public List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings, String projectName);
}
