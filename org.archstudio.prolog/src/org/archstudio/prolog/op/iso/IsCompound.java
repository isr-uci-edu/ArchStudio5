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

public class IsCompound extends ComplexTerm implements Executable {

	public IsCompound(String name, List<? extends Term> terms) {
		super(name, 1, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		Term t = getTerm(0).resolve(proofContext, variables);
		if (t instanceof ComplexTerm && ((ComplexTerm) t).getArity() > 0) {
			return Collections.singleton(variables);
		}
		return Collections.emptyList();
	}
}
