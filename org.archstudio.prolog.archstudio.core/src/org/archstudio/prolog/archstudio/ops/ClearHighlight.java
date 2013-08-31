package org.archstudio.prolog.archstudio.ops;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.MasterPropertyCoder;
import org.archstudio.prolog.engine.PrologUtils;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.xadl3.hints_3_0.Hint;
import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.ecore.EObject;

public class ClearHighlight extends ComplexTerm implements Executable {

	public ClearHighlight(String name, List<? extends Term> terms) {
		super(name, 1, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {

		for (Term t : PrologUtils.termOrListTerms(getTerm(0).resolve(proofContext, variables))) {
			EObject eObject = (EObject) ((ConstantTerm) t).getValue();
			IXArchADT xarch = XArchADTProxy.getXArchADT(eObject);

			HintsExtension hintsExtension = XArchADTProxy.getExtension(xarch, eObject,
					Hints_3_0Package.Literals.HINTS_EXTENSION, false);
			if (hintsExtension != null) {
				Hint hint = null;
				for (Hint currentHint : hintsExtension.getHint()) {
					if (currentHint.getName().equals("highlight")) {
						hint = currentHint;
						break;
					}
				}
				if (hint != null) {
					MasterPropertyCoder mpc = new MasterPropertyCoder();
					IEncodedValue ev = mpc.encode(mpc, null);
					hint.setHint(ev.getType() + ":" + ev.getData());
				}
			}
		}

		return Collections.singleton(variables);
	}
}
