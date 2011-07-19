package org.archstudio.eclipse;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class ArchStudioNature implements IProjectNature {

	public static final String ID = "org.archstudio.ArchStudioNature";

	public IProject theProject = null;

	public ArchStudioNature() {
	}

	public void configure() throws CoreException {
	}

	public void deconfigure() throws CoreException {
	}

	public IProject getProject() {
		return theProject;
	}

	public void setProject(IProject project) {
		theProject = project;
	}

}
