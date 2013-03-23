package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Neck extends ComplexTerm {

	private final Operation bodyTerm;

	public Neck(String functor, List<? extends Term> terms) {
		super(functor, terms.get(0));
		this.bodyTerm = (Operation) terms.get(1);
		checkArgument(terms.get(0) instanceof ComplexTerm);
		checkArgument(terms.size() == 2);
	}

	public Neck(ComplexTerm head, Term bodyTerm) {
		this(":-", Lists.newArrayList(head, bodyTerm));
	}

	@Override
	public String toString() {
		return getFunctor() + "(" + Joiner.on(',').join(getTerms()) + "," + bodyTerm + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (bodyTerm == null ? 0 : bodyTerm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Neck other = (Neck) obj;
		if (bodyTerm == null) {
			if (other.bodyTerm != null) {
				return false;
			}
		}
		else if (!bodyTerm.equals(other.bodyTerm)) {
			return false;
		}
		return true;
	}

	public ComplexTerm getHead() {
		return (ComplexTerm) getTerm(0);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext,
			final UnificationEngine unificationEngine, final Term source, final Map<VariableTerm, Term> variables) {

		Map<VariableTerm, Term> localVariables = toLocal(variables, (ComplexTerm) source);
		Iterable<Map<VariableTerm, Term>> localResults = bodyTerm.execute(proofContext, unificationEngine, getHead(),
				localVariables);
		return Iterables.transform(localResults, new Function<Map<VariableTerm, Term>, Map<VariableTerm, Term>>() {
			@Override
			public Map<VariableTerm, Term> apply(Map<VariableTerm, Term> localVariables) {
				return toGlobal(variables, (ComplexTerm) source, localVariables);
			}
		});
	}

	protected Map<VariableTerm, Term> toLocal(Map<VariableTerm, Term> globalVariables, ComplexTerm globalTerm) {
		ComplexTerm localTerm = getHead();

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
			Map<VariableTerm, Term> localVariables) {
		ComplexTerm localTerm = getHead();

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
