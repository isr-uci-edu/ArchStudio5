package org.archstudio.prolog.op;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Equals extends Operation {

	public Equals(List<Term> terms) {
		super("==", terms);
		checkArgument(terms.size() == 2);
	}

	public Equals(Term... terms) {
		super("==", terms);
		checkArgument(terms.length == 2);
	}

	@Override
	public boolean execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Map<VariableTerm, Term> variables) {
		return unificationEngine.unify(new UnificationContext(getTerm(0), getTerm(1), variables));
	}

}
