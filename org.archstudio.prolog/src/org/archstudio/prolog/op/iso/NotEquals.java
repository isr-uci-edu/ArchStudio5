package org.archstudio.prolog.op.iso;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class NotEquals extends Equals implements Executable {

	public NotEquals(String name, List<? extends Term> terms) {
		super(name, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		return negate(super.execute(proofContext, unificationEngine, source, variables), variables);
	}
}
