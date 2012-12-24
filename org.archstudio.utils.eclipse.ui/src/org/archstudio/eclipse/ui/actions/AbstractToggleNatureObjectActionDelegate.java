package org.archstudio.eclipse.ui.actions;

import java.util.List;

import org.eclipse.core.commands.Command;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.UIJob;

import com.google.common.collect.Lists;

public abstract class AbstractToggleNatureObjectActionDelegate extends AbstractObjectActionDelegate implements IStartup {

	protected final String natureID;
	protected final String commandID;

	public AbstractToggleNatureObjectActionDelegate(String natureID) {
		this(natureID, natureID + ".action");
	}

	public AbstractToggleNatureObjectActionDelegate(String natureID, String commandID) {
		this.natureID = natureID;
		this.commandID = commandID;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		action.setChecked(isNatureAdded(getProjects(selection), natureID));
	}

	@Override
	public void run(IAction action) {
		boolean allAdded = isNatureAdded(getProjects(selection), natureID);
		for (IProject project : getProjects(selection)) {
			setNatureAdded(project, natureID, !allAdded);
		}
	}

	@Override
	public void earlyStartup() {
		// Necessary to initialize toggle state
		// see: http://wiki.eclipse.org/Menu_Contributions/Radio_Button_Command#Initializing_the_Handler
		UIJob job = new UIJob("InitCommandsWorkaround") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				try {
					ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getService(ICommandService.class);
					Command command = commandService.getCommand(commandID);
					command.isEnabled(); // force initialization of command 
					return new Status(IStatus.OK, commandID, "Init commands workaround performed succesfully");
				}
				catch (Throwable t) {
					return new Status(IStatus.ERROR, commandID, "Init commands workaround failed", t);
				}
			}
		};
		job.schedule();
	}

	protected boolean isNatureAdded(Iterable<IProject> projects, String natureID) {
		boolean allAdded = true;
		for (IProject project : projects) {
			allAdded &= isNatureAdded(project, natureID);
		}
		return allAdded;
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