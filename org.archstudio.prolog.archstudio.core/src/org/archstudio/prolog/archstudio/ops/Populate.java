package org.archstudio.prolog.archstudio.ops;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Populate extends ComplexTerm implements Executable {

	public Populate(String name, List<? extends Term> terms) {
		super(name, 3, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {

		VariableTerm variableTerm = (VariableTerm) getTerm(0);
		ListTerm listTerm = (ListTerm) getTerm(1).resolve(proofContext, variables);
		Term goalTerm = getTerm(2);

		List<Term> list = listTerm.asList();
		List<Term> conjunction = Lists.newArrayList();
		for (int i = 0; i < list.size(); i++) {
			Term t = list.get(i);
			if (t instanceof VariableTerm) {
				VariableTerm v = (VariableTerm) t;
				Map<VariableTerm, Term> replacement = Maps.newHashMap();
				replacement.put(variableTerm, v);
				Term newGoal = goalTerm.resolve(proofContext, replacement);
				conjunction.add(newGoal);
			}
		}
		Term conjunctionTerm = proofContext.create(",", conjunction);
		Executable executable = PrologUtils.resolveExecutable(proofContext, conjunctionTerm, variables);
		return executable.execute(proofContext, unificationEngine, source, variables);
	}
}
