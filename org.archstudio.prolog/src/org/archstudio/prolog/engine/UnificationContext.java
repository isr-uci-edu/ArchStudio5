package org.archstudio.prolog.engine;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class UnificationContext {

	public final Set<Equation> equations = Sets.newHashSet();
	public final Map<VariableTerm, Term> variables = Maps.newHashMap();

	public UnificationContext() {
	}

	public UnificationContext(Term t1, Term t2) {
		equations.add(new Equation(t1, t2));
	}

	public UnificationContext(Term t1, Term t2, Map<VariableTerm, Term> variables) {
		equations.add(new Equation(t1, t2));
		for (Entry<VariableTerm, Term> e : variables.entrySet()) {
			equations.add(new Equation(e.getKey(), e.getValue()));
		}
	}

	public String toString() {
		return "Equations: " + equations + ", Varaibles: " + variables;
	}

	public void reset() {
		for (Entry<VariableTerm, Term> var : variables.entrySet()) {
			equations.add(new Equation(var.getKey(), var.getValue()));
		}
		variables.clear();
	}

}
