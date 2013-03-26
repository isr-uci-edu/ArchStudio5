package org.archstudio.prolog.op.iso;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Evaluable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Add extends ComplexTerm implements Evaluable {

	public Add(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public java.lang.Number evaluate(Map<VariableTerm, Term> variables) {
		java.lang.Number n1 = evaluate(getTerm(0), variables);
		java.lang.Number n2 = evaluate(getTerm(1), variables);
		if (n1 instanceof BigInteger && n2 instanceof BigInteger) {
			return ((BigInteger) n1).add((BigInteger) n2);
		}
		return toBigDecimal(n1).add(toBigDecimal(n2));
	}
}
