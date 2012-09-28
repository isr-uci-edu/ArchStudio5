package org.archstudio.prolog.test;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.SingleThreadProofEngine;
import org.junit.Before;

public class ProofTest2 extends ProofTest {

	@Override
	@Before
	public void init() {
		super.init();
		unificationEngine = new MostGeneralUnifierEngine();
		proofEngine = new SingleThreadProofEngine();
	}
}
