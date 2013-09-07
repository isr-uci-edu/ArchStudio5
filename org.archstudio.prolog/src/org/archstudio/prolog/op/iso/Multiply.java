package org.archstudio.prolog.op.iso;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.Evaluable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class Multiply extends ComplexTerm implements Evaluable {

	public Multiply(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public java.lang.Number evaluate(ProofContext proofContext, Map<VariableTerm, Term> variables) {
		java.lang.Number n1 = PrologUtils.evaluate(proofContext, getTerm(0), variables);
		java.lang.Number n2 = PrologUtils.evaluate(proofContext, getTerm(1), variables);
		if (n1 instanceof BigInteger && n2 instanceof BigInteger) {
			return ((BigInteger) n1).multiply((BigInteger) n2);
		}
		return PrologUtils.toBigDecimal(n1).multiply(PrologUtils.toBigDecimal(n2));
	}
}
