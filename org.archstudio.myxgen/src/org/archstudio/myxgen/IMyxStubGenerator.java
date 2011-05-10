package org.archstudio.myxgen;

import java.io.IOException;
import java.util.List;

public interface IMyxStubGenerator {
	public List<MyxStubGenerationStatus> generateStubs(String projectName) throws MyxStubException, IOException;
}
