package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Function;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Plus extends ComplexTerm implements Function {

	public Plus(String name, List<? extends Term> terms) {
		super(name, terms);
		checkArgument(terms.size() == 2);
	}

	@Override
	public java.lang.Number evaluate(Map<VariableTerm, Term> variables) {
		try {
			Term t1 = getTerm(0);
			java.lang.Number n1 = null;
			if (t1 instanceof VariableTerm) {
				t1 = variables.get(t1);
			}
			if (t1 instanceof Function) {
				n1 = ((Function) t1).evaluate(variables);
			}
			Term t2 = getTerm(1);
			java.lang.Number n2 = null;
			if (t2 instanceof VariableTerm) {
				t2 = variables.get(t2);
			}
			if (t2 instanceof Function) {
				n2 = ((Function) t2).evaluate(variables);
			}
			if (n1 != null && n2 != null) {
				if (n1 instanceof BigInteger && n2 instanceof BigInteger) {
					return ((BigInteger) n1).add((BigInteger) n2);
				}
				return toBigDecimal(n1).add(toBigDecimal(n2));
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Not arithmetic: " + this, e);
		}
		throw new RuntimeException("Not arithmetic: " + this);
	}

	private BigDecimal toBigDecimal(java.lang.Number n) {
		return n instanceof BigDecimal ? (BigDecimal) n : new BigDecimal((BigInteger) n);
	}
}
