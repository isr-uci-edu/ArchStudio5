package org.archstudio.eclipsedev.core.action;

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
		for (IProject project : getProjects(selection)) {
			System.err.println(natureID + " " + isNatureAdded(project, natureID));
			action.setChecked(isNatureAdded(project, natureID));
		}
	}

	@Override
	public void run(IAction action) {
		for (IProject project : getProjects(selection)) {
			toggleNatureAdded(project, natureID);
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
					System.err.println(commandID + " " + natureID + " " + command.isDefined() + " "
							+ command.isEnabled());
					return new Status(IStatus.OK, commandID, "Init commands workaround performed succesfully");
				}
				catch (Throwable t) {
					return new Status(IStatus.ERROR, commandID, "Init commands workaround failed", t);
				}
			}
		};
		job.schedule();
	}

	protected static boolean isNatureAdded(IProject project, String natureID) {
		try {
			IProjectDescription description = project.getDescription();
			List<String> natures = Lists.newArrayList(description.getNatureIds());
			return natures.contains(natureID);
		}
		catch (CoreException e) {
		}
		return false;
	}

	protected static boolean toggleNatureAdded(IProject project, String natureID) {
		try {
			IProjectDescription description = project.getDescription();
			List<String> natures = Lists.newArrayList(description.getNatureIds());

			if (natures.contains(natureID)) {
				natures.remove(natureID);
			}
			else {
				natures.add(natureID);
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