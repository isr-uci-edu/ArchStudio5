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
import org.eclipse.swt.graphics.RGB;

public class Highlight extends ComplexTerm implements Executable {

	public Highlight(String name, List<? extends Term> terms) {
		super(name, 4, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {

		int red = PrologUtils.evaluate(proofContext, getTerm(1), variables).intValue();
		int green = PrologUtils.evaluate(proofContext, getTerm(2), variables).intValue();
		int blue = PrologUtils.evaluate(proofContext, getTerm(3), variables).intValue();
		RGB rgb = new RGB(red, green, blue);

		for (Term t : PrologUtils.termOrListTerms(getTerm(0).resolve(proofContext, variables))) {
			EObject eObject = (EObject) ((ConstantTerm) t).getValue();
			IXArchADT xarch = XArchADTProxy.getXArchADT(eObject);

			HintsExtension hintsExtension = XArchADTProxy.getExtension(xarch, eObject,
					Hints_3_0Package.Literals.HINTS_EXTENSION, true);
			Hint hint = null;
			for (Hint currentHint : hintsExtension.getHint()) {
				if (currentHint.getName().equals("highlight")) {
					hint = currentHint;
					break;
				}
			}
			if (hint == null) {
				hint = XArchADTProxy.create(xarch, Hints_3_0Package.Literals.HINT);
				hint.setName("highlight");
				hintsExtension.getHint().add(hint);
			}
			MasterPropertyCoder mpc = new MasterPropertyCoder();
			IEncodedValue ev = mpc.encode(mpc, rgb);
			hint.setHint(ev.getType() + ":" + ev.getData());
		}

		return Collections.singleton(variables);
	}
}
