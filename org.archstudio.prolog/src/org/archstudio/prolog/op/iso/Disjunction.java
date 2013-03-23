package org.archstudio.prolog.op.iso;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.AbstractIterator;

public class Disjunction extends ComplexTerm implements Operation {

	public Disjunction(String name, List<? extends Term> terms) {
		super(name, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {
				return new AbstractIterator<Map<VariableTerm, Term>>() {

					int termsIndex = 0;
					Iterator<Map<VariableTerm, Term>> variablesIterator = Collections
							.<Map<VariableTerm, Term>> emptyList().iterator();

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (variablesIterator.hasNext()) {
								return variablesIterator.next();
							}
							if (termsIndex == getTermsSize()) {
								return endOfData();
							}
							Term term = getTerm(termsIndex++);
							if (term instanceof Operation) {
								variablesIterator = ((Operation) term).execute(proofContext, unificationEngine, term,
										variables).iterator();
								continue;
							}
							else {
								throw new UnsupportedOperationException(term.toString());
							}
						}
					}
				};
			}
		};
	}
}
