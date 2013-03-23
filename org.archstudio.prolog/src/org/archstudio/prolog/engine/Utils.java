package org.archstudio.prolog.engine;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

import com.google.common.collect.Lists;

public class Utils {

	public static final Map<VariableTerm, Term> emptyVariables() {
		return Collections.emptyMap();
	}

	@SuppressWarnings("unchecked")
	public static final List<Map<VariableTerm, Term>> asList(Map<VariableTerm, Term> variables) {
		return Lists.newArrayList(variables);
	}

}
