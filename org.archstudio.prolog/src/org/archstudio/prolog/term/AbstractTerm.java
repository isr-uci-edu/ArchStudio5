package org.archstudio.prolog.term;

abstract public class AbstractTerm implements Term {

	public AbstractTerm() {
	}

	@Override
	public int compareTo(Term term) {
		// FIXME: implement correct sorting, see: http://www.swi-prolog.org/pldoc/man?section=compare
		return toString().compareTo(term.toString());
	}
}
