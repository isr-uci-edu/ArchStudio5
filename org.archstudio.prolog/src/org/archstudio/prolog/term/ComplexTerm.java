package org.archstudio.prolog.term;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.Signature;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.op.iso.Neck;

import com.google.common.base.Joiner;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;

public class ComplexTerm extends AbstractTerm implements Term, Executable {

	private final String functor;
	private final List<? extends Term> terms;

	public ComplexTerm(String functor, List<? extends Term> terms) {
		this(functor, -1, terms);
	}

	protected ComplexTerm(String functor, int arity, List<? extends Term> terms) {
		this.functor = checkNotNull(functor);
		this.terms = checkNotNull(terms);
		checkArgument(terms.size() > 0);
		for (Term term : terms) {
			checkNotNull(term);
		}
		if (arity >= 0) {
			checkArgument(terms.size() == arity);
		}
	}

	public int getArity() {
		return terms.size();
	}

	public String getFunctor() {
		return functor;
	}

	public int getTermsSize() {
		return terms.size();
	}

	public Term getTerm(int index) {
		return terms.get(index);
	}

	public List<Term> getTerms() {
		return Lists.newArrayList(terms);
	}

	public Signature getSignature() {
		return new Signature(functor, getArity());
	}

	@Override
	public Term resolve(ProofContext proofContext, Map<VariableTerm, Term> variables) {
		List<Term> terms = Lists.newArrayListWithCapacity(getArity());
		for (Term term : getTerms()) {
			terms.add(term.resolve(proofContext, variables));
		}
		return proofContext.create(getFunctor(), terms);
	}

	@Override
	public String toString() {
		return functor + "(" + Joiner.on(',').join(terms) + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (functor == null ? 0 : functor.hashCode());
		result = prime * result + (terms == null ? 0 : terms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ComplexTerm other = (ComplexTerm) obj;
		if (functor == null) {
			if (other.functor != null) {
				return false;
			}
		}
		else if (!functor.equals(other.functor)) {
			return false;
		}
		if (terms == null) {
			if (other.terms != null) {
				return false;
			}
		}
		else if (!terms.equals(other.terms)) {
			return false;
		}
		return true;
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(final ProofContext proofContext,
			final UnificationEngine unificationEngine, final Term source, final Map<VariableTerm, Term> variables) {

		return new Iterable<Map<VariableTerm, Term>>() {

			@Override
			public Iterator<Map<VariableTerm, Term>> iterator() {
				return new AbstractIterator<Map<VariableTerm, Term>>() {

					List<ComplexTerm> kbTerms = proofContext.getKnowledgeBaseTerms(ComplexTerm.this, variables);
					int kbIndex = 0;
					Iterator<Map<VariableTerm, Term>> variablesIterator = Collections
							.<Map<VariableTerm, Term>> emptyList().iterator();

					@Override
					protected Map<VariableTerm, Term> computeNext() {
						while (true) {
							if (variablesIterator.hasNext()) {
								return variablesIterator.next();
							}
							if (kbIndex < kbTerms.size()) {
								ComplexTerm kbTerm = kbTerms.get(kbIndex++);
								if (kbTerm instanceof Neck) {
									variablesIterator = ((Neck) kbTerm).execute(proofContext, unificationEngine,
											ComplexTerm.this, variables).iterator();
									continue;
								}
								else {
									UnificationContext context = new UnificationContext(proofContext, ComplexTerm.this,
											kbTerm, variables);
									if (unificationEngine.unifies(proofContext, context)) {
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
}
