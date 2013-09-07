package org.archstudio.prolog.term;

import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;

public interface Term extends Comparable<Term> {

	Term resolve(ProofContext proofContext, Map<VariableTerm, Term> variables);

}
