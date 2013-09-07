package org.archstudio.prolog.engine;

import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Sets;

/*
 * A unifer based on http://www.univ-orleans.fr/lifo/software/stdprolog/unification.html
 */

public class MostGeneralUnifierEngine implements UnificationEngine {

	@Override
	public boolean unifies(ProofContext proofContext, UnificationContext context) {
		for (int i = 0; i < context.equations.size(); i++) {
			Equation e = context.equations.get(i);
			Term f = e.term1;
			Term g = e.term2;

			// 2 & 3
			if (f.equals(g)) {
				continue;
			}
			// 5
			if (g instanceof VariableTerm && !(f instanceof VariableTerm)) {
				Term t = f;
				f = g;
				g = t;
				context.equations.set(i, new Equation(f, g));
				// fall through
			}
			// 6
			if (f instanceof VariableTerm) {
				VariableTerm v = (VariableTerm) f;
				if (!v.getName().equals("_")) {
					context.variables.put(v, g);
					for (int j = i + 1; j < context.equations.size(); j++) {
						Equation e2 = context.equations.get(j);
						context.equations.set(j, new Equation(e2.term1.resolve(proofContext, context.variables),
								e2.term2.resolve(proofContext, context.variables)));
					}
				}
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
				if (!c.getFunctor().equals(d.getFunctor())) {
					// 1.3
					return false;
				}
				if (c.getArity() != d.getArity()) {
					// 1.4
					return false;
				}
				for (int j = 0; j < c.getArity(); j++) {
					// 4
					context.equations.add(new Equation(c.getTerm(j), d.getTerm(j)));
				}
				continue;
			}
			// added
			if (f instanceof ListTerm) {
				if (!(g instanceof ListTerm)) {
					return false;
				}

				ListTerm c = (ListTerm) f;
				ListTerm d = (ListTerm) g;
				if (c.isEmpty()) {
					if (d.isEmpty()) {
						continue;
					}
					return false;
				}
				if (d.isEmpty()) {
					return false;
				}

				context.equations.add(new Equation(c.getHead(), d.getHead()));
				context.equations.add(new Equation(c.getTail(), d.getTail()));
				continue;
			}
			if (g instanceof ComplexTerm && !(f instanceof VariableTerm)) {
				// 1.2a
				return false;
			}
			// 1.1
			return false;
		}

		// Note: This is necessary otherwise, intermediate variables do not get resolved
		// and results (especially with lists) end up looking weird and incomplete.
		// FIXME: check for loops, self references, and do not simplify when those occur
		for (Entry<VariableTerm, Term> entry : context.variables.entrySet()) {
			Term value = entry.getValue();
			int tries = 0;
			Set<Term> seenTerms = Sets.newHashSet();
			while (seenTerms.add(value) && tries++ < 20) {
				value = value.resolve(proofContext, context.variables);
				entry.setValue(value);
			}
		}

		return true;
	}
}
