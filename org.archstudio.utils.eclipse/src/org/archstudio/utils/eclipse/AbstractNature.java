package org.archstudio.utils.eclipse;

import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class AbstractNature implements IProjectNature {

	protected final String natureID;
	protected final String builderID;

	public AbstractNature(String natureID, String builderID) {
		this.natureID = natureID;
		this.builderID = builderID;
	}

	protected IProject project;

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	private final Predicate<ICommand> isBuilderPredicate = new Predicate<ICommand>() {

		public boolean apply(ICommand input) {
			return input.getBuilderName().equals(builderID);
		}
	};

	public void configure() throws CoreException {
		IProjectDescription description = project.getDescription();
		List<ICommand> commands = Lists.newArrayList(description.getBuildSpec());

		for (ICommand command : commands) {
			if (isBuilderPredicate.apply(command)) {
				return;
			}
		}

		ICommand command = description.newCommand();
		command.setBuilderName(builderID);
		commands.add(command);

		description.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
		project.setDescription(description, null);
	}

	public void deconfigure() throws CoreException {
		IProjectDescription description = project.getDescription();
		List<ICommand> commands = Lists.newArrayList(description.getBuildSpec());

		for (ICommand command : commands) {
			if (isBuilderPredicate.apply(command)) {
				commands.remove(command);
				description.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
				project.setDescription(description, null);
				return;
			}
		}
	}
}
