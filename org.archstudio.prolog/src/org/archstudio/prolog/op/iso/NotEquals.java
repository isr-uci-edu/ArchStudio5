package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class NotEquals extends ComplexTerm implements Operation {

	public NotEquals(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 2);
	}

	@Override
	public Map<VariableTerm, Term> execute(ProofEngine proofEngine, ProofContext proofContext,
			UnificationEngine unificationEngine, Map<VariableTerm, Term> variables) {
		return unificationEngine.unify(new UnificationContext(getTerm(0), getTerm(1), variables)) ? null : variables;
	}

}
