package org.archstudio.prolog.op.iso;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class IsInteger extends ComplexTerm implements Executable {

	public IsInteger(String name, List<? extends Term> terms) {
		super(name, 1, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		Term t = getTerm(0).resolve(proofContext, variables);
		if (t instanceof ConstantTerm && ((ConstantTerm) t).getValue() instanceof BigInteger) {
			return Collections.singleton(variables);
		}
		return Collections.emptyList();
	}
}
