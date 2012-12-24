package org.archstudio.prolog.term;

public class ListTerm implements Term {

	private final Term head;
	private final Term tail;

	public ListTerm(Term head, Term tail) {
		this.head = head;
		this.tail = tail;
	}

	public boolean isEmpty() {
		return head == null && tail == null;
	}

	public boolean contains(Term v) {
		if (isEmpty()) {
			return false;
		}
		if (head.equals(v)) {
			return true;
		}
		return tail.contains(v);
	}

	public Term replace(Term v, Term t) {
		if (isEmpty()) {
			return this;
		}
		if (head.equals(v)) {
			return new ListTerm(t, tail.replace(v, t));
		}
		return new ListTerm(head, tail.replace(v, t));
	}

	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		return ".(" + head + ", " + tail + ")";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (head == null ? 0 : head.hashCode());
		result = prime * result + (tail == null ? 0 : tail.hashCode());
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
		ListTerm other = (ListTerm) obj;
		if (head == null) {
			if (other.head != null) {
				return false;
			}
		}
		else if (!head.equals(other.head)) {
			return false;
		}
		if (tail == null) {
			if (other.tail != null) {
				return false;
			}
		}
		else if (!tail.equals(other.tail)) {
			return false;
		}
		return true;
	}
}
