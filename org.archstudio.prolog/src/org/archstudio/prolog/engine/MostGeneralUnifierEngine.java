package org.archstudio.prolog.engine;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;

/*
 * A unifer based on
 * http://www.univ-orleans.fr/lifo/software/stdprolog/unification.html
 */

public class MostGeneralUnifierEngine implements UnificationEngine {

	@Override
	public boolean unify(UnificationContext context) {
		while (context.equations.size() > 0) {
			for (Equation e : Lists.newArrayList(context.equations)) {
				Term f = e.term1;
				Term g = e.term2;

				// 2 & 3
				if (f.equals(g)) {
					context.equations.remove(e);
					continue;
				}
				// 4
				if (f instanceof ComplexTerm) {
					if (!(g instanceof ComplexTerm)) {
						// 1.2b
						return false;
					}
					ComplexTerm c = (ComplexTerm) f;
					ComplexTerm d = (ComplexTerm) g;
					if (!(c.getFunctor().equals(d.getFunctor()))) {
						// 1.3
						return false;
					}
					if (c.getArity() != d.getArity()) {
						// 1.4
						return false;
					}
					context.equations.remove(e);
					for (int i = c.getArity() - 1; i >= 0; i--) {
						// 4
						context.equations.add(new Equation(c.getTerm(i), d.getTerm(i)));
					}
					continue;
				}
				if (g instanceof ComplexTerm && !(f instanceof VariableTerm)) {
					// 1.2a
					return false;
				}
				// 5
				if (g instanceof VariableTerm && !(f instanceof VariableTerm)) {
					context.equations.remove(e);
					context.equations.add(e = new Equation(g, f));
					Term t = f;
					f = g;
					g = t;
				}
				// 6
				if (f instanceof VariableTerm) {
					VariableTerm v = (VariableTerm) f;
					if (!g.contains(v)) {
						context.variables.put(v, g);
						for (Equation e2 : Lists.newArrayList(context.equations)) {
							context.equations.remove(e2);
							context.equations.add(new Equation(e2.term1.replace(v, g), e2.term2.replace(v, g)));
						}
						continue;
					}
					else {
						// 7
						return false;
					}
				}
				// 1.1
				if (!f.equals(g)) {
					return false;
				}
			}
		}
		return true;
	}
}
