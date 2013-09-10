package org.archstudio.prolog.op.iso;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;

public class WriteNewLine extends ComplexTerm implements Executable {

	public WriteNewLine(String name, List<? extends Term> terms) {
		super(name, 0, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {

		try {
			proofContext.getOutput().newLine();
			proofContext.getOutput().flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.singleton(variables);
	}
}
