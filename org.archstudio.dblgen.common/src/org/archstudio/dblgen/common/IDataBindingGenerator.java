package org.archstudio.dblgen.common;

import java.io.IOException;
import java.util.List;

public interface IDataBindingGenerator {
	public List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings, String projectName) throws PackageComputeException, IOException;
}
