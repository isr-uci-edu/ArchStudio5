package org.archstudio.prolog.archstudio;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2MenuContributor;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2MenuContributor;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.prolog.console.PrologConsoleFactory;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

public class ArchipelagoTreeMenuContributor extends AbstractArchipelago2MenuContributor
		implements IArchipelago2MenuContributor {
	public ArchipelagoTreeMenuContributor() {
	}

	@Override
	public void fillMenu(IArchipelago2Outline outline, List<Object> element, IMenuManager menuManager) {
		final ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
		if (objRef != null) {
			IAction newStructureAction = new Action("Generate Prolog Facts") {
				@Override
				public void run() {
					ProofContext proofContext = process(XArchADTProxy.<EObject> proxy(xarch, objRef));
					PrologConsoleFactory.openConsole(proofContext);
				}
			};
			menuManager.add(newStructureAction);
		}
	}

	public ProofContext process(final EObject eObject) {
		final ProofContext[] proofContextResult = new ProofContext[1];
		try {
			PlatformUI.getWorkbench().getProgressService().run(true, true, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Prolog", 1);
					try {
						ProofContext proofContext = new ProofContext();
						proofContext.add(PrologUtils.getFacts(proofContext, subMonitor.newChild(1), eObject));
						proofContextResult[0] = proofContext;
					}
					catch (Throwable t) {
						throw new InvocationTargetException(t);
					}
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (proofContextResult[0] == null) {
			throw new NullPointerException();
		}
		return proofContextResult[0];
	}
}
