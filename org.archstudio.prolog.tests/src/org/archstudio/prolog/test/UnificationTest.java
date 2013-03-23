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

		Assert.assertTrue(engine.unifies(context));

		Map<VariableTerm, Term> expectedA = Maps.newHashMap();
		expectedA.put(x, y);
		expectedA.put(y, gR);
		Map<VariableTerm, Term> expectedB = Maps.newHashMap();
		expectedB.put(x, gR);
		expectedB.put(y, gR);
		Map<VariableTerm, Term> expectedC = Maps.newHashMap();
		expectedC.put(x, gR);
		expectedC.put(y, x);

		Assert.assertTrue(context.variables.equals(expectedA) | context.variables.equals(expectedB)
				| context.variables.equals(expectedC));
	}
}
