package org.archstudio.prolog.test;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.op.iso.ListTerm;
import org.archstudio.prolog.parser.ParseException;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
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
		for (Term t : PrologParser.parseTerms(proofContext, statement)) {
			for (Map<VariableTerm, Term> v : ((Executable) t).execute(proofContext, unificationEngine, t,
					Maps.<VariableTerm, Term> newHashMap())) {
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
			expected.add(new Variables().add(X, a).add(Y, new ListTerm(b, new ListTerm(c, new ListTerm()))).done());
			Assert.assertEquals(expected, run("[X|Y]=[a, b, c]."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(X, a).add(Y, new ListTerm(b, new ListTerm(c, new ListTerm()))).done());
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
			Assert.assertEquals(expected, run("teaches(dr_fred, Course), !, studies(Student, Course)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Course, english).add(Student, alice).done());
			Assert.assertEquals(expected, run("teaches(dr_fred, Course), studies(Student, Course), !."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new Variables().add(Course, english).add(Student, alice).done());
			expected.add(new Variables().add(Course, english).add(Student, angus).done());
			expected.add(new Variables().add(Course, drama).add(Student, amelia).done());
			Assert.assertEquals(expected, run("!, teaches(dr_fred, Course), studies(Student, Course)."));
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
			expected.add(new Variables().add(X, Y).add(Y, b1).done());
			Assert.assertEquals(expected, run("X=Y, Y=1, nonvar(X)."));
		}
		{
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			Assert.assertEquals(expected, run("X=Y, Y=X, nonvar(X)."));
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
}
