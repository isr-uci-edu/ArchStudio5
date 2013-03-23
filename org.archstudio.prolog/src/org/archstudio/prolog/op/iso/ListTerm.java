package org.archstudio.prolog.op.iso;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.archstudio.prolog.term.Term;

public class ListTerm implements Term {

	private final Term head;
	private final Term tail;

	public ListTerm(Term head, Term tail) {
		this.head = head;
		this.tail = tail;
	}

	public ListTerm() {
		this((Term) null, (Term) null);
	}

	public ListTerm(String functor, List<Term> terms) {
		this(terms.get(0), terms.get(1));
		checkArgument(terms.size() == 2);
	}

	public boolean isEmpty() {
		return head == null && tail == null;
	}

	@Override
	public boolean contains(Term v) {
		if (isEmpty()) {
			return false;
		}
		return head.equals(v) || head.contains(v) || tail.equals(v) || tail.contains(v);
	}

	@Override
	public Term replace(Term v, Term t) {
		if (isEmpty()) {
			return this;
		}
		if (head.equals(v)) {
			return new ListTerm(t, tail.replace(v, t));
		}
		return new ListTerm(head, tail.replace(v, t));
	}

	protected String toListString() {
		if (isEmpty()) {
			return "]";
		}
		if (tail instanceof ListTerm) {
			return ", " + head + ((ListTerm) tail).toListString();
		}
		return ", " + head + ", " + tail + "]";
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		if (tail instanceof ListTerm) {
			return "[" + head + ((ListTerm) tail).toListString();
		}
		return "[" + head + ", " + tail + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (head == null ? 0 : head.hashCode());
		result = prime * result + (tail == null ? 0 : tail.hashCode());
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

	public Term getHead() {
		return head;
	}

	public Term getTail() {
		return tail;
	}
}
