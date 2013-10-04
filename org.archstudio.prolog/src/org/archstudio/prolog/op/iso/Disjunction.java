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

public class Disjunction extends ComplexTerm implements Executable {

	public Disjunction(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		if (getTerm(0) instanceof IfThen) {
			// special case for IF-THEN-ELSE, A -> B ; C

			Executable term0if = PrologUtils.resolveExecutable(proofContext, ((IfThen) getTerm(0)).getTerm(0),
					variables);
			Iterator<Map<VariableTerm, Term>> term0Variables = term0if.execute(proofContext, unificationEngine,
					term0if, variables).iterator();

			if (term0Variables.hasNext()) {
				Executable term0then = PrologUtils.resolveExecutable(proofContext, ((IfThen) getTerm(0)).getTerm(1),
						variables);
				return term0then.execute(proofContext, unificationEngine, term0then, term0Variables.next());
			}
		}
		else {
			Executable term0 = PrologUtils.resolveExecutable(proofContext, getTerm(0), variables);
			Iterable<Map<VariableTerm, Term>> term0Variables = term0.execute(proofContext, unificationEngine, term0,
					variables);

			if (term0Variables.iterator().hasNext()) {
				return term0Variables;
			}
		}

		Executable term1 = PrologUtils.resolveExecutable(proofContext, getTerm(1), variables);
		return term1.execute(proofContext, unificationEngine, term1, variables);
	}
}
