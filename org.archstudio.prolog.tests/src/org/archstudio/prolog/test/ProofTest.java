package org.archstudio.prolog.test;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.parser.ParseException;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ProofTest {

	private static class Variables {

		Map<VariableTerm, Term> result = Maps.newHashMap();

		public Variables add(VariableTerm variable, Term value) {
			result.put(variable, value);
			return this;
		}

		public Map<VariableTerm, Term> done() {
			return result;
		}
	}

	ProofContext proofContext;
	UnificationEngine unificationEngine;

	@Before
	public void init() {
		proofContext = new ProofContext();
		unificationEngine = new MostGeneralUnifierEngine();
	}

	protected Set<Map<VariableTerm, Term>> run(String statement) throws ParseException {
		Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
		ProofContext clonedContext = proofContext.clone();
		for (Term t : PrologParser.parseTerms(clonedContext, statement)) {
			for (Map<VariableTerm, Term> v : PrologUtils.execute(clonedContext, unificationEngine, t)) {
				results.add(v);
			}
		}
		return results;
	}

	protected Iterable<ComplexTerm> parse(String statement) throws ParseException {
		return Iterables.filter(PrologParser.parseTerms(proofContext, statement), ComplexTerm.class);
	}

	ConstantTerm a = new ConstantTerm("a");
	ConstantTerm b = new ConstantTerm("b");
	ConstantTerm c = new ConstantTerm("c");
	VariableTerm A = new VariableTerm("A");
	VariableTerm B = new VariableTerm("B");
	VariableTerm C = new VariableTerm("C");
	VariableTerm D = new VariableTerm("D");
	VariableTerm X = new VariableTerm("X");
	VariableTerm Y = new VariableTerm("Y");
	VariableTerm Z = new VariableTerm("Z");
	ConstantTerm b0 = new ConstantTerm(BigInteger.valueOf(0));
	ConstantTerm b1 = new ConstantTerm(BigInteger.valueOf(1));
	ConstantTerm b2 = new ConstantTerm(BigInteger.valueOf(2));
	ConstantTerm b3 = new ConstantTerm(BigInteger.valueOf(3));
	ConstantTerm b4 = new ConstantTerm(BigInteger.valueOf(4));

	@Test
	public void testExample() throws ParseException {
		proofContext.add(parse("f(a)."));
		proofContext.add(parse("f(b)."));
		proofContext.add(parse("g(a)."));
		proofContext.add(parse("g(b)."));
		proofContext.add(parse("h(b)."));
		proofContext.add(parse("k(X) :- f(X), g(X), h(X)."));
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Y, a).done());
			expected.add(new Variables().add(Y, b).done());
			Assert.assertEquals(expected, run("f(Y)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Y, b).done());
			Assert.assertEquals(expected, run("k(Y)."));
		}
	}

	@Test
	public void testProofNotUnification() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1=1.")); // unifies with
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("1\\=1.")); // unifies with
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1\\=1.0."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1.0\\=1."));
		}

		ConstantTerm vincent = new ConstantTerm("vincent");
		ConstantTerm marcellus = new ConstantTerm("marcellus");

		proofContext.add(parse("loves(vincent, mia)."));
		proofContext.add(parse("loves(marcellus, mia)."));
		proofContext.add(parse("jealous(A, B) :- loves(A, C), loves(B, C), A \\= B."));
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, vincent).add(Y, marcellus).done());
			expected.add(new Variables().add(X, marcellus).add(Y, vincent).done());
			Assert.assertEquals(expected, run("jealous(X, Y)."));
		}
	}

	@Test
	public void testVariableRenames() throws ParseException {
		proofContext.add(parse("f(1, 2)."));
		proofContext.add(parse("g(X, Y) :- f(X, Y)."));
		proofContext.add(parse("h(C, D) :- g(C, D)."));
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(A, b1).add(B, b2).done());
			Assert.assertEquals(expected, run("h(A, B)."));
		}
	}

	@Test
	public void testVariableRenamesWithOverlap() throws ParseException {
		proofContext.add(parse("f(1, 2)."));
		proofContext.add(parse("f(3, 4)."));
		proofContext.add(parse("g(Y, X) :- f(X, Y)."));
		proofContext.add(parse("h(X, Y) :- g(Y, X)."));
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).add(Y, b2).done());
			expected.add(new Variables().add(X, b3).add(Y, b4).done());
			Assert.assertEquals(expected, run("h(X, Y)."));
		}
	}

	@Test
	public void testLists() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).add(Y, ListTerm.asListTerm(b, c)).done());
			Assert.assertEquals(expected, run("[X|Y]=[a, b, c]."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).add(Y, ListTerm.asListTerm(b, c)).done());
			Assert.assertEquals(expected, run("[a, b, c]=[X|Y]."));
		}
	}

	@Test
	public void testDisjunction() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).done());
			Assert.assertEquals(expected, run("X=a; X=b."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b).done());
			Assert.assertEquals(expected, run("X=a, false; X=b."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).done());
			Assert.assertEquals(expected, run("X=a; X=b; X=c."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b).done());
			Assert.assertEquals(expected, run("X=a, false; X=b; X=c."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, c).done());
			Assert.assertEquals(expected, run("X=a, false; X=b, false; X=c."));
		}
	}

	@Test
	public void testIfThenElse() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b).done());
			Assert.assertEquals(expected, run("false -> false, X=a; X=b."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("true -> false, X=a; X=b."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b).done());
			Assert.assertEquals(expected, run("false -> true, X=a; X=b."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).done());
			Assert.assertEquals(expected, run("true -> true, X=a; X=b."));
		}
	}

	@Test
	public void testIfThen() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).add(Y, a).done());
			Assert.assertEquals(expected, run("X=a, true -> Y=a ; Y=b."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Y, b).done());
			Assert.assertEquals(expected, run("X=a, false -> Y=a ; Y=b."));
		}
		proofContext.add(parse("dog(a)."));
		proofContext.add(parse("dog(b)."));
		proofContext.add(parse("dog(c)."));
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).add(Y, b).done());
			Assert.assertEquals(expected, run("dog(X), true -> Y=b ; Y=c."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Y, c).done());
			Assert.assertEquals(expected, run("dog(X), false -> Y=b ; Y=c."));
		}
	}

	@Test
	public void testSoftCut() throws ParseException {
		proofContext.add(parse("dog(a)."));
		proofContext.add(parse("dog(b)."));
		proofContext.add(parse("dog(c)."));
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).add(Y, b).done());
			expected.add(new Variables().add(X, b).add(Y, b).done());
			expected.add(new Variables().add(X, c).add(Y, b).done());
			Assert.assertEquals(expected, run("dog(X), true *-> Y=b ; Y=c."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Y, c).done());
			Assert.assertEquals(expected, run("dog(X), false *-> Y=b ; Y=c."));
		}
	}

	@Test
	public void testEquals() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1=1.")); // unifies with
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1==1.")); // equals
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("1==1.0."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("1.0==1."));
		}
	}

	@Test
	public void testCut() throws ParseException {
		VariableTerm Course = new VariableTerm("Course");
		VariableTerm Student = new VariableTerm("Student");
		ConstantTerm english = new ConstantTerm("english");
		ConstantTerm drama = new ConstantTerm("drama");
		ConstantTerm alice = new ConstantTerm("alice");
		ConstantTerm angus = new ConstantTerm("angus");
		ConstantTerm amelia = new ConstantTerm("amelia");

		proofContext.add(parse("teaches(dr_fred, history)."));
		proofContext.add(parse("teaches(dr_fred, english)."));
		proofContext.add(parse("teaches(dr_fred, drama)."));
		proofContext.add(parse("teaches(dr_fiona, physics)."));
		proofContext.add(parse("studies(alice, english)."));
		proofContext.add(parse("studies(angus, english)."));
		proofContext.add(parse("studies(amelia, drama)."));
		proofContext.add(parse("studies(alex, physics)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Course, english).add(Student, alice).done());
			expected.add(new Variables().add(Course, english).add(Student, angus).done());
			expected.add(new Variables().add(Course, drama).add(Student, amelia).done());
			Assert.assertEquals(expected, run("teaches(dr_fred, Course), studies(Student, Course)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Course, english).add(Student, alice).done());
			expected.add(new Variables().add(Course, english).add(Student, angus).done());
			expected.add(new Variables().add(Course, drama).add(Student, amelia).done());
			Assert.assertEquals(expected, run("!, teaches(dr_fred, Course), studies(Student, Course)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("teaches(dr_fred, Course), !, studies(Student, Course)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Course, english).add(Student, alice).done());
			Assert.assertEquals(expected, run("teaches(dr_fred, Course), studies(Student, Course), !."));
		}

		proofContext.add(parse("max(X, Y, Y) :- X =< Y, !."));
		proofContext.add(parse("max(X, Y, X)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b2).done());
			Assert.assertEquals(expected, run("max(1, 2, X)."));
			Assert.assertEquals(expected, run("max(2, 1, X)."));
		}

		ConstantTerm invalid_pension = new ConstantTerm("invalid_pension");
		ConstantTerm old_age_pension = new ConstantTerm("old_age_pension");
		ConstantTerm supplementary_benefit = new ConstantTerm("supplementary_benefit");
		ConstantTerm nothing = new ConstantTerm("nothing");

		proofContext.add(parse("pension(X, invalid_pension) :- invalid(X), !."));
		proofContext.add(parse("pension(X, old_age_pension) :- over_65(X), paid_up(X), !."));
		proofContext.add(parse("pension(X, supplementary_benefit) :- over_65(X), !."));
		proofContext.add(parse("pension(_, nothing)."));

		proofContext.add(parse("invalid(fred)."));
		proofContext.add(parse("over_65(fred)."));
		proofContext.add(parse("over_65(joe)."));
		proofContext.add(parse("over_65(jim)."));
		proofContext.add(parse("paid_up(fred)."));
		proofContext.add(parse("paid_up(joe)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, invalid_pension).done());
			Assert.assertEquals(expected, run("pension(fred, X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, old_age_pension).done());
			Assert.assertEquals(expected, run("pension(joe, X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, supplementary_benefit).done());
			Assert.assertEquals(expected, run("pension(jim, X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, nothing).done());
			Assert.assertEquals(expected, run("pension(sally, X)."));
		}
	}

	@Test
	public void testVar() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("var(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("X=1, var(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("X=Y, Y=1, var(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, Y).done());
			Assert.assertEquals(expected, run("X=Y, Y=X, var(X)."));
		}
	}

	@Test
	public void testNonVar() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("nonvar(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			Assert.assertEquals(expected, run("X=1, nonvar(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).add(Y, b1).done());
			Assert.assertEquals(expected, run("X=Y, Y=1, nonvar(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("X=Z, Y=X, nonvar(X)."));
		}
	}

	@Test
	public void testAtom() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atom('a')."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atom(a)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atom([])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atom(!)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ atom(1)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ atom([1])."));
		}
	}

	@Test
	public void testAtomic() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atomic('a')."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atomic(a)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atomic([])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atomic(!)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("atomic(1)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ atomic([1])."));
		}
	}

	@Test
	public void testCompound() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ compound('a')."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ compound(a)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ compound([])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ compound(!)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ compound(1)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("\\+ compound([1])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("compound(a(1))."));
		}
	}

	@Test
	public void testValueEquals() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1 =:= 1."));
			Assert.assertEquals(expected, run("1.0 =:= 1."));
			Assert.assertEquals(expected, run("1 =:= 1.0."));
			Assert.assertEquals(expected, run("1.0 =:= 1.0."));
			Assert.assertEquals(expected, run("1 =\\= 2."));
			Assert.assertEquals(expected, run("1.0 =\\= 2."));
			Assert.assertEquals(expected, run("1 =\\= 2.0."));
			Assert.assertEquals(expected, run("1.0 =\\= 2.0."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("1 =:= 2."));
			Assert.assertEquals(expected, run("1.0 =:= 2."));
			Assert.assertEquals(expected, run("1 =:= 2.0."));
			Assert.assertEquals(expected, run("1.0 =:= 2.0."));
			Assert.assertEquals(expected, run("1 =\\= 1."));
			Assert.assertEquals(expected, run("1.0 =\\= 1."));
			Assert.assertEquals(expected, run("1 =\\= 1.0."));
			Assert.assertEquals(expected, run("1.0 =\\= 1.0."));
		}
	}

	@Test
	public void testAlphaEquals() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> t = Sets.newHashSet();
			t.add(new Variables().done());
			Set<Map<VariableTerm, Term>> f = Sets.newHashSet();

			Assert.assertEquals(t, run("'1' == '1'."));
			Assert.assertEquals(f, run("'1.0' == '1'."));
			Assert.assertEquals(f, run("'1' == '1.0'."));
			Assert.assertEquals(t, run("'1.0' == '1.0'."));
			Assert.assertEquals(t, run("'1' \\== '2'."));
			Assert.assertEquals(t, run("'1.0' \\== '2'."));
			Assert.assertEquals(t, run("'1' \\== '2.0'."));
			Assert.assertEquals(t, run("'1.0' \\== '2.0'."));
			Assert.assertEquals(f, run("'1' == '2'."));
			Assert.assertEquals(f, run("'1.0' == '2'."));
			Assert.assertEquals(f, run("'1' == '2.0'."));
			Assert.assertEquals(f, run("'1.0' == '2.0'."));
			Assert.assertEquals(f, run("'1' \\== '1'."));
			Assert.assertEquals(t, run("'1.0' \\== '1'."));
			Assert.assertEquals(t, run("'1' \\== '1.0'."));
			Assert.assertEquals(f, run("'1.0' \\== '1.0'."));
		}
	}

	@Test
	public void testValueInequalities() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("1 =< 1."));
			Assert.assertEquals(expected, run("1 =< 2."));
			Assert.assertEquals(expected, run("1 < 2."));
			Assert.assertEquals(expected, run("1 >= 1."));
			Assert.assertEquals(expected, run("2 >= 1."));
			Assert.assertEquals(expected, run("2 > 1."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("1 =< 0."));
			Assert.assertEquals(expected, run("1 < 1."));
			Assert.assertEquals(expected, run("1 < 0."));
			Assert.assertEquals(expected, run("0 >= 1."));
			Assert.assertEquals(expected, run("1 > 1."));
			Assert.assertEquals(expected, run("0 > 1."));
		}
	}

	@Test
	public void testAlphaInequalities() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("'1' @=< '1'."));
			Assert.assertEquals(expected, run("'1' @=< '2'."));
			Assert.assertEquals(expected, run("'1' @< '2'."));
			Assert.assertEquals(expected, run("'1' @>= '1'."));
			Assert.assertEquals(expected, run("'2' @>= '1'."));
			Assert.assertEquals(expected, run("'2' @> '1'."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("'1' @=< '0'."));
			Assert.assertEquals(expected, run("'1' @< '1'."));
			Assert.assertEquals(expected, run("'1' @< '0'."));
			Assert.assertEquals(expected, run("'0' @>= '1'."));
			Assert.assertEquals(expected, run("'1' @> '1'."));
			Assert.assertEquals(expected, run("'0' @> '1'."));
		}
	}

	@Test
	public void testAbs() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			Assert.assertEquals(expected, run("X is abs(-1)."));
			Assert.assertEquals(expected, run("X is abs(1)."));
		}
	}

	@Test
	public void testMember() throws ParseException {
		proofContext.add(parse("member(X, [X|_])."));
		proofContext.add(parse("member(X, [_|T]) :- member(X, T)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("member(1, [1])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("member(1, [1, 1])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("member(1, [1, 2])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("member(1, [1, 2, 3])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("member(2, [1, 2, 3])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().done());
			Assert.assertEquals(expected, run("member(3, [1, 2, 3])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("member(4, [1, 2, 3])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			Assert.assertEquals(expected, run("member(X, [1])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			expected.add(new Variables().add(X, b2).done());
			Assert.assertEquals(expected, run("member(X, [1, 2])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			expected.add(new Variables().add(X, b2).done());
			expected.add(new Variables().add(X, b3).done());
			Assert.assertEquals(expected, run("member(X, [1, 2, 3])."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).add(Y, ListTerm.asListTerm(b1, b2, b3)).done());
			expected.add(new Variables().add(X, b2).add(Y, ListTerm.asListTerm(b1, b2, b3)).done());
			expected.add(new Variables().add(X, b3).add(Y, ListTerm.asListTerm(b1, b2, b3)).done());
			Assert.assertEquals(expected, run("Y=[1, 2, 3], member(X, Y)."));
		}
	}

	@Test
	public void testFindAll() throws ParseException {
		//ConstantTerm martha = new ConstantTerm("martha");
		ConstantTerm charlotte = new ConstantTerm("charlotte");
		ConstantTerm caroline = new ConstantTerm("caroline");
		ConstantTerm laura = new ConstantTerm("laura");
		ConstantTerm rose = new ConstantTerm("rose");

		proofContext.add(parse("child(martha,charlotte)."));
		proofContext.add(parse("child(charlotte,caroline)."));
		proofContext.add(parse("child(caroline,laura)."));
		proofContext.add(parse("child(laura,rose)."));
		proofContext.add(parse("descend(X,Y) :- child(X,Y)."));
		proofContext.add(parse("descend(X,Y) :- child(X,Z), descend(Z,Y)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(charlotte, caroline, laura, rose)).done());
			Assert.assertEquals(expected, run("findall(X, descend(martha, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(caroline, laura, rose)).done());
			Assert.assertEquals(expected, run("findall(X, descend(charlotte, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(laura, rose)).done());
			Assert.assertEquals(expected, run("findall(X, descend(caroline, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(rose)).done());
			Assert.assertEquals(expected, run("findall(X, descend(laura, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm()).done());
			Assert.assertEquals(expected, run("findall(X, descend(rose, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z,
					ListTerm.asListTerm(charlotte, caroline, laura, rose, caroline, laura, rose, laura, rose, rose))
					.done());
			Assert.assertEquals(expected, run("findall(X, descend(Mother, X), Z)."));
		}
		{
			// findall(Y,descend(martha,X),Z) % result is Z = unbound internal variables of length 4 (one for each solution)
			Set<Map<VariableTerm, Term>> results = run("findall(Y, descend(martha, X), Z).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, Z
			Term value = result.get(Z);
			Assert.assertEquals(4, ((ListTerm) value).length()); // of length 4
		}
	}

	@Test
	public void testBagOf() throws ParseException {

		VariableTerm Mother = new VariableTerm("Mother");
		ConstantTerm martha = new ConstantTerm("martha");
		ConstantTerm charlotte = new ConstantTerm("charlotte");
		ConstantTerm caroline = new ConstantTerm("caroline");
		ConstantTerm laura = new ConstantTerm("laura");
		ConstantTerm rose = new ConstantTerm("rose");

		proofContext.add(parse("child(martha,charlotte)."));
		proofContext.add(parse("child(charlotte,caroline)."));
		proofContext.add(parse("child(caroline,laura)."));
		proofContext.add(parse("child(laura,rose)."));
		proofContext.add(parse("descend(X,Y) :- child(X,Y)."));
		proofContext.add(parse("descend(X,Y) :- child(X,Z), descend(Z,Y)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(charlotte, caroline, laura, rose)).done());
			Assert.assertEquals(expected, run("bagof(X, descend(martha, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(caroline, laura, rose)).done());
			Assert.assertEquals(expected, run("bagof(X, descend(charlotte, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(laura, rose)).done());
			Assert.assertEquals(expected, run("bagof(X, descend(caroline, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, ListTerm.asListTerm(rose)).done());
			Assert.assertEquals(expected, run("bagof(X, descend(laura, X), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			// bagof does not return empty lists, rose fails
			Assert.assertEquals(expected, run("bagof(X, descend(rose, X), Z)."));
		}
		{
			// FIXME: bagof/3 order matters? But, test does not check for this!

			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Mother, martha) //
					.add(Z, ListTerm.asListTerm(charlotte, caroline, laura, rose)).done());
			expected.add(new Variables().add(Mother, charlotte) //
					.add(Z, ListTerm.asListTerm(caroline, laura, rose)).done());
			expected.add(new Variables().add(Mother, caroline) //
					.add(Z, ListTerm.asListTerm(laura, rose)).done());
			expected.add(new Variables().add(Mother, laura) //
					.add(Z, ListTerm.asListTerm(rose)).done());
			// bagof does not return empty lists, thus rose fails
			Assert.assertEquals(expected, run("bagof(X, descend(Mother, X), Z)."));
		}
		{
			// FIXME: bagof(Y,descend(martha,X),Z) % results are not understood!
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables()//
					.add(Z, ListTerm.asListTerm(//
							charlotte, caroline, laura, rose,//
							caroline, laura, rose,//
							laura, rose,//
							rose//
							)).done());
			Assert.assertEquals(expected, run("bagof(X, Mother^descend(Mother, X), Z)."));
		}
	}

	@Test
	public void testSetOf() throws ParseException {

		ConstantTerm harry = new ConstantTerm("harry");
		ConstantTerm draco = new ConstantTerm("draco");
		ConstantTerm ron = new ConstantTerm("ron");
		ConstantTerm hermione = new ConstantTerm("hermione");
		ConstantTerm dumbledore = new ConstantTerm("dumbledore");
		ConstantTerm hagrid = new ConstantTerm("hagrid");
		ConstantTerm b13 = new ConstantTerm(BigInteger.valueOf(13));
		ConstantTerm b14 = new ConstantTerm(BigInteger.valueOf(14));
		ConstantTerm b30 = new ConstantTerm(BigInteger.valueOf(30));
		ConstantTerm b60 = new ConstantTerm(BigInteger.valueOf(60));

		proofContext.add(parse("age(harry,13)."));
		proofContext.add(parse("age(draco,14)."));
		proofContext.add(parse("age(ron,13)."));
		proofContext.add(parse("age(hermione,13)."));
		proofContext.add(parse("age(dumbledore,60)."));
		proofContext.add(parse("age(hagrid,30)."));

		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, //
					ListTerm.asListTerm(draco, dumbledore, hagrid, harry, hermione, ron)).done());
			Assert.assertEquals(expected, run("setof(X, Y^age(X, Y), Z)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Z, //
					ListTerm.asListTerm(b13, b14, b30, b60)).done());
			Assert.assertEquals(expected, run("setof(Y, X^age(X, Y), Z)."));
		}
	}

	@Test
	public void testSort() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, ListTerm.asListTerm()).done());
			Assert.assertEquals(expected, run("sort([], X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, ListTerm.asListTerm(b1)).done());
			Assert.assertEquals(expected, run("sort([1], X)."));
			Assert.assertEquals(expected, run("sort([1, 1], X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, ListTerm.asListTerm(b1, b2)).done());
			Assert.assertEquals(expected, run("sort([1, 2], X)."));
			Assert.assertEquals(expected, run("sort([1, 1, 2], X)."));
			Assert.assertEquals(expected, run("sort([1, 2, 2], X)."));
			Assert.assertEquals(expected, run("sort([2, 1], X)."));
			Assert.assertEquals(expected, run("sort([2, 2, 1], X)."));
			Assert.assertEquals(expected, run("sort([2, 1, 1], X)."));
		}
	}

	@Test
	public void testLength() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b0).done());
			Assert.assertEquals(expected, run("length([], X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			Assert.assertEquals(expected, run("length([1], X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b2).done());
			Assert.assertEquals(expected, run("length([1, 2], X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b3).done());
			Assert.assertEquals(expected, run("length([1, 2, 3], X)."));
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length(X, 0).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(0, ((ListTerm) value).length()); // of length 0
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length(X, 1).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(1, ((ListTerm) value).length()); // of length 1
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length(X, 2).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(2, ((ListTerm) value).length()); // of length 2
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length(X, 3).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(3, ((ListTerm) value).length()); // of length 3
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length([1|X], 0).");
			Assert.assertEquals(0, results.size()); // no results
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length([1|X], 1).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(0, ((ListTerm) value).length()); // of length 0
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length([1|X], 2).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(1, ((ListTerm) value).length()); // of length 1
		}
		{
			Set<Map<VariableTerm, Term>> results = run("length([1|X], 3).");
			Assert.assertEquals(1, results.size()); // one result
			Map<VariableTerm, Term> result = results.iterator().next();
			Assert.assertEquals(1, result.size()); // with one variable, X
			Term value = result.get(X);
			Assert.assertEquals(2, ((ListTerm) value).length()); // of length 2
		}
	}

	@Test
	public void testNot() throws ParseException {
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			Assert.assertEquals(expected, run("X=1, \\+ X=2."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("X=1, \\+ X=1."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, b1).done());
			Assert.assertEquals(expected, run("X=1, \\+ \\+ X=1."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("X=1, \\+ \\+ X=2."));
		}
	}

}
