package org.archstudio.prolog.term;

import java.util.Arrays;
import java.util.List;

public class Rule extends ComplexTerm {

	final List<Term> bodyTerms;

	public Rule(String functor, List<Term> headTerms, List<Term> bodyTerms) {
		super(functor, headTerms);
		this.bodyTerms = bodyTerms;
	}

	public Rule(ComplexTerm head, Term... bodyTerms) {
		super(head.functor, head.terms);
		this.bodyTerms = Arrays.asList(bodyTerms);
	}

	public int getBodyTermSize() {
		return bodyTerms.size();
	}

	public Term getBodyTerm(int index) {
		return bodyTerms.get(index);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append(" :- ");
		boolean firstTerm = true;
		for (Term t : bodyTerms) {
			if (!firstTerm)
				sb.append(", ");
			firstTerm = false;
			sb.append(t);
		}
		sb.append(".");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bodyTerms == null) ? 0 : bodyTerms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (bodyTerms == null) {
			if (other.bodyTerms != null)
				return false;
		}
		else if (!bodyTerms.equals(other.bodyTerms))
			return false;
		return true;
	}

	public ComplexTerm getHead() {
		return new ComplexTerm(functor, terms);
	}
}
