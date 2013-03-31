package org.archstudio.prolog.op.iso;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.AbstractIterator;

public class Precedes extends ComplexTerm implements Executable {

	public Precedes(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, final Term source, final Map<VariableTerm, Term> variables) {

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {

				return new AbstractIterator<Map<VariableTerm, Term>>() {

					Iterator<Map<VariableTerm, Term>> term0Variables = null;
					int term0Index = Integer.MAX_VALUE;
					Iterator<Map<VariableTerm, Term>> term1Variables = null;

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (term0Variables == null) {
								ComplexTerm t = resolveComplexTerm(getTerm(0), variables);
								term0Variables = t.execute(proofContext, unificationEngine, t, variables).iterator();
							}
							if ((term1Variables == null || !term1Variables.hasNext()) && term0Variables.hasNext()) {
								Map<VariableTerm, Term> variables = term0Variables.next();
								term0Index = getIndex(getTerm(0), variables);
								ComplexTerm t = resolveComplexTerm(getTerm(1), variables);
								term1Variables = t.execute(proofContext, unificationEngine, t, variables).iterator();
							}
							if (term1Variables != null && term1Variables.hasNext()) {
								Map<VariableTerm, Term> variables = term1Variables.next();
								int term1Index = getIndex(getTerm(1), variables);
								if (term0Index < term1Index) {
									return variables;
								}
								continue;
							}
							return endOfData();
						}
					}

					private int getIndex(Term term, Map<VariableTerm, Term> variables) {
						return proofContext.getIndex(resolveComplexTerm(term, variables).resolve(variables));
					}
				};
			}
		};
	}
}
