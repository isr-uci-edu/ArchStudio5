package org.archstudio.prolog.test;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.junit.Assert;
import org.junit.Test;

public class TermTest {

	@Test
	public void testContains() {
		Term v = new VariableTerm("X");
		Term c = new ConstantTerm(42);
		Term f = new ComplexTerm("f", v);

		VariableTerm x = new VariableTerm("X");

		Assert.assertTrue(v.contains(x));
		Assert.assertFalse(c.contains(x));
		Assert.assertTrue(f.contains(x));
	}

	@Test
	public void testReplace() {
		Term v = new VariableTerm("X");
		Term c = new ConstantTerm(42);
		Term f = new ComplexTerm("f", v);

		VariableTerm x = new VariableTerm("X");

		Assert.assertEquals(c, v.replace(x, c));
		Assert.assertEquals(new ComplexTerm("f", c), f.replace(x, c));
	}

}
