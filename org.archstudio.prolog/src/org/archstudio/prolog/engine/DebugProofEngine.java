package org.archstudio.prolog.engine;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Rule;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@NonNullByDefault
public class DebugProofEngine implements ProofEngine {

	protected final static Map<VariableTerm, Term> EMPTY_VARIABLES = Collections.<VariableTerm, Term> emptyMap();

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			ComplexTerm goal) {
		return execute(proofContext, unificationEngine, goal, Maps.<VariableTerm, Term> newHashMap());
	}

	protected Map<VariableTerm, Term> toLocal(Map<VariableTerm, Term> globalVariables, ComplexTerm globalTerm,
			ComplexTerm localTerm) {
		Map<VariableTerm, Term> localVariables = Maps.newHashMap();
		for (int j = 0; j < globalTerm.getArity(); j++) {
			Term g = globalTerm.getTerm(j);
			Term l = localTerm.getTerm(j);
			if (!(g instanceof VariableTerm) && l instanceof VariableTerm) {
				localVariables.put((VariableTerm) l, g);
			}
			else if (globalVariables.containsKey(g) && l instanceof VariableTerm) {
				localVariables.put((VariableTerm) l, globalVariables.get(g));
			}
		}
		return localVariables;
	}

	protected Map<VariableTerm, Term> toGlobal(Map<VariableTerm, Term> globalVariables, ComplexTerm globalTerm,
			ComplexTerm localTerm, Map<VariableTerm, Term> localVariables) {
		Map<VariableTerm, Term> gVariables = Maps.newHashMap(globalVariables);
		for (int j = 0; j < globalTerm.getArity(); j++) {
			Term g = globalTerm.getTerm(j);
			Term l = localTerm.getTerm(j);
			if (g instanceof VariableTerm) {
				gVariables.put((VariableTerm) g, localVariables.get(l));
			}
		}
		return gVariables;
	}

	protected Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			ComplexTerm goal, Map<VariableTerm, Term> variables) {

		List<Map<VariableTerm, Term>> results = Lists.newArrayList();
		List<ComplexTerm> kbTerms = proofContext.getKnowledgeBaseTerms(goal, variables);

		for (ComplexTerm kbTerm : kbTerms) {
			if (kbTerm instanceof Rule) {
				Rule rule = (Rule) kbTerm;
				UnificationContext context = new UnificationContext(goal, kbTerm, EMPTY_VARIABLES);
				if (unificationEngine.unify(context)) {
					Map<VariableTerm, Term> localVariables = toLocal(variables, goal, rule);
					List<Map<VariableTerm, Term>> firstVariables = execute(proofContext, unificationEngine, goal,
							localVariables, rule, 0);
					for (Map<VariableTerm, Term> v : firstVariables) {
						results.add(toGlobal(variables, goal, rule, v));
					}
				}
			}
			else {
				UnificationContext context = new UnificationContext(goal, kbTerm, variables);
				if (unificationEngine.unify(context)) {
					results.add(context.variables);
				}
			}
		}

		return results;
	}

	protected List<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			ComplexTerm goal, Map<VariableTerm, Term> variables, Rule rule, int ruleIndex) {

		if (ruleIndex == rule.getBodyTermSize()) {
			return Collections.singletonList(variables);
		}

		Term t = rule.getBodyTerm(ruleIndex);

		if (t instanceof Operation) {
			Operation ot = (Operation) t;
			Map<VariableTerm, Term> newVariables = ot.execute(this, proofContext, unificationEngine, variables);
			if (newVariables == null) {
				return Collections.emptyList();
			}
			return execute(proofContext, unificationEngine, goal, newVariables, rule, ruleIndex + 1);
		}

		if (t instanceof ComplexTerm) {
			List<Map<VariableTerm, Term>> results = Lists.newArrayList();
			ComplexTerm ct = (ComplexTerm) t;
			Iterable<Map<VariableTerm, Term>> termVariables = execute(proofContext, unificationEngine, ct, variables);
			for (Map<VariableTerm, Term> v : termVariables) {
				results.addAll(execute(proofContext, unificationEngine, goal, v, rule, ruleIndex + 1));
			}
			return results;
		}

		throw new IllegalArgumentException(t.toString());
	}
}