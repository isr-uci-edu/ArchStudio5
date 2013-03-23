package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.engine.Utils;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class NotUnifiable extends ComplexTerm implements Operation {

	public NotUnifiable(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 2);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		if (unificationEngine.unifies(new UnificationContext(getTerm(0), getTerm(1), variables))) {
			return Collections.emptyList();
		}
		return Utils.asList(variables);
	}

}
