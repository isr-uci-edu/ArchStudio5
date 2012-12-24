package org.archstudio.prolog.term;

public class VariableTerm implements Term {

	private static long nextTemp = 0;

	public static VariableTerm nextTemp() {
		return new VariableTerm("_" + nextTemp++);
	}

	final String name;

	public VariableTerm(String name) {
		this.name = name;
	}

	public boolean contains(Term v) {
		return this.equals(v);
	}

	public Term replace(Term v, Term t) {
		return this.equals(v) ? t : this;
	}

	public String toString() {
		return name.toString();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
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
		VariableTerm other = (VariableTerm) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
