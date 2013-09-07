package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Neck extends ComplexTerm {

	final Set<VariableTerm> allVariables;

	public Neck(String functor, List<? extends Term> terms) {
		super(functor, 2, terms);
		allVariables = PrologUtils.extractVariables(Sets.<VariableTerm> newHashSet(), this);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, final Term source, final Map<VariableTerm, Term> variables) {

		final Map<VariableTerm, Term> temporaryVariables = Maps.newHashMapWithExpectedSize(allVariables.size());
		for (VariableTerm v : allVariables) {
			temporaryVariables.put(v, PrologUtils.getTemporaryVariableTerm());
		}
		Neck n = (Neck) resolve(proofContext, temporaryVariables);

		final UnificationContext context = new UnificationContext(proofContext, source, n.getTerm(0), variables);
		if (unificationEngine.unifies(proofContext, context)) {
			return Iterables.transform(PrologUtils.resolveExecutable(proofContext, n.getTerm(1), context.variables)
					.execute(proofContext, unificationEngine, source, context.variables),
					new Function<Map<VariableTerm, Term>, Map<VariableTerm, Term>>() {
						@Override
						public Map<VariableTerm, Term> apply(Map<VariableTerm, Term> input) {
							for (Entry<VariableTerm, Term> e : input.entrySet()) {
								e.setValue(e.getValue().resolve(proofContext, input));
							}
							for (Term temporaryVariable : temporaryVariables.values()) {
								input.remove(temporaryVariable);
							}
							return input;
						}
					});
		}

		return Collections.emptyList();
	}
}
