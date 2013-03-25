package org.archstudio.prolog.term;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.archstudio.prolog.op.Evaluable;
import org.archstudio.prolog.op.Executable;

import com.google.common.collect.Sets;

abstract public class AbstractTerm implements Term {

	public AbstractTerm() {
	}

	protected Term resolve(Term t, Map<VariableTerm, Term> variables) {
		Set<Term> termsVisited = Sets.newHashSet();
		while (variables.containsKey(t)) {
			if (!termsVisited.add(t)) {
				break;
			}
			t = variables.get(t);
		}
		return t;
	}

	protected boolean isCyclic(Term t, Map<VariableTerm, Term> variables) {
		Set<Term> termsVisited = Sets.newHashSet();
		while (variables.containsKey(t)) {
			if (!termsVisited.add(t)) {
				return true;
			}
			t = variables.get(t);
		}
		return false;
	}

	protected Executable resolveOperation(Term t, Map<VariableTerm, Term> variables) {
		t = resolve(t, variables);
		if (t instanceof Executable) {
			return (Executable) t;
		}
		throw new RuntimeException("Not executable: " + t);
	}

	protected ComplexTerm resolveComplexTerm(Term t, Map<VariableTerm, Term> variables) {
		t = resolve(t, variables);
		if (t instanceof ComplexTerm) {
			return (ComplexTerm) t;
		}
		throw new RuntimeException("Not complex term: " + t);
	}

	protected Number evaluate(Term t, Map<VariableTerm, Term> variables) {
		t = resolve(t, variables);
		if (t instanceof Evaluable) {
			return ((Evaluable) t).evaluate(variables);
		}
		throw new RuntimeException("Not arithmetic: " + t);
	}

	protected BigDecimal toBigDecimal(java.lang.Number n) {
		return n instanceof BigDecimal ? (BigDecimal) n : new BigDecimal((BigInteger) n);
	}

	protected Iterable<Map<VariableTerm, Term>> negate(Iterable<Map<VariableTerm, Term>> result,
			Map<VariableTerm, Term> variables) {
		if (result.iterator().hasNext()) {
			return emptyVariablesList();
		}
		return Collections.singleton(variables);
	}

	protected Iterable<Map<VariableTerm, Term>> emptyVariablesList() {
		return Collections.emptyList();
	}

}
