package org.archstudio.prolog.engine;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UnificationContext {

	public final List<Equation> equations = Lists.newArrayListWithCapacity(25);
	public final Map<VariableTerm, Term> variables = Maps.newHashMap();

	public UnificationContext() {
	}

	public UnificationContext(Term t1, Term t2) {
		equations.add(new Equation(t1, t2));
	}

	public UnificationContext(Term t1, Term t2, Map<VariableTerm, Term> variables) {
		for (Entry<VariableTerm, Term> e : variables.entrySet()) {
			t1 = t1.replace(e.getKey(), e.getValue());
			t2 = t2.replace(e.getKey(), e.getValue());
			this.variables.put(e.getKey(), e.getValue());
		}
		equations.add(new Equation(t1, t2));
	}

	@Override
	public String toString() {
		return "Equations: " + equations + ", Varaibles: " + variables;
	}

}
