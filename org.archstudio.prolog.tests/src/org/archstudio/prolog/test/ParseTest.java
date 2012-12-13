package org.archstudio.prolog.test;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.iso.Equals;
import org.archstudio.prolog.op.iso.NotEquals;
import org.archstudio.prolog.parser.ParseException;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
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

	private void assertParse(String expected) throws ParseException {
		String r = PrologParser.parseTerms(proofContext, expected).get(0).toString();
		if (!r.endsWith(".")) {
			r = r + ".";
		}
		Assert.assertEquals(expected, r);
	}

	private void assertParseContains(Term expectedIncluded, String s) throws ParseException {
		Term t = PrologParser.parseTerms(proofContext, s).get(0);
		String r = t.toString();
		if (!r.endsWith(".")) {
			r = r + ".";
		}
		Assert.assertEquals(s, r);
		Assert.assertTrue(t.contains(expectedIncluded));
	}

	private void assertParseEquals(Term expected, String s) throws ParseException {
		Term t = PrologParser.parseTerms(proofContext, s).get(0);
		Assert.assertEquals(expected, t);
	}

	@Test
	public void testRule() throws ParseException {
		assertParse("k(X) :- f(X), g(X), h(X).");
	}

	@Test
	public void testList() throws ParseException {
		ListTerm l = new ListTerm(new VariableTerm("A"), new ListTerm(new VariableTerm("B"), new ListTerm(
				new VariableTerm("C"), new ListTerm(null, null))));
		assertParseEquals(l, "[A, B, C].");
		assertParseEquals(l, "[A|[B, C]].");
		assertParseEquals(l, "[A, B|[C]].");
		assertParseEquals(l, "[A, B, C|[]].");
		assertParseEquals(l, ".(A, .(B, .(C, []))).");
		assertParseEquals(l, ".(A, .(B, [C])).");
		assertParseEquals(l, ".(A, [B, C]).");
	}

	@Test
	public void testFunctor() throws ParseException {
		assertParseContains(new ConstantTerm(BigDecimal.valueOf(1)), "f(1).");
		assertParseContains(new ConstantTerm(BigDecimal.valueOf(1)), "fabc(1).");
	}

	@Test
	public void testNumeral() throws ParseException {
		assertParseContains(new ConstantTerm(BigDecimal.valueOf(1)), "f(1).");
		assertParseContains(new ConstantTerm(BigDecimal.valueOf(-1)), "f(-1).");
		assertParseContains(new ConstantTerm(BigDecimal.valueOf(123)), "f(123).");
		assertParseContains(new ConstantTerm(BigDecimal.valueOf(-123)), "f(-123).");
	}

	@Test
	public void testString() throws ParseException {
		assertParseContains(new StringTerm("a"), "f('a').");
		assertParseContains(new StringTerm("abc"), "f('abc').");
	}

	@Test
	public void testAtom() throws ParseException {
		assertParseContains(new ConstantTerm("a"), "f(a).");
		assertParseContains(new ConstantTerm("abc"), "f(abc).");
	}

	@Test
	public void testVariable() throws ParseException {
		assertParseContains(new VariableTerm("X"), "f(X).");
		assertParseContains(new VariableTerm("Xabc"), "f(Xabc).");
	}

	@Test
	public void testEquals() throws ParseException {
		assertParseEquals(new Equals("==", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"Xabc==Xbcd.");
		assertParseEquals(new Equals("==", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"==(Xabc,Xbcd).");
	}

	@Test
	public void testNotEquals() throws ParseException {
		assertParseEquals(new NotEquals("\\=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"Xabc\\=Xbcd.");
		assertParseEquals(new NotEquals("\\=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"\\=(Xabc,Xbcd).");
	}

}
