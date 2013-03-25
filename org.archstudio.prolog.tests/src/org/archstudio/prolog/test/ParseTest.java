package org.archstudio.prolog.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

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
		List<? extends Term> X = Lists.newArrayList(new VariableTerm("X"));
		assertParse(
				new Neck(":-", Lists.newArrayList(
						new ComplexTerm("key_1", X),
						new Conjunction(",", Lists.newArrayList(new ComplexTerm("f2", X), new ComplexTerm("g3_f", X),
								new ComplexTerm("h_g", X))))), "key_1(X) :- f2(X), g3_f(X), h_g(X).");
		assertParse(
				new Neck(":-", Lists.newArrayList(
						new ComplexTerm("key_1", X),
						new Conjunction(",", Lists.newArrayList(new ComplexTerm("f2", X), new ComplexTerm("g3_f", X),
								new ComplexTerm("h_g", X))))), ":-(key_1(X), ,(f2(X), g3_f(X), h_g(X))).");
	}

	@Test
	public void testTautology() throws ParseException {
		Term a = new ConstantTerm("a");
		Term b = new ConstantTerm("b");
		assertParse(new Unifiable("=", Lists.newArrayList(a, a)), "a=a.");
		assertParse(new Unifiable("=", Lists.newArrayList(a, b)), "a=b.");
		assertParse(new Unifiable("=", Lists.newArrayList(b, a)), "b=a.");
		assertParse(new Unifiable("=", Lists.newArrayList(b, b)), "b=b.");
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

	private List<? extends Term> i(long v) {
		return Lists.newArrayList(new ConstantTerm(BigInteger.valueOf(v)));
	}

	@Test
	public void testFunctor() throws ParseException {
		assertParse(new ComplexTerm("f", i(1)), "f(1).");
		assertParse(new ComplexTerm("fabc", i(-1)), "fabc(-1).");
	}

	@Test
	public void testInteger() throws ParseException {
		assertParse(new ComplexTerm("f", i(1)), "f(1).");
		assertParse(new ComplexTerm("f", i(-1)), "f(-1).");
		assertParse(new ComplexTerm("f", i(123)), "f(123).");
		assertParse(new ComplexTerm("f", i(-123)), "f(-123).");
	}

	private List<? extends Term> d(double v) {
		return Lists.newArrayList(new ConstantTerm(BigDecimal.valueOf(v)));
	}

	@Test
	public void testFloat() throws ParseException {
		assertParse(new ComplexTerm("f", d(1.0)), "f(1.0).");
		assertParse(new ComplexTerm("f", d(-1.0)), "f(-1.0).");
		assertParse(new ComplexTerm("f", d(123.0)), "f(123.0).");
		assertParse(new ComplexTerm("f", d(-123.0)), "f(-123.0).");
		assertParse(new ComplexTerm("f", d(1.0e+10)), "f(1.0e+10).");
		assertParse(new ComplexTerm("f", d(-1.0e+10)), "f(-1.0e+10).");
		assertParse(new ComplexTerm("f", d(1.0e-10)), "f(1.0e-10).");
		assertParse(new ComplexTerm("f", d(-1.0e-10)), "f(-1.0e-10).");
	}

	private List<? extends Term> s(String v) {
		return Lists.newArrayList(new StringTerm(v));
	}

	@Test
	public void testString() throws ParseException {
		assertParse(new ComplexTerm("f", s("a")), "f('a').");
		assertParse(new ComplexTerm("f", s("abc")), "f('abc').");
	}

	private List<? extends Term> a(String v) {
		return Lists.newArrayList(new ConstantTerm(v));
	}

	@Test
	public void testAtom() throws ParseException {
		assertParse(new ComplexTerm("f", a("a")), "f(a).");
		assertParse(new ComplexTerm("f", a("abc")), "f(abc).");
	}

	private List<? extends Term> v(String v) {
		return Lists.newArrayList(new VariableTerm(v));
	}

	@Test
	public void testVariable() throws ParseException {
		assertParse(new ComplexTerm("f", v("X")), "f(X).");
		assertParse(new ComplexTerm("f", v("Xabc")), "f(Xabc).");
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
	public void testE() throws ParseException {
		assertParse(new Unifiable("=", Lists.newArrayList(new VariableTerm("X"), new ConstantTerm(BigInteger.ONE))),
				"X=1.");
		assertParse(new Unifiable("=", Lists.newArrayList(new VariableTerm("X"), new ConstantTerm(BigInteger.ONE))),
				"=(X,1).");
		assertParse(new Unifiable("=", Lists.newArrayList(new VariableTerm("E"), new ConstantTerm(BigInteger.ONE))),
				"E=1.");
		assertParse(new Unifiable("=", Lists.newArrayList(new VariableTerm("E"), new ConstantTerm(BigInteger.ONE))),
				"=(E,1).");
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
