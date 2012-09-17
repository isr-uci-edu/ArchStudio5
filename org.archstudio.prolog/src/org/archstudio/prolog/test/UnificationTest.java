package org.archstudio.prolog.test;

import java.util.Map;

import junit.framework.Assert;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.UnificationContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;

public class UnificationTest {

	UnificationEngine engine;

	@Before
	public void init() {
		engine = new MostGeneralUnifierEngine();
	}

	@Test
	public void testSuccess() {
		VariableTerm r = new VariableTerm("R");
		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");
		Term fXX = new ComplexTerm("f", x, x);
		Term gR = new ComplexTerm("g", r);
		Term fYgR = new ComplexTerm("f", y, gR);

		UnificationContext context = new UnificationContext(fXX, fYgR);

		Assert.assertTrue(engine.unify(context));

		Map<VariableTerm, Term> expected = Maps.newHashMap();
		expected.put(x, gR);
		expected.put(y, gR);
		Assert.assertEquals(expected, context.variables);
	}


	@Test
	public void testFailure() {
		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");
		Term fXX = new ComplexTerm("f", x, x);
		Term gX = new ComplexTerm("g", x);
		Term fYgX = new ComplexTerm("f", y, gX);

		UnificationContext context = new UnificationContext(fXX, fYgX);

		Assert.assertFalse(engine.unify(context));
	}
}
