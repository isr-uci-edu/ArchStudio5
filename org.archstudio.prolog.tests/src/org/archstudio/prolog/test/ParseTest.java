package org.archstudio.prolog.test;

import junit.framework.Assert;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.iso.Equals;
import org.archstudio.prolog.op.iso.NotEquals;
import org.archstudio.prolog.parser.ParseException;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ParseTest {

	ProofContext proofContext;

	@Before
	public void init() {
		proofContext = new ProofContext();
	}

	private void assertParse(String s) throws ParseException {
		String r = PrologParser.parseTerms(proofContext, s).get(0).toString();
		if (!r.endsWith(".")) {
			r = r + ".";
		}
		Assert.assertEquals(s, r);
	}

	private void assertParse(String s, Term checkIncluded) throws ParseException {
		Term t = PrologParser.parseTerms(proofContext, s).get(0);
		String r = t.toString();
		if (!r.endsWith(".")) {
			r = r + ".";
		}
		Assert.assertEquals(s, r);
		Assert.assertTrue(t.contains(checkIncluded));
	}

	private void assertParseOnly(String s, Term expected) throws ParseException {
		Term t = PrologParser.parseTerms(proofContext, s).get(0);
		Assert.assertEquals(expected, t);
	}

	@Test
	public void test() throws ParseException {
		assertParse("fa(aa).");
		assertParse("f(b).");
		assertParse("g(a).");
		assertParse("g(b).");
		assertParse("h(b).");
		assertParse("k(X) :- f(X), g(X), h(X).");
	}

	@Test
	public void testFunctor() throws ParseException {
		assertParse("f(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testLongFunctor() throws ParseException {
		assertParse("fabc(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testNumeral() throws ParseException {
		assertParse("f(1).", new ConstantTerm(Long.valueOf(1)));
	}

	@Test
	public void testNegativeNumeral() throws ParseException {
		assertParse("f(-1).", new ConstantTerm(Long.valueOf(-1)));
	}

	@Test
	public void testLongNumeral() throws ParseException {
		assertParse("f(123).", new ConstantTerm(Long.valueOf(123)));
	}

	@Test
	public void testString() throws ParseException {
		assertParse("f('a').", new StringTerm("a"));
	}

	@Test
	public void testLongString() throws ParseException {
		assertParse("f('abc').", new StringTerm("abc"));
	}

	@Test
	public void testAtom() throws ParseException {
		assertParse("f(a).", new ConstantTerm("a"));
	}

	@Test
	public void testLongAtom() throws ParseException {
		assertParse("f(abc).", new ConstantTerm("abc"));
	}

	@Test
	public void testVariable() throws ParseException {
		assertParse("f(X).", new VariableTerm("X"));
	}

	@Test
	public void testLongVariable() throws ParseException {
		assertParse("f(Xabc).", new VariableTerm("Xabc"));
	}

	@Test
	public void testEquals() throws ParseException {
		assertParseOnly("Xabc==Xbcd.",
				new Equals("==", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))));
	}

	@Test
	public void testEquals2() throws ParseException {
		assertParseOnly("==(Xabc,Xbcd).",
				new Equals("==", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))));
	}

	@Test
	public void testNotEquals() throws ParseException {
		assertParseOnly("Xabc\\=Xbcd.",
				new NotEquals("\\=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))));
	}

	@Test
	public void testNotEquals2() throws ParseException {
		assertParseOnly("\\=(Xabc,Xbcd).",
				new NotEquals("\\=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))));
	}

}
