package org.archstudio.prolog.op.iso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Evaluable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Abs extends ComplexTerm implements Evaluable {

	public Abs(String name, List<? extends Term> terms) {
		super(name, 1, terms);
	}

	@Override
	public java.lang.Number evaluate(Map<VariableTerm, Term> variables) {
		java.lang.Number n1 = evaluate(getTerm(0), variables);
		if (n1 instanceof BigInteger) {
			return ((BigInteger) n1).abs();
		}
		return ((BigDecimal) n1).abs();
	}
}
