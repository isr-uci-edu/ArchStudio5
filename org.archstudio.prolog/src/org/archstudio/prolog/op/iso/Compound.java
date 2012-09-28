package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Compound extends Operation {

	public Compound(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 1);
	}

	@Override
	public Map<VariableTerm, Term> execute(ProofEngine proofEngine, ProofContext proofContext,
			UnificationEngine unificationEngine, Map<VariableTerm, Term> variables) {
		Term t = getTerm(0);
		if (t instanceof VariableTerm) {
			t = variables.get(t);
		}
		return t instanceof ComplexTerm && ((ComplexTerm) t).getArity() > 0 ? variables : null;
	}

}
