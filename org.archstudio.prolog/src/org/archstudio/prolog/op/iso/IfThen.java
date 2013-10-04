package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.PrologUtils;
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

		// Note: This is also evaluated in Disjunction, see Disjunction for details

		Executable t0 = PrologUtils.resolveExecutable(proofContext, getTerm(0), variables);
		Iterator<Map<VariableTerm, Term>> t0i = t0.execute(proofContext, unificationEngine, t0, variables).iterator();
		if (t0i.hasNext()) {
			Map<VariableTerm, Term> t0v = t0i.next();
			Executable t1 = PrologUtils.resolveExecutable(proofContext, getTerm(1), t0v);
			return t1.execute(proofContext, unificationEngine, t1, t0v);
		}
		return Collections.emptyList();
	}
}
