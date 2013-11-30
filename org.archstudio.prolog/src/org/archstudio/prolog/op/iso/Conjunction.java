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

public class Conjunction extends ComplexTerm implements Executable {

	public Conjunction(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext t0ProofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		final Executable t0 = PrologUtils.resolveExecutable(t0ProofContext, getTerm(0), variables);

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {

				return new AbstractIterator<Map<VariableTerm, Term>>() {

					Iterator<Map<VariableTerm, Term>> t0Variables = t0.execute(t0ProofContext, unificationEngine, t0,
							variables).iterator();
					Iterator<Map<VariableTerm, Term>> t1Variables = PrologUtils.emptyVariablesList().iterator();

					// if term0 is the cut operator, then create a new cut context for term1
					ProofContext t1ProofContext = t0 instanceof Cut ? new ProofContext(t0ProofContext) : t0ProofContext;

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (t0ProofContext.isCancelled()) {
								return endOfData();
							}
							if (t1Variables.hasNext()) {
								return t1Variables.next();
							}
							if (t0Variables.hasNext()) {
								Map<VariableTerm, Term> variables = t0Variables.next();
								Executable t1 = PrologUtils.resolveExecutable(t1ProofContext, getTerm(1), variables);
								t1Variables = t1.execute(t1ProofContext, unificationEngine, t1, variables).iterator();
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
