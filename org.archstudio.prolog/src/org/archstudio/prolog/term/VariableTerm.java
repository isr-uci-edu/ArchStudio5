package org.archstudio.prolog.term;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.archstudio.prolog.engine.ProofContext;

import com.google.common.collect.Sets;

public class VariableTerm extends AbstractTerm implements Term {

	final String name;

	public VariableTerm(String name) {
		this.name = checkNotNull(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public Term resolve(ProofContext proofContext, Map<VariableTerm, Term> variables) {
		Term t = this;
		Set<VariableTerm> termsVisited = Sets.newHashSet();
		while (variables.containsKey(t)) {
			if (!termsVisited.add((VariableTerm) t)) {
				return Collections.min(termsVisited);
			}
			t = variables.get(t);
		}
		return t;
	}

	@Override
	public String toString() {
		return name.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VariableTerm other = (VariableTerm) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
