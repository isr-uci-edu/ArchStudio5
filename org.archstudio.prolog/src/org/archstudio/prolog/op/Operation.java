package org.archstudio.prolog.op;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public abstract class Operation extends ComplexTerm {

	public Operation(String functor, List<Term> terms) {
		super(functor, terms);
	}

	public Operation(String functor, Term... terms) {
		super(functor, terms);
	}

	public abstract boolean execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Map<VariableTerm, Term> variables);

}
