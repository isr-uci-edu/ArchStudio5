package org.archstudio.prolog.op;

import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public interface Evaluable extends Term {

	/**
	 * @return the value of the expression
	 */
	public Number evaluate(ProofContext proofContext, Map<VariableTerm, Term> variables);

}
