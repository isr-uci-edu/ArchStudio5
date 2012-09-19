package org.archstudio.prolog.term;

public interface Term {

	boolean contains(Term v);

	Term replace(Term v, Term t);

}
