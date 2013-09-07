package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Sort extends ComplexTerm implements Executable {

	public Sort(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		Term in = getTerm(0).resolve(proofContext, variables);
		Term out = getTerm(1);

		Set<Term> inTerms = Sets.newHashSet(((ListTerm) in).asList());
		List<Term> outTerms = Lists.newArrayList(inTerms);
		Collections.sort(outTerms);

		UnificationContext context = new UnificationContext(proofContext, out, ListTerm.asListTerm(outTerms), variables);
		if (unificationEngine.unifies(proofContext, context)) {
			return Collections.singleton(context.variables);
		}
		return Collections.emptyList();
	}
}
