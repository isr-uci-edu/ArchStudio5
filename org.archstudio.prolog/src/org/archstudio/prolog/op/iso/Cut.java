package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Cut extends ConstantTerm implements Executable {

	public Cut(String name) {
		super(name);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		// Note: Cut is actually implemented in Conjunction, this implements the "true" part of it
		return Collections.singleton(variables);
	}
}
