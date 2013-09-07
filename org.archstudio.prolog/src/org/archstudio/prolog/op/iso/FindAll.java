package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;

public class FindAll extends ComplexTerm implements Executable {

	public FindAll(String name, List<? extends Term> terms) {
		super(name, 3, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		Term template = getTerm(0);
		Term goal = getTerm(1);
		Term bag = getTerm(2);

		List<Term> results = Lists.newArrayList();
		for (Map<VariableTerm, Term> result : PrologUtils.resolveExecutable(proofContext, goal, variables).execute(
				proofContext, unificationEngine, goal, variables)) {
			results.add(template.resolve(proofContext, result));
		}

		UnificationContext context = new UnificationContext(proofContext, bag, ListTerm.asListTerm(results), variables);
		if (unificationEngine.unifies(proofContext, context)) {
			return Collections.singleton(context.variables);
		}
		return Collections.emptyList();
	}
}
