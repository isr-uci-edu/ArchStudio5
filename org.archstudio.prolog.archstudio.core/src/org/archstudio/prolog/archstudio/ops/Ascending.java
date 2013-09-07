package org.archstudio.prolog.archstudio.ops;

import java.util.Collections;
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

import com.google.common.collect.Sets;

public class Ascending extends ComplexTerm implements Executable {

	public Ascending(String name, List<? extends Term> terms) {
		super(name, 1, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {
		Term t = getTerm(0).resolve(proofContext, variables);

		if (t instanceof ListTerm) {
			ListTerm listTerm = (ListTerm) t;
			if (PrologUtils.extractVariables(Sets.<VariableTerm> newHashSet(), t).size() == 0) {
				List<Term> list = listTerm.asList();
				for (int i = 1; i < list.size(); i++) {
					if (list.get(i - 1).compareTo(list.get(i)) >= 0) {
						return Collections.emptyList();
					}
				}
				return Collections.singleton(variables);
			}
		}
		return Collections.emptyList();
	}
}
