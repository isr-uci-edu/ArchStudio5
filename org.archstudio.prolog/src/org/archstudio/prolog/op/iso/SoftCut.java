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

public class SoftCut extends ComplexTerm implements Executable {

	public SoftCut(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, final Term source, final Map<VariableTerm, Term> variables) {

		final Executable t0 = resolveOperation(getTerm(0), variables);
		final Executable t1 = resolveOperation(getTerm(1), variables);

		return new Iterable<Map<VariableTerm, Term>>() {

			Iterator<Map<VariableTerm, Term>> t0i = t0.execute(proofContext, unificationEngine, t0, variables)
					.iterator();
			boolean t0iHadNext = t0i.hasNext();
			Iterator<Map<VariableTerm, Term>> t1i = emptyVariablesList().iterator();

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {
				if (!t0iHadNext) {
					return emptyVariablesList().iterator();
				}

				return new AbstractIterator<Map<VariableTerm, Term>>() {
					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (t1i.hasNext()) {
								return t1i.next();
							}
							if (t0i.hasNext()) {
								t1i = t1.execute(proofContext, unificationEngine, t1, t0i.next()).iterator();
								continue;
							}
							break;
						}
						return endOfData();
					}
				};
			}
		};
	}
}