package org.archstudio.prolog.parser.test;

import junit.framework.Assert;

import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.junit.Test;

public class ParseTest {

	private void assertParse(String s) {
		String r = PrologParser.parseTerms(s).get(0).toString();
		if (!r.endsWith(".")) {
			r = r + ".";
		}
		Assert.assertEquals(s, r);
	}

	private void assertParse(String s, Term checkIncluded) {
		Term t = PrologParser.parseTerms(s).get(0);
		String r = t.toString();
		if (!r.endsWith(".")) {
			r = r + ".";
		}
		Assert.assertEquals(s, r);
		Assert.assertTrue(t.contains(checkIncluded));
	}

	@Test
	public void test() {
		assertParse("f(a).");
		assertParse("f(b).");
		assertParse("g(a).");
		assertParse("g(b).");
		assertParse("h(b).");
		assertParse("k(X) :- f(X), g(X), h(X).");
	}

	@Test
	public void testNumeral() {
		assertParse("f(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testNumerals() {
		assertParse("f(123).", new ConstantTerm(Long.valueOf(123)));
	}

	@Test
	public void testString() {
		assertParse("f('a').", new StringTerm("a"));
	}

	@Test
	public void testStrings() {
		assertParse("f('abc').", new StringTerm("abc"));
	}

	@Test
	public void testAtom() {
		assertParse("f(a).", new ConstantTerm("a"));
	}

	@Test
	public void testVariable() {
		assertParse("f(X).", new VariableTerm("X"));
	}

}
