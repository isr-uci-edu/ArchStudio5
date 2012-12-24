package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class Not extends ComplexTerm implements Operation {

	public Not(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 1);
	}

	public Map<VariableTerm, Term> execute(ProofEngine proofEngine, ProofContext proofContext,
			UnificationEngine unificationEngine, Map<VariableTerm, Term> variables) {
		return Iterables.isEmpty(proofEngine.execute(proofContext, unificationEngine, (ComplexTerm) getTerm(0),
				Maps.newHashMap(variables))) ? variables : null;
	}
}
