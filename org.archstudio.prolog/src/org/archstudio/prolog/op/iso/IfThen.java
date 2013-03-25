package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class IfThen extends ComplexTerm implements Executable {

	public IfThen(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {

		Executable t0 = resolveOperation(getTerm(0), variables);
		Executable t1 = resolveOperation(getTerm(1), variables);

		Iterable<Map<VariableTerm, Term>> iterable = t0.execute(proofContext, unificationEngine, t0, variables);
		if (iterable.iterator().hasNext()) {
			return t1.execute(proofContext, unificationEngine, t1, iterable.iterator().next());
		}
		return Collections.emptyList();
	}
}
