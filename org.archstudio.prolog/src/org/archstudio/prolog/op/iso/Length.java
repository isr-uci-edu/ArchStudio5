package org.archstudio.prolog.op.iso;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;

public class Length extends ComplexTerm implements Executable {

	public Length(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, Term source, final Map<VariableTerm, Term> variables) {

		Term list = getTerm(0);
		Term length = getTerm(1);

		if (list instanceof VariableTerm) {
			java.lang.Number bLength = PrologUtils.evaluate(proofContext, length, variables);
			if (bLength instanceof BigInteger) {
				List<VariableTerm> variableList = Lists.newArrayList();
				for (int i = 0; i < bLength.intValue(); i++) {
					variableList.add(PrologUtils.getTemporaryVariableTerm());
				}
				ListTerm listTerm = ListTerm.asListTerm(variableList);

				UnificationContext context = new UnificationContext(proofContext, list, listTerm, variables);
				if (unificationEngine.unifies(proofContext, context)) {
					return Collections.singleton(context.variables);
				}
				return Collections.emptyList();
			}
		}
		// TODO: Handle this situation better
		else if (list instanceof ListTerm && getVariableLength((ListTerm) list) instanceof VariableTerm) {
			VariableTerm tail = getVariableLength((ListTerm) list);
			int actualLength = getListLength((ListTerm) list);

			java.lang.Number bLength = PrologUtils.evaluate(proofContext, length, variables);
			if (actualLength <= bLength.intValue()) {
				List<VariableTerm> variableList = Lists.newArrayList();
				for (int i = actualLength; i < bLength.intValue(); i++) {
					variableList.add(PrologUtils.getTemporaryVariableTerm());
				}
				ListTerm listTerm = ListTerm.asListTerm(variableList);

				UnificationContext context = new UnificationContext(proofContext, tail, listTerm, variables);
				if (unificationEngine.unifies(proofContext, context)) {
					return Collections.singleton(context.variables);
				}
			}
			return Collections.emptyList();
		}
		else {
			Term bLength = new ConstantTerm(BigInteger.valueOf(((ListTerm) list).length()));

			UnificationContext context = new UnificationContext(proofContext, length, bLength, variables);
			if (unificationEngine.unifies(proofContext, context)) {
				return Collections.singleton(context.variables);
			}
			return Collections.emptyList();
		}

		throw new UnsupportedOperationException("Cannot satisfy length: " + this);
	}

	private VariableTerm getVariableLength(ListTerm list) {

		Term tail = list.getTail();
		if (tail instanceof VariableTerm) {
			return (VariableTerm) tail;
		}
		if (tail instanceof ListTerm) {
			return getVariableLength((ListTerm) tail);
		}
		return null;
	}

	private int getListLength(ListTerm list) {

		Term tail = list.getTail();
		if (tail instanceof VariableTerm) {
			return 1;
		}
		if (tail instanceof ListTerm) {
			return 1 + getListLength((ListTerm) tail);
		}
		return 0;
	}
}
