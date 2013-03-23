package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
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

public class Unifiable extends ComplexTerm implements Operation {

	public Unifiable(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 2);
	}

	public Unifiable(String name, Term... terms) {
		this(name, Arrays.asList(terms));
		checkArgument(terms.length == 2);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		UnificationContext context = new UnificationContext(getTerm(0), getTerm(1), variables);
		if (unificationEngine.unifies(context)) {
			return Utils.asList(context.variables);
		}
		return Collections.emptyList();
	}
}
