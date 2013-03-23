package org.archstudio.prolog.test;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.engine.Utils;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.op.iso.Conjunction;
import org.archstudio.prolog.op.iso.ListTerm;
import org.archstudio.prolog.op.iso.Neck;
import org.archstudio.prolog.op.iso.NotUnifiable;
import org.archstudio.prolog.op.iso.Unifiable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ProofTest {

	private static class MapMaker {

		Map<VariableTerm, Term> result = Maps.newHashMap();

		public MapMaker add(VariableTerm variable, Term value) {
			result.put(variable, value);
			return this;
		}

		public Map<VariableTerm, Term> toMap() {
			return result;
		}

	}

	UnificationEngine unificationEngine;

	@Before
	public void init() {
		unificationEngine = new MostGeneralUnifierEngine();
	}

	Map<VariableTerm, Term> newMap() {
		return Maps.newHashMap();
	}

	@Test
	public void testProof1() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		ConstantTerm a = new ConstantTerm("a");
		ConstantTerm b = new ConstantTerm("b");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("f", a));
		knowledgeBase.add(new ComplexTerm("f", b));
		knowledgeBase.add(new ComplexTerm("g", a));
		knowledgeBase.add(new ComplexTerm("g", b));
		knowledgeBase.add(new ComplexTerm("h", b));
		knowledgeBase.add(new Neck(new ComplexTerm("k", X), new Conjunction(",", new ComplexTerm("f", X),
				new ComplexTerm("g", X), new ComplexTerm("h", X))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("f", Y);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(Y, a).toMap());
			expected.add(new MapMaker().add(Y, b).toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testProof2() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		ConstantTerm a = new ConstantTerm("a");
		ConstantTerm b = new ConstantTerm("b");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("f", a));
		knowledgeBase.add(new ComplexTerm("f", b));
		knowledgeBase.add(new ComplexTerm("g", a));
		knowledgeBase.add(new ComplexTerm("g", b));
		knowledgeBase.add(new ComplexTerm("h", b));
		knowledgeBase.add(new Neck(new ComplexTerm("k", X), new Conjunction(",", new ComplexTerm("f", X),
				new ComplexTerm("g", X), new ComplexTerm("h", X))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("k", Y);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(Y, b).toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testProof3() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm A = new VariableTerm("A");
		VariableTerm B = new VariableTerm("B");
		VariableTerm C = new VariableTerm("C");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("vincent"), new ConstantTerm("mia")));
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("marcellus"), new ConstantTerm("mia")));
		knowledgeBase.add(new Neck(new ComplexTerm("jealous", A, B), new Conjunction(",",
				new ComplexTerm("loves", A, C), new ComplexTerm("loves", B, C))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("jealous", X, Y);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(X, new ConstantTerm("vincent")).add(Y, new ConstantTerm("vincent")).toMap());
			expected.add(new MapMaker().add(X, new ConstantTerm("vincent")).add(Y, new ConstantTerm("marcellus"))
					.toMap());
			expected.add(new MapMaker().add(X, new ConstantTerm("marcellus")).add(Y, new ConstantTerm("vincent"))
					.toMap());
			expected.add(new MapMaker().add(X, new ConstantTerm("marcellus")).add(Y, new ConstantTerm("marcellus"))
					.toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testProofUnification() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm A = new VariableTerm("A");
		VariableTerm B = new VariableTerm("B");
		VariableTerm C = new VariableTerm("C");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("vincent"), new ConstantTerm("mia")));
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("marcellus"), new ConstantTerm("mia")));
		knowledgeBase.add(new Neck(new ComplexTerm("jealous", A, B), new Conjunction(",",
				new ComplexTerm("loves", A, C), new ComplexTerm("loves", B, C), new Unifiable("==", Lists.newArrayList(
						A, B)))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("jealous", X, Y);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(X, new ConstantTerm("vincent")).add(Y, new ConstantTerm("vincent")).toMap());
			expected.add(new MapMaker().add(X, new ConstantTerm("marcellus")).add(Y, new ConstantTerm("marcellus"))
					.toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testProofNotUnification() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm A = new VariableTerm("A");
		VariableTerm B = new VariableTerm("B");
		VariableTerm C = new VariableTerm("C");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("vincent"), new ConstantTerm("mia")));
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("marcellus"), new ConstantTerm("mia")));
		knowledgeBase.add(new Neck(new ComplexTerm("jealous", A, B), new Conjunction(",",
				new ComplexTerm("loves", A, C), new ComplexTerm("loves", B, C), new NotUnifiable("\\=", Lists
						.newArrayList(A, B)))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("jealous", X, Y);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(X, new ConstantTerm("vincent")).add(Y, new ConstantTerm("marcellus"))
					.toMap());
			expected.add(new MapMaker().add(X, new ConstantTerm("marcellus")).add(Y, new ConstantTerm("vincent"))
					.toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testVariableRenames() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm A = new VariableTerm("A");
		VariableTerm B = new VariableTerm("B");
		VariableTerm C = new VariableTerm("C");
		VariableTerm D = new VariableTerm("D");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(1)), new ConstantTerm(BigInteger
				.valueOf(2))));
		knowledgeBase.add(new Neck(new ComplexTerm("g", X, Y), new ComplexTerm("f", X, Y)));
		knowledgeBase.add(new Neck(new ComplexTerm("h", C, D), new ComplexTerm("g", C, D)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("h", A, B);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(A, new ConstantTerm(BigInteger.valueOf(1)))
					.add(B, new ConstantTerm(BigInteger.valueOf(2))).toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testVariableRenamesWithOverlap() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(1)), new ConstantTerm(BigInteger
				.valueOf(2))));
		knowledgeBase.add(new ComplexTerm("f", new ConstantTerm(BigInteger.valueOf(3)), new ConstantTerm(BigInteger
				.valueOf(4))));
		knowledgeBase.add(new Neck(new ComplexTerm("g", Y, X), new ComplexTerm("f", X, Y)));
		knowledgeBase.add(new Neck(new ComplexTerm("h", X, Y), new ComplexTerm("g", Y, X)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new ComplexTerm("h", X, Y);
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(X, new ConstantTerm(BigInteger.valueOf(1)))
					.add(Y, new ConstantTerm(BigInteger.valueOf(2))).toMap());
			expected.add(new MapMaker().add(X, new ConstantTerm(BigInteger.valueOf(3)))
					.add(Y, new ConstantTerm(BigInteger.valueOf(4))).toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testLists() {
		ConstantTerm a = new ConstantTerm("a");
		ConstantTerm b = new ConstantTerm("b");
		ConstantTerm c = new ConstantTerm("c");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");

		ProofContext proofContext = new ProofContext();
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new Unifiable("=", new ListTerm(X, Y), new ListTerm(a, new ListTerm(b, new ListTerm(c,
					new ListTerm()))));
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(X, a).add(Y, new ListTerm(b, new ListTerm(c, new ListTerm()))).toMap());
			Assert.assertEquals(expected, results);
		}
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Operation o = new Unifiable("=", new ListTerm(a, new ListTerm(b, new ListTerm(c, new ListTerm()))),
					new ListTerm(X, Y));
			for (Map<VariableTerm, Term> v : o.execute(proofContext, unificationEngine, o, Utils.emptyVariables())) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add(X, a).add(Y, new ListTerm(b, new ListTerm(c, new ListTerm()))).toMap());
			Assert.assertEquals(expected, results);
		}
	}
}
