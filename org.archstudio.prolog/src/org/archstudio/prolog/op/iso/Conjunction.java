package org.archstudio.prolog.op.iso;

import java.util.Arrays;
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
import com.google.common.collect.Lists;

public class Conjunction extends ComplexTerm implements Operation {

	public Conjunction(String name, List<? extends Term> terms) {
		super(name, terms);
	}

	public Conjunction(String name, Term... terms) {
		super(name, Arrays.asList(terms));
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {

				final List<Iterator<Map<VariableTerm, Term>>> indexVariables = Lists.newArrayList();
				indexVariables.add(Collections.singleton(variables).iterator());

				return new AbstractIterator<Map<VariableTerm, Term>>() {

					int termsIndex = 0;

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							Iterator<Map<VariableTerm, Term>> variablesIterator = indexVariables.get(termsIndex);
							if (variablesIterator.hasNext()) {
								if (termsIndex < getTermsSize()) {
									Term term = getTerm(termsIndex++);
									if (term instanceof Operation) {
										indexVariables.add(((Operation) term).execute(proofContext, unificationEngine,
												term, variablesIterator.next()).iterator());
										continue;
									}
									else {
										throw new UnsupportedOperationException(term.toString());
									}
								}
								else {
									return variablesIterator.next();
								}
							}
							indexVariables.remove(termsIndex--);
							if (termsIndex == 0) {
								return endOfData();
							}
						}
					}
				};
			}
		};
	}
}
