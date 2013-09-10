package org.archstudio.prolog.op.iso;

import java.util.Collections;
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

public class Conjunction extends ComplexTerm implements Executable {

	public Conjunction(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		Executable term0 = PrologUtils.resolveExecutable(proofContext, getTerm(0), variables);
		final Iterator<Map<VariableTerm, Term>> term0Variables = term0.execute(proofContext, unificationEngine, term0,
				variables).iterator();

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {

				return new AbstractIterator<Map<VariableTerm, Term>>() {

					Iterator<Map<VariableTerm, Term>> term1Variables = Collections
							.<Map<VariableTerm, Term>> emptyList().iterator();

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						if (!term1Variables.hasNext()) {
							while (term0Variables.hasNext()) {
								Map<VariableTerm, Term> variables = term0Variables.next();
								Executable term1 = PrologUtils.resolveExecutable(proofContext, getTerm(1), variables);
								term1Variables = term1.execute(proofContext, unificationEngine, term1, variables)
										.iterator();
								if (term1Variables.hasNext()) {
									break;
								}
							}
							if (!term1Variables.hasNext()) {
								return endOfData();
							}
						}
						return term1Variables.next();
					}
				};
			}
		};
	}
}
