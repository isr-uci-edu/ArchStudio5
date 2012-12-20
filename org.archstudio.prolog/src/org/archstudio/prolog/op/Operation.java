package org.archstudio.prolog.op;

import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public interface Operation extends Term {

	/**
	 * @return <code>null</code> if the operation fails, or the resulting
	 *         variables after the operation succeeds.
	 */
	public Map<VariableTerm, Term> execute(ProofEngine proofEngine, ProofContext proofContext,
			UnificationEngine unificationEngine, Map<VariableTerm, Term> variables);
}
