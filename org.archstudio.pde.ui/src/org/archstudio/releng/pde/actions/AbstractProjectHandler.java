package org.archstudio.releng.pde.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractProjectHandler extends AbstractHandler {

	public AbstractProjectHandler() {
		super();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			for (Object selected : ((IStructuredSelection) selection).toList()) {
				if (selected instanceof IProject) {
					execute((IProject) selected);
				}
				else if (selected instanceof IProjectNature) {
					execute(((IProjectNature) selected).getProject());
				}
				else if (selected instanceof IWorkingSet) {
					execute((IWorkingSet) selected);
				}
			}
		}
		return null;
	}

	protected void execute(IWorkingSet workingSet) {
		for (Object selected : workingSet.getElements()) {
			if (selected instanceof IProject) {
				execute((IProject) selected);
			}
			else if (selected instanceof IProjectNature) {
				execute(((IProjectNature) selected).getProject());
			}
			else if (selected instanceof IWorkingSet) {
				execute((IWorkingSet) selected);
			}
		}
	}

	protected abstract void execute(IProject project);
}
