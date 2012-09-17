package org.archstudio.prolog.engine;

import java.util.Map;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface ProofEngine {

	public interface MatchIterator {
		@Nullable
		public Map<VariableTerm, Term> next();
	}

	MatchIterator execute(ProofContext proofContext, UnificationEngine unificationEngine, ComplexTerm goal);

	MatchIterator execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Map<VariableTerm, Term> variables, ComplexTerm goal);

}
