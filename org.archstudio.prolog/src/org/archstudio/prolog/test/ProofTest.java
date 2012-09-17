package org.archstudio.prolog.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.archstudio.prolog.engine.GenericProofEngine;
import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.ProofEngine;
import org.archstudio.prolog.engine.ProofEngine.MatchIterator;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Equals;
import org.archstudio.prolog.op.NotEquals;
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
		proofEngine = new GenericProofEngine();
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
			MatchIterator i = proofEngine.execute(proofContext, unificationEngine, new ComplexTerm("f", Y));
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Map<VariableTerm, Term> v;
			while ((v = i.next()) != null) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("Y", "a").toMap());
			expected.add(new MapMaker().add("Y", "b").toMap());
			Assert.assertEquals(expected, results);
			
			Assert.assertNull(i.next());
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
			MatchIterator i = proofEngine.execute(proofContext, unificationEngine, new ComplexTerm("k", Y));
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Map<VariableTerm, Term> v;
			while ((v = i.next()) != null) {
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
			MatchIterator i = proofEngine.execute(proofContext, unificationEngine, new ComplexTerm("jealous", X, Y));
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Map<VariableTerm, Term> v;
			while ((v = i.next()) != null) {
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
				"loves", B, C), new Equals(A, B)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			MatchIterator i = proofEngine.execute(proofContext, unificationEngine, new ComplexTerm("jealous", X, Y));
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Map<VariableTerm, Term> v;
			while ((v = i.next()) != null) {
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
				"loves", B, C), new NotEquals(A, B)));

		ProofContext proofContext = new ProofContext(knowledgeBase);
		{
			MatchIterator i = proofEngine.execute(proofContext, unificationEngine, new ComplexTerm("jealous", X, Y));
			Set<Map<VariableTerm, Term>> results = Sets.newHashSet();
			Map<VariableTerm, Term> v;
			while ((v = i.next()) != null) {
				results.add(v);
			}
			Set<Map<VariableTerm, Term>> expected = Sets.newHashSet();
			expected.add(new MapMaker().add("X", "vincent").add("Y", "marcellus").toMap());
			expected.add(new MapMaker().add("X", "marcellus").add("Y", "vincent").toMap());
			Assert.assertEquals(expected, results);
		}
	}

}
