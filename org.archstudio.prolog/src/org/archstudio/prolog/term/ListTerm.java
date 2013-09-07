package org.archstudio.prolog.term;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;

import com.google.common.collect.Lists;

public class ListTerm extends AbstractTerm implements Term {

	private final Term head;
	private final Term tail;

	public ListTerm(Term head, Term tail) {
		this.head = head; // nullable
		this.tail = tail; // nullable
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
	public Term resolve(ProofContext proofContext, Map<VariableTerm, Term> variables) {
		Term h = head == null ? null : head.resolve(proofContext, variables);
		Term t = tail == null ? null : tail.resolve(proofContext, variables);
		return new ListTerm(h, t);
	}

	protected String toStringHelper() {
		if (isEmpty()) {
			return "]";
		}
		if (tail instanceof ListTerm) {
			return ", " + head + ((ListTerm) tail).toStringHelper();
		}
		return ", " + head + ", " + tail + "]";
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		if (tail instanceof ListTerm) {
			return "[" + head + ((ListTerm) tail).toStringHelper();
		}
		return "[" + head + ", " + tail + "]";
	}

	public int length() {
		if (isEmpty()) {
			return 0;
		}
		if (tail instanceof ListTerm) {
			return ((ListTerm) tail).length() + 1;
		}
		throw new UnsupportedOperationException("Cannot calculate length!");
	}

	public List<Term> asList() {
		List<Term> list = Lists.newArrayList();
		ListTerm lt = this;
		while (!lt.isEmpty()) {
			list.add(lt.getHead());
			if (!(lt.getTail() instanceof ListTerm)) {
				throw new UnsupportedOperationException("Cannot create list!");
			}
			lt = (ListTerm) lt.getTail();
		}
		return list;
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

	public static ListTerm asListTerm(Term... terms) {
		return asListTerm(Arrays.asList(terms));
	}

	public static ListTerm asListTerm(Iterable<? extends Term> terms) {
		return asListTerm(Lists.newArrayList(terms));
	}

	public static ListTerm asListTerm(List<? extends Term> terms) {
		ListTerm lt = new ListTerm();
		for (Term term : Lists.reverse(terms)) {
			lt = new ListTerm(term, lt);
		}
		return lt;
	}
}
