package org.archstudio.prolog.archstudio.ops;

import java.math.BigInteger;
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
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.hints_3_0.Hint;
import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.ecore.EObject;

public class Alpha extends ComplexTerm implements Executable {

	public Alpha(String name, List<? extends Term> terms) {
		super(name, 2, terms);
	}

	@Override
	public Iterable<Map<VariableTerm, Term>> execute(ProofContext proofContext, UnificationEngine unificationEngine,
			Term source, Map<VariableTerm, Term> variables) {

		Number alphaNumber = PrologUtils.evaluate(proofContext, getTerm(1), variables);
		if (alphaNumber instanceof BigInteger) {
			alphaNumber = alphaNumber.doubleValue() / 255d;
		}
		float alpha = SystemUtils.bound(0, alphaNumber.floatValue(), 1);

		for (Term t : PrologUtils.termOrListTerms(getTerm(0).resolve(proofContext, variables))) {
			EObject eObject = (EObject) ((ConstantTerm) t).getValue();
			IXArchADT xarch = XArchADTProxy.getXArchADT(eObject);

			if (alpha < 1) {
				HintsExtension hintsExtension = XArchADTProxy.getExtension(xarch, eObject,
						Hints_3_0Package.Literals.HINTS_EXTENSION, true);
				Hint hint = null;
				for (Hint currentHint : hintsExtension.getHint()) {
					if (currentHint.getName().equals("alpha")) {
						hint = currentHint;
						break;
					}
				}
				if (hint == null) {
					hint = XArchADTProxy.create(xarch, Hints_3_0Package.Literals.HINT);
					hint.setName("alpha");
					hintsExtension.getHint().add(hint);
				}
				MasterPropertyCoder mpc = new MasterPropertyCoder();
				IEncodedValue ev = mpc.encode(mpc, alpha);
				hint.setHint(ev.getType() + ":" + ev.getData());
			}
			else {
				HintsExtension hintsExtension = XArchADTProxy.getExtension(xarch, eObject,
						Hints_3_0Package.Literals.HINTS_EXTENSION, false);
				if (hintsExtension != null) {
					Hint hint = null;
					for (Hint currentHint : hintsExtension.getHint()) {
						if (currentHint.getName().equals("alpha")) {
							hint = currentHint;
							break;
						}
					}
					if (hint != null) {
						MasterPropertyCoder mpc = new MasterPropertyCoder();
						IEncodedValue ev = mpc.encode(mpc, alpha);
						hint.setHint(ev.getType() + ":" + ev.getData());
					}
				}
			}
		}

		return Collections.singleton(variables);
	}
}
