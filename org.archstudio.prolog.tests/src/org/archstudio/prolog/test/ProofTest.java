package org.archstudio.prolog.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.archstudio.prolog.engine.DebugProofEngine;
import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.iso.Equals;
import org.archstudio.prolog.op.iso.NotEquals;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Rule;
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

		public MapMaker add(String variable, Object value) {
			result.put(new VariableTerm(variable), new ConstantTerm(value));
			return this;
		}

		public Map<VariableTerm, Term> toMap() {
			return result;
		}

	}

	UnificationEngine unificationEngine;
	ProofEngine proofEngine;

	@Before
	public void init() {
		unificationEngine = new MostGeneralUnifierEngine();
		proofEngine = new DebugProofEngine();
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
		knowledgeBase.add(new Rule(new ComplexTerm("k", X), new ComplexTerm("f", X), new ComplexTerm("g", X),
				new ComplexTerm("h", X)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("f", Y))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("Y", "a").toMap());
			expected.add(new MapMaker().add("Y", "b").toMap());
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
		knowledgeBase.add(new Rule(new ComplexTerm("k", X), new ComplexTerm("f", X), new ComplexTerm("g", X),
				new ComplexTerm("h", X)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("k", Y))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("Y", "b").toMap());
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
		knowledgeBase.add(new Rule(new ComplexTerm("jealous", A, B), new ComplexTerm("loves", A, C), new ComplexTerm(
				"loves", B, C)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("jealous", X, Y))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("X", "vincent").add("Y", "vincent").toMap());
			expected.add(new MapMaker().add("X", "vincent").add("Y", "marcellus").toMap());
			expected.add(new MapMaker().add("X", "marcellus").add("Y", "vincent").toMap());
			expected.add(new MapMaker().add("X", "marcellus").add("Y", "marcellus").toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testProofEquals() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm A = new VariableTerm("A");
		VariableTerm B = new VariableTerm("B");
		VariableTerm C = new VariableTerm("C");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("vincent"), new ConstantTerm("mia")));
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("marcellus"), new ConstantTerm("mia")));
		knowledgeBase.add(new Rule(new ComplexTerm("jealous", A, B), new ComplexTerm("loves", A, C), new ComplexTerm(
				"loves", B, C), new Equals("==", Lists.newArrayList(A, B))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("jealous", X, Y))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("X", "vincent").add("Y", "vincent").toMap());
			expected.add(new MapMaker().add("X", "marcellus").add("Y", "marcellus").toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testProofNotEquals() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm A = new VariableTerm("A");
		VariableTerm B = new VariableTerm("B");
		VariableTerm C = new VariableTerm("C");
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("vincent"), new ConstantTerm("mia")));
		knowledgeBase.add(new ComplexTerm("loves", new ConstantTerm("marcellus"), new ConstantTerm("mia")));
		knowledgeBase.add(new Rule(new ComplexTerm("jealous", A, B), new ComplexTerm("loves", A, C), new ComplexTerm(
				"loves", B, C), new NotEquals("\\=", Lists.newArrayList(A, B))));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("jealous", X, Y))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("X", "vincent").add("Y", "marcellus").toMap());
			expected.add(new MapMaker().add("X", "marcellus").add("Y", "vincent").toMap());
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
		knowledgeBase.add(new ComplexTerm("f", new ConstantTerm(1), new ConstantTerm(2)));
		knowledgeBase.add(new Rule(new ComplexTerm("g", X, Y), new ComplexTerm("f", X, Y)));
		knowledgeBase.add(new Rule(new ComplexTerm("h", C, D), new ComplexTerm("g", C, D)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("h", A, B))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("A", 1).add("B", 2).toMap());
			Assert.assertEquals(expected, results);
		}
	}

	@Test
	public void testVariableRenamesWithOverlap() {
		List<ComplexTerm> knowledgeBase = Lists.newArrayList();
		VariableTerm X = new VariableTerm("X");
		VariableTerm Y = new VariableTerm("Y");
		knowledgeBase.add(new ComplexTerm("f", new ConstantTerm(1), new ConstantTerm(2)));
		knowledgeBase.add(new ComplexTerm("f", new ConstantTerm(3), new ConstantTerm(4)));
		knowledgeBase.add(new Rule(new ComplexTerm("g", Y, X), new ComplexTerm("f", X, Y)));
		knowledgeBase.add(new Rule(new ComplexTerm("h", X, Y), new ComplexTerm("g", Y, X)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			for (Map<VariableTerm, Term> v : proofEngine.execute(proofContext, unificationEngine, //
					new ComplexTerm("h", X, Y))) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("X", 1).add("Y", 2).toMap());
			expected.add(new MapMaker().add("X", 3).add("Y", 4).toMap());
			Assert.assertEquals(expected, results);
		}
	}
}
