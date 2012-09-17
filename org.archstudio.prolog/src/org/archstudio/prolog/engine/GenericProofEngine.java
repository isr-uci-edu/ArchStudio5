package org.archstudio.prolog.engine;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Rule;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Maps;

// A proof engine based on
// http://reference.kfupm.edu.sa/content/p/r/prolog___the_language_and_its_implementa_532064.pdf
// http://www.learnprolognow.org/lpnpage.php?pagetype=html&pageid=lpn-htmlse6

@NonNullByDefault
public class GenericProofEngine implements ProofEngine {

	private static class MatchListIterator implements MatchIterator {

		Iterator<Map<VariableTerm, Term>> i;

		public MatchListIterator(List<Map<VariableTerm, Term>> list) {
			this.i = list.iterator();
		}

		@Override
		@Nullable
		public Map<VariableTerm, Term> next() {
			return i.hasNext() ? i.next() : null;
		}

	}

	private static class State {
		ComplexTerm goal;
		int kbTermIndex;
		List<ComplexTerm> kbTerms;

		Rule rule;
		Map<VariableTerm, Term> ruleVariables;
		Stack<MatchIterator> ruleBodyIterators;
	}

	public MatchIterator execute(ProofContext proofContext, UnificationEngine unificationEngine, ComplexTerm goal) {
		return execute(proofContext, unificationEngine, Maps.<VariableTerm, Term> newHashMap(), goal);
	}

	@Override
	public MatchIterator execute(final ProofContext proofContext, final UnificationEngine unificationEngine,
			final Map<VariableTerm, Term> variables, final ComplexTerm goal) {

		return new MatchIterator() {

			Stack<State> states = new Stack<State>();

			{ // initialize
				State state = new State();
				state.goal = goal;
				state.kbTermIndex = 0;
				state.kbTerms = proofContext.knowledgeBase.get(goal.getSignature());
				states.add(state);
			}

			public Map<VariableTerm, Term> next() {
				MAIN: while (true) {
					if (states.isEmpty())
						return null;

					State state = states.peek();
					if (state.goal != null) {
						while (state.kbTermIndex < state.kbTerms.size()) {
							ComplexTerm kbTerm = state.kbTerms.get(state.kbTermIndex++);
							UnificationContext context = new UnificationContext(goal, kbTerm, variables);
							if (unificationEngine.unify(context)) {
								if (kbTerm instanceof Rule) {
									Rule rule = (Rule) kbTerm;
									State ruleState = new State();
									ruleState.rule = rule;
									ruleState.ruleVariables = context.variables;
									ruleState.ruleBodyIterators = new Stack<MatchIterator>();
									states.push(ruleState);
									continue MAIN;
								}
								else {
									return context.variables;
								}
							}
						}
						states.pop();
						continue MAIN;
					}
					else {
						while (state.ruleBodyIterators.size() <= state.rule.getBodyTermSize()) {
							Map<VariableTerm, Term> v;
							if (state.ruleBodyIterators.size() == 0) {
								v = Maps.newHashMap();
							}
							else {
								v = state.ruleBodyIterators.peek().next();
							}
							if (v == null) {
								state.ruleBodyIterators.pop();
								if (state.ruleBodyIterators.size() == 0) {
									states.pop();
									continue MAIN;
								}
								continue;
							}
							else if (state.ruleBodyIterators.size() < state.rule.getBodyTermSize()) {
								Term term = state.rule.getBodyTerm(state.ruleBodyIterators.size());
								if (term instanceof Operation) {
									Operation operationTerm = (Operation) term;
									if (!operationTerm.execute(proofContext, unificationEngine, v)) {
										continue;
									}
									state.ruleBodyIterators.add(new MatchListIterator(Collections.singletonList(v)));
								}
								else if (term instanceof ComplexTerm) {
									ComplexTerm complexTerm = (ComplexTerm) term;
									state.ruleBodyIterators
											.add(execute(proofContext, unificationEngine, v, complexTerm));
								}
								else {
									throw new IllegalArgumentException(term.toString());
								}
								continue;
							}
							else {
								Map<VariableTerm, Term> results = Maps.newHashMap();
								for (Map.Entry<VariableTerm, Term> r : state.ruleVariables.entrySet()) {
									if (v.containsKey(r.getValue())) {
										results.put(r.getKey(), v.get(r.getValue()));
									}
									else {
										results.put(r.getKey(), r.getValue());
									}
								}
								return results;
							}
						}
					}
				}
			}
		};
	}
}
