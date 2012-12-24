package org.archstudio.prolog.engine;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Rule;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@NonNullByDefault
public class SingleThreadProofEngine extends DebugProofEngine implements ProofEngine {

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			ComplexTerm goal) {
		return execute(proofContext, unificationEngine, goal, EMPTY_VARIABLES);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, final ComplexTerm goal, final Map<VariableTerm, Term> variables) {

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {
				return new AbstractIterator<Map<VariableTerm, Term>>() {

					int kbTermsIndex = 0;
					List<ComplexTerm> kbTerms = proofContext.getKnowledgeBaseTerms(goal, variables);
					Iterator<Map<VariableTerm, Term>> results = null;

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (results != null) {
								if (results.hasNext()) {
									return results.next();
								}
								results = null;
							}
							if (kbTermsIndex < kbTerms.size()) {
								ComplexTerm kbTerm = kbTerms.get(kbTermsIndex++);
								if (kbTerm instanceof Rule) {
									final Rule rule = (Rule) kbTerm;
									final UnificationContext context = new UnificationContext(goal, kbTerm,
											EMPTY_VARIABLES);
									if (unificationEngine.unify(context)) {
										Map<VariableTerm, Term> localVariables = toLocal(variables, goal, rule);
										results = Iterators.transform(
												execute(proofContext, unificationEngine, goal, localVariables, rule),
												new Function<Map<VariableTerm, Term>, Map<VariableTerm, Term>>() {

													@Override
													@Nullable
													public Map<VariableTerm, Term> apply(
															@Nullable Map<VariableTerm, Term> input) {
														return toGlobal(variables, goal, rule, input);
													}
												});
									}
								}
								else {
									UnificationContext context = new UnificationContext(goal, kbTerm, variables);
									if (unificationEngine.unify(context)) {
										return context.variables;
									}
								}
								continue;
							}
							return endOfData();
						}
					}
				};
			}
		};
	}

	@SuppressWarnings("unchecked")
	protected Iterator<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, final ComplexTerm goal, final Map<VariableTerm, Term> variables,
			final Rule rule) {

		return new AbstractIterator<Map<VariableTerm, Term>>() {

			int ruleIndex = 0;
			Map<VariableTerm, Term>[] bodyVariables = new Map[rule.getBodyTermSize()];
			Iterator<Map<VariableTerm, Term>>[] bodyIterators = new Iterator[rule.getBodyTermSize()];
			{
				bodyVariables[0] = variables;
			}

			protected void pop() {
				bodyVariables[ruleIndex] = null;
				bodyIterators[ruleIndex] = null;
				ruleIndex--;
			}

			@Override
			protected Map<VariableTerm, Term> computeNext() {
				while (ruleIndex != -1) {
					if (ruleIndex < rule.getBodyTermSize()) {
						if (bodyIterators[ruleIndex] == null) {
							Term t = rule.getBodyTerm(ruleIndex);
							if (t instanceof Operation) {
								Operation operation = (Operation) t;
								Map<VariableTerm, Term> v = Maps.newHashMap(bodyVariables[ruleIndex]);
								Map<VariableTerm, Term> newV = operation.execute(SingleThreadProofEngine.this,
										proofContext, unificationEngine, v);
								if (newV == null) {
									pop();
									continue;
								}
								bodyIterators[ruleIndex] = Collections.singletonList(newV).iterator();
							}
							else if (t instanceof ComplexTerm) {
								ComplexTerm ct = (ComplexTerm) t;
								Map<VariableTerm, Term> v = bodyVariables[ruleIndex];
								bodyIterators[ruleIndex] = execute(proofContext, unificationEngine, ct, v).iterator();
							}
							else {
								throw new IllegalArgumentException(t.toString());
							}
						}
						if (bodyIterators[ruleIndex].hasNext()) {
							ruleIndex++;
							if (ruleIndex < rule.getBodyTermSize()) {
								bodyVariables[ruleIndex] = bodyIterators[ruleIndex - 1].next();
							}
							continue;
						}
						else {
							pop();
							continue;
						}
					}
					else {
						if (bodyIterators[ruleIndex - 1].hasNext()) {
							return bodyIterators[ruleIndex - 1].next();
						}
						ruleIndex--;
						continue;
					}
				}
				return endOfData();
			}

			@Override
			public String toString() {
				StringBuffer sb = new StringBuffer();
				sb.append(goal + " " + variables + " " + ruleIndex + "\n");
				for (int j = 0; j < rule.getBodyTermSize(); j++) {
					sb.append(rule.getBodyTerm(j) + " " + bodyVariables[j] + "\n");
				}
				return sb.toString();
			}
		};
	}
}
