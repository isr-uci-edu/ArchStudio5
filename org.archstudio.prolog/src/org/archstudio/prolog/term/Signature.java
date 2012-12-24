package org.archstudio.prolog.term;

public class Signature {

	public final String functor;
	public final int arity;

	public Signature(String functor, int arity) {
		this.functor = functor;
		this.arity = arity;
	}

	public String toString() {
		return functor + "/" + arity;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arity;
		result = prime * result + (functor == null ? 0 : functor.hashCode());
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
		Signature other = (Signature) obj;
		if (arity != other.arity) {
			return false;
		}
		if (functor == null) {
			if (other.functor != null) {
				return false;
			}
		}
		else if (!functor.equals(other.functor)) {
			return false;
		}
		return true;
	}
}
