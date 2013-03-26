package org.archstudio.prolog.op;

import java.util.Map;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public interface Evaluable extends Term {

	/**
	 * @return the value of the expression
	 */
	public Number evaluate(Map<VariableTerm, Term> variables);

}
