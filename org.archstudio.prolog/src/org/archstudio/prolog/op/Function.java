package org.archstudio.prolog.op;

import java.util.Map;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public interface Function {

	public Number evaluate(Map<VariableTerm, Term> variables);

}
