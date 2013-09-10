package org.archstudio.prolog.op.iso;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.AbstractIterator;

public class Disjunction extends ComplexTerm implements Executable {

	public Disjunction(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {
				return new AbstractIterator<Map<VariableTerm, Term>>() {

					int termsIndex = 0;
					Iterator<Map<VariableTerm, Term>> variablesIterator = null;

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (variablesIterator != null && variablesIterator.hasNext()) {
								return variablesIterator.next();
							}
							if (variablesIterator == null && termsIndex < getTermsSize()) {
								Term term = getTerm(termsIndex++);
								variablesIterator = PrologUtils.resolveExecutable(proofContext, term, variables)
										.execute(proofContext, unificationEngine, term, variables).iterator();
								if (!variablesIterator.hasNext()) {
									variablesIterator = null;
								}
								continue;
							}
							return endOfData();
						}
					}
				};
			}
		};
	}
}
