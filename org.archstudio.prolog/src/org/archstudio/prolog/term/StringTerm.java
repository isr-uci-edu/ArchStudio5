package org.archstudio.prolog.term;

public class StringTerm extends ConstantTerm {

	public StringTerm(Object value) {
		super(value);
	}

	@Override
	public String toString() {
		return "'" + super.toString() + "'";
	}
}
