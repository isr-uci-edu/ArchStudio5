package org.archstudio.eclipse.ui.actions;

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

import com.google.common.collect.Lists;

public abstract class AbstractToggleProjectNatureHandler extends AbstractHandler implements IElementUpdater {

	protected final String natureID;

	public AbstractToggleProjectNatureHandler(String natureID) {
		this.natureID = natureID;
	}

	@Override
	public void updateElement(UIElement element, @SuppressWarnings("rawtypes") Map parameters) {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
				.getSelection();
		IProject project = null;
		if (selection instanceof IStructuredSelection) {
			if (((IStructuredSelection) selection).size() == 1) {
				Object selected = ((IStructuredSelection) selection).getFirstElement();
				if (selected instanceof IProject) {
					project = (IProject) selected;
				}
				else if (selected instanceof IProjectNature) {
					project = ((IProjectNature) selected).getProject();
				}
			}
		}
		if (project != null) {
			element.setChecked(isNatureAdded(project, natureID));
		}
		else {
			element.setChecked(false);
		}
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		IProject project = null;
		if (selection instanceof IStructuredSelection) {
			if (((IStructuredSelection) selection).size() == 1) {
				Object selected = ((IStructuredSelection) selection).getFirstElement();
				if (selected instanceof IProject) {
					project = (IProject) selected;
				}
				else if (selected instanceof IProjectNature) {
					project = ((IProjectNature) selected).getProject();
				}
			}
		}
		if (project != null) {
			setNatureAdded(project, natureID, !isNatureAdded(project, natureID));
		}
		else {
			// do nothing
		}
		return null;
	}

	protected boolean isNatureAdded(IProject project, String natureID) {
		try {
			IProjectDescription description = project.getDescription();
			List<String> natures = Lists.newArrayList(description.getNatureIds());
			return natures.contains(natureID);
		}
		catch (CoreException e) {
		}
		return false;
	}

	protected boolean setNatureAdded(IProject project, String natureID, boolean addNature) {
		try {
			IProjectDescription description = project.getDescription();
			List<String> natures = Lists.newArrayList(description.getNatureIds());

			if (addNature) {
				if (!natures.contains(natureID)) {
					natures.add(natureID);
				}
			}
			else {
				if (natures.contains(natureID)) {
					natures.remove(natureID);
				}
			}

			description.setNatureIds(natures.toArray(new String[natures.size()]));
			project.setDescription(description, null);
			return natures.contains(natureID);
		}
		catch (CoreException e) {
		}
		return false;
	}
}