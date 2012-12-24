package org.archstudio.prolog.engine;

import org.archstudio.prolog.term.Term;

public class Equation {

	public final Term term1;
	public final Term term2;

	public Equation(Term term1, Term term2) {
		this.term1 = term1;
		this.term2 = term2;
	}

	public String toString() {
		return term1 + " = " + term2;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (term1 == null ? 0 : term1.hashCode());
		result = prime * result + (term2 == null ? 0 : term2.hashCode());
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
		Equation other = (Equation) obj;
		if (term1 == null) {
			if (other.term1 != null) {
				return false;
			}
		}
		else if (!term1.equals(other.term1)) {
			return false;
		}
		if (term2 == null) {
			if (other.term2 != null) {
				return false;
			}
		}
		else if (!term2.equals(other.term2)) {
			return false;
		}
		return true;
	}
}
