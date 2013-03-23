package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.engine.Utils;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Iterables;

public class Not extends ComplexTerm implements Operation {

	public Not(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 1);
		checkArgument(terms.get(0) instanceof Operation);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		if (Iterables.isEmpty(((Operation) getTerm(0)).execute(proofContext, unificationEngine, this, variables))) {
			return Utils.asList(variables);
		}
		return Collections.emptyList();
	}
}
