package org.archstudio.prolog.test;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.iso.Conjunction;
import org.archstudio.prolog.op.iso.ListTerm;
import org.archstudio.prolog.op.iso.Neck;
import org.archstudio.prolog.op.iso.NotUnifiable;
import org.archstudio.prolog.op.iso.Unifiable;
import org.archstudio.prolog.parser.ParseException;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
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

	private void assertParse(String expected) throws ParseException {
		String r = PrologParser.parseTerms(proofContext, expected).get(0).toString() + ".";
		Assert.assertEquals(expected, r);
	}

	private void assertParseContains(Term expectedIncluded, String s) throws ParseException {
		Term t = PrologParser.parseTerms(proofContext, s).get(0);
		Assert.assertTrue(t.contains(expectedIncluded));
	}

	private void assertParse(Term expected, String s) throws ParseException {
		Term t = PrologParser.parseTerms(proofContext, s).get(0);
		Assert.assertEquals(expected, t);
	}

	private void assertParse(String expected, String s) throws ParseException {
		Term expectedTerm = PrologParser.parseTerms(proofContext, expected).get(0);
		Term sTerm = PrologParser.parseTerms(proofContext, s).get(0);
		Assert.assertEquals(expectedTerm, sTerm);
	}

	@Test
	public void testRule() throws ParseException {
		VariableTerm X = new VariableTerm("X");
		assertParse(new Neck(new ComplexTerm("key_1", X), new Conjunction(",", new ComplexTerm("f2", X),
				new ComplexTerm("g3_f", X), new ComplexTerm("h_g", X))), "key_1(X) :- f2(X), g3_f(X), h_g(X).");
		assertParse(new Neck(new ComplexTerm("key_1", X), new Conjunction(",", new ComplexTerm("f2", X),
				new ComplexTerm("g3_f", X), new ComplexTerm("h_g", X))), ":-(key_1(X), ,(f2(X), g3_f(X), h_g(X))).");
	}

	@Test
	public void testList() throws ParseException {
		assertParse("=(.(X,Y),.(1,.(2,.(3,[])))).", "[X|Y]=[1,2,3].");
		ListTerm l = new ListTerm(new VariableTerm("A"), new ListTerm(new VariableTerm("B"), new ListTerm(
				new VariableTerm("C"), new ListTerm())));
		assertParse(l, ".(A, .(B, .(C, []))).");
		assertParse(l, ".(A, .(B, [C])).");
		assertParse(l, ".(A, [B, C]).");
		assertParse(l, "[A, B, C].");
		assertParse(l, "[A|[B, C]].");
		assertParse(l, "[A, B|[C]].");
		assertParse(l, "[A, B, C|[]].");
		ListTerm m = new ListTerm(new ConstantTerm(BigInteger.valueOf(1)), new ListTerm(new ConstantTerm(
				BigInteger.valueOf(2)), new ListTerm(new ConstantTerm(BigInteger.valueOf(3)), new ListTerm())));
		assertParse(m, ".(1, .(2, .(3, []))).");
		assertParse(m, ".(1, .(2, [3])).");
		assertParse(m, ".(1, [2, 3]).");
		assertParse(m, "[1, 2, 3].");
		assertParse(m, "[1|[2, 3]].");
		assertParse(m, "[1, 2|[3]].");
		assertParse(m, "[1, 2, 3|[]].");
	}

	@Test
	public void testFunctor() throws ParseException {
		assertParseContains(new ConstantTerm(BigInteger.valueOf(1)), "f(1).");
		assertParseContains(new ConstantTerm(BigInteger.valueOf(1)), "fabc(1).");
	}

	@Test
	public void testInteger() throws ParseException {
		assertParse(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(1))), "f(1).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(-1))), "f(-1).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(123))), "f(123).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(-123))), "f(-123).");
	}

	@Test
	public void testFloat() throws ParseException {
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(1.0))), "f(1.0).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(-1.0))), "f(-1.0).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(123.0))), "f(123.0).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(-123.0))), "f(-123.0).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(1.0e+10))), "f(1.0e+10).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(-1.0e+10))), "f(-1.0e+10).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(1.0e-10))), "f(1.0e-10).");
		assertParse(new ComplexTerm("f", new ConstantTerm(BigDecimal.valueOf(-1.0e-10))), "f(-1.0e-10).");
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
	public void testUnifiable() throws ParseException {
		assertParse(new Unifiable("=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"Xabc=Xbcd.");
		assertParse(new Unifiable("=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"=(Xabc,Xbcd).");
	}

	@Test
	public void testNotUnifiable() throws ParseException {
		assertParse(new NotUnifiable("\\=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"Xabc\\=Xbcd.");
		assertParse(new NotUnifiable("\\=", Lists.newArrayList(new VariableTerm("Xabc"), new VariableTerm("Xbcd"))),
				"\\=(Xabc,Xbcd).");
	}

	@Test
	public void testExtra() throws ParseException {
		assertParse("compatible_directions('in','out').");
		assertParse("compatible_directions('out','in').");
		assertParse("compatible_directions('inout','inout').");
		assertParse("compatible_directions('none','none').");
		assertParse(
				"test(Id,'Connected interfaces have incompatible directions') :- link(L), id(L,Id), link_point1(L,A), link_point2(L,B), direction(A,Ad), direction(B,Bd), \\+(compatible_directions(Ad,Bd)).",
				"test(Id,'Connected interfaces have incompatible directions') :- link(L), id(L,Id), link_point1(L,A), link_point2(L,B), direction(A,Ad), direction(B,Bd), \\+ compatible_directions(Ad,Bd).");
	}
}
