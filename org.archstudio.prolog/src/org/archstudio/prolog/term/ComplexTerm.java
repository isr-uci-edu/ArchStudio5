package org.archstudio.prolog.term;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class ComplexTerm implements Term {

	private final String functor;
	private final List<? extends Term> terms;

	public ComplexTerm(String functor, List<? extends Term> terms) {
		this.functor = functor;
		this.terms = terms;
	}

	public ComplexTerm(String functor, Term... terms) {
		this.functor = functor;
		this.terms = Arrays.asList(terms);
	}

	public int getArity() {
		return terms.size();
	}

	public String getFunctor() {
		return functor;
	}

	public Term getTerm(int index) {
		return terms.get(index);
	}

	public List<? extends Term> getTerms() {
		return Lists.newArrayList(terms);
	}

	public Signature getSignature() {
		return new Signature(functor, getArity());
	}

	public boolean contains(Term v) {
		for (Term t : terms) {
			if (t.equals(v) || t.contains(v)) {
				return true;
			}
		}
		return false;
	}

	public Term replace(Term v, Term t) {
		List<Term> terms2 = Lists.newArrayListWithCapacity(terms.size());
		for (Term i : terms) {
			terms2.add(i.replace(v, t));
		}
		return new ComplexTerm(functor, terms2);
	}

	public String toString() {
		return functor + "(" + Joiner.on(',').join(terms) + ")";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (functor == null ? 0 : functor.hashCode());
		result = prime * result + (terms == null ? 0 : terms.hashCode());
		return result;
	}

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
}
