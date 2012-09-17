package org.archstudio.prolog.term;

public interface Term {

	boolean contains(VariableTerm v);

	Term replace(VariableTerm v, Term t);

}
