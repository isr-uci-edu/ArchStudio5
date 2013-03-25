package org.archstudio.prolog.op;

import java.util.Map;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public interface Evaluable {

	public Number evaluate(Map<VariableTerm, Term> variables);

}
