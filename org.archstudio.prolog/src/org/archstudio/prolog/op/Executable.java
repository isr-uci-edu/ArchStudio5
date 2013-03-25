package org.archstudio.prolog.op;

import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public interface Executable extends Term {

	/**
	 * @return the resulting variables after the operation succeeds.
	 */
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables);
}
