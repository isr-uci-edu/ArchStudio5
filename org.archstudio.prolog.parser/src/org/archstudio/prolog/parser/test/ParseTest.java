package org.archstudio.prolog.parser.test;

import junit.framework.Assert;

import org.archstudio.prolog.op.Equals;
import org.archstudio.prolog.op.NotEquals;
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

	private void assertParseOnly(String s, Term expected) {
		Term t = PrologParser.parseTerms(s).get(0);
		Assert.assertEquals(expected, t);
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
	public void testFunctor() {
		assertParse("f(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testLongFunctor() {
		assertParse("fabc(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testNumeral() {
		assertParse("f(1).", new ConstantTerm(Long.valueOf(1)));
	}

	@Test
	public void testNegativeNumeral() {
		assertParse("f(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testLongNumeral() {
		assertParse("f(123).", new ConstantTerm(Long.valueOf(123)));
	}

	@Test
	public void testString() {
		assertParse("f('a').", new StringTerm("a"));
	}

	@Test
	public void testLongString() {
		assertParse("f('abc').", new StringTerm("abc"));
	}

	@Test
	public void testAtom() {
		assertParse("f(a).", new ConstantTerm("a"));
	}

	@Test
	public void testLongAtom() {
		assertParse("f(abc).", new ConstantTerm("abc"));
	}

	@Test
	public void testVariable() {
		assertParse("f(X).", new VariableTerm("X"));
	}

	@Test
	public void testLongVariable() {
		assertParse("f(Xabc).", new VariableTerm("Xabc"));
	}

	@Test
	public void testEquals() {
		assertParseOnly("Xabc==Xbcd.", new Equals(new VariableTerm("Xabc"), new VariableTerm("Xbcd")));
	}

	@Test
	public void testEquals2() {
		assertParseOnly("==(Xabc,Xbcd).", new Equals(new VariableTerm("Xabc"), new VariableTerm("Xbcd")));
	}

	@Test
	public void testNotEquals() {
		assertParseOnly("Xabc\\=Xbcd.", new NotEquals(new VariableTerm("Xabc"), new VariableTerm("Xbcd")));
	}

	@Test
	public void testNotEquals2() {
		assertParseOnly("\\=(Xabc,Xbcd).", new NotEquals(new VariableTerm("Xabc"), new VariableTerm("Xbcd")));
	}

}
