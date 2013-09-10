package org.archstudio.prolog.engine;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.archstudio.prolog.op.Evaluable;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;

public class PrologUtils {

	public static final VariableTerm DONT_CARE_VARIABLE = new VariableTerm("_");

	static private AtomicInteger temporaryVarialeCounter = new AtomicInteger();

	private PrologUtils() {
	}

	public static final Set<VariableTerm> extractVariables(Set<VariableTerm> variables, Term term) {
		if (term instanceof VariableTerm) {
			if (!"_".equals(((VariableTerm) term).getName())) {
				variables.add((VariableTerm) term);
			}
		}
		else if (term instanceof ComplexTerm) {
			for (Term t : ((ComplexTerm) term).getTerms()) {
				extractVariables(variables, t);
			}
		}
		else if (term instanceof ListTerm) {
			if (((ListTerm) term).getHead() != null) {
				extractVariables(variables, ((ListTerm) term).getHead());
			}
			if (((ListTerm) term).getTail() != null) {
				extractVariables(variables, ((ListTerm) term).getTail());
			}
		}
		return variables;
	}

	public static final ComplexTerm resolveComplexTerm(ProofContext proofContext, Term t,
			Map<VariableTerm, Term> variables) {
		t = t.resolve(proofContext, variables);
		if (t instanceof ComplexTerm) {
			return (ComplexTerm) t;
		}
		throw new RuntimeException("Not complex term: " + t);
	}

	public static final Executable resolveExecutable(ProofContext proofContext, Term t,
			Map<VariableTerm, Term> variables) {
		t = t.resolve(proofContext, variables);
		if (t instanceof Executable) {
			return (Executable) t;
		}
		throw new RuntimeException("Not executable: " + t);
	}

	public static final Evaluable resolveEvaluable(ProofContext proofContext, Term t, Map<VariableTerm, Term> variables) {
		t = t.resolve(proofContext, variables);
		if (t instanceof Evaluable) {
			return (Evaluable) t;
		}
		throw new RuntimeException("Not evaluable: " + t);
	}

	public static final String resolveString(ProofContext proofContext, Term t, Map<VariableTerm, Term> variables) {
		t = t.resolve(proofContext, variables);
		if (t instanceof StringTerm) {
			return (String) ((StringTerm) t).getValue();
		}
		return t.toString();
	}

	public static final Number evaluate(ProofContext proofContext, Term term, Map<VariableTerm, Term> variables) {
		return resolveEvaluable(proofContext, term, variables).evaluate(proofContext, variables);
	}

	public static final BigDecimal toBigDecimal(java.lang.Number n) {
		return n instanceof BigDecimal ? (BigDecimal) n : new BigDecimal((BigInteger) n);
	}

	public static final Iterable<Map<VariableTerm, Term>> negate(Iterable<Map<VariableTerm, Term>> result,
			Map<VariableTerm, Term> variables) {
		if (result.iterator().hasNext()) {
			return emptyVariablesList();
		}
		return Collections.singleton(variables);
	}

	public static final Iterable<Map<VariableTerm, Term>> emptyVariablesList() {
		return Collections.emptyList();
	}

	public static final List<Term> termOrListTerms(final Term t) {

		List<Term> terms = Lists.newArrayList();

		if (t instanceof ListTerm) {
			for (Term t2 : ((ListTerm) t).asList()) {
				terms.addAll(termOrListTerms(t2));
			}
		}
		else {
			terms.add(t);
		}

		return terms;
	}

	public static final VariableTerm getTemporaryVariableTerm() {
		return new VariableTerm("_G" + temporaryVarialeCounter.incrementAndGet());
	}
}