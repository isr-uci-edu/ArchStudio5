package org.archstudio.prolog.op.iso;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class Neck extends ComplexTerm {

	public Neck(String functor, List<? extends Term> terms) {
		super(functor, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext,
			final UnificationEngine unificationEngine, final Term source, final Map<VariableTerm, Term> variables) {

		final ComplexTerm head = resolveComplexTerm(getTerm(0), variables);
		Executable body = resolveOperation(getTerm(1), variables);

		Map<VariableTerm, Term> localVariables = toLocal(head, variables, (ComplexTerm) source);
		Iterable<Map<VariableTerm, Term>> localResults = body.execute(proofContext, unificationEngine, head,
				localVariables);
		return Iterables.transform(localResults, new Function<Map<VariableTerm, Term>, Map<VariableTerm, Term>>() {
			@Override
			public Map<VariableTerm, Term> apply(Map<VariableTerm, Term> localVariables) {
				return toGlobal(variables, (ComplexTerm) source, localVariables, head);
			}
		});
	}

	protected Map<VariableTerm, Term> toLocal(ComplexTerm localTerm, Map<VariableTerm, Term> globalVariables,
			ComplexTerm globalTerm) {
		Map<VariableTerm, Term> localVariables = Maps.newHashMap();
		for (int j = 0; j < globalTerm.getArity(); j++) {
			Term g = globalTerm.getTerm(j);
			Term l = localTerm.getTerm(j);
			if (!(g instanceof VariableTerm) && l instanceof VariableTerm) {
				localVariables.put((VariableTerm) l, g);
			}
			else if (globalVariables.containsKey(g) && l instanceof VariableTerm) {
				localVariables.put((VariableTerm) l, globalVariables.get(g));
			}
		}
		return localVariables;
	}

	protected Map<VariableTerm, Term> toGlobal(Map<VariableTerm, Term> globalVariables, ComplexTerm globalTerm,
			Map<VariableTerm, Term> localVariables, ComplexTerm localTerm) {
		Map<VariableTerm, Term> newVariables = Maps.newHashMap(globalVariables);
		for (int j = 0; j < globalTerm.getArity(); j++) {
			Term g = globalTerm.getTerm(j);
			Term l = localTerm.getTerm(j);
			if (g instanceof VariableTerm) {
				if (l instanceof VariableTerm) {
					newVariables.put((VariableTerm) g, localVariables.get(l));
				}
				else {
					newVariables.put((VariableTerm) g, l);
				}
			}
		}
		return newVariables;
	}
}
