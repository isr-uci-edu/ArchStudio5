package org.archstudio.prolog.engine;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UnificationContext {

	public final ProofContext proofContext;
	public final List<Equation> equations = Lists.newArrayListWithCapacity(25);
	public final Map<VariableTerm, Term> variables = Maps.newHashMap();

	public UnificationContext(ProofContext proofContext, Term t1, Term t2, Map<VariableTerm, Term> variables) {
		this.proofContext = proofContext;
		this.equations.add(new Equation(t1.resolve(proofContext, variables), t2.resolve(proofContext, variables)));
		this.variables.putAll(variables);
	}

	@Override
	public String toString() {
		return "Equations: " + equations + ", Variables: " + variables;
	}

}
