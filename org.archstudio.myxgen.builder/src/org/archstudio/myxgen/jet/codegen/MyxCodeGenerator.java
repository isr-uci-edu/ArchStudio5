package org.archstudio.myxgen.jet.codegen;

import java.io.ByteArrayInputStream;

import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.eclipse.extension.MyxGenWorkspaceExtensions;
import org.archstudio.utils.eclipse.jdt.CodeGeneration;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;

public class MyxCodeGenerator {

	private final IJavaProject javaProject;

	public MyxCodeGenerator(IJavaProject javaProject) {
		this.javaProject = javaProject;
	}

	public void generateCode() {
		for (MyxGenBrick brick : MyxGenWorkspaceExtensions.getMyxGenBricks(javaProject.getProject())) {
			try {
				generateCode(brick);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void generateCode(MyxGenBrick brick) throws CoreException {
		IFile stubFile = javaProject.getProject()
				.getFile("src/" + brick.getStubClassName().replace('.', '/') + ".java");
		String stubSource = MyxCompStubBuilder.generate(brick);
		if (stubFile.exists())
			stubFile.setContents(new ByteArrayInputStream(stubSource.getBytes()), true, true, new NullProgressMonitor());
		else
			stubFile.create(new ByteArrayInputStream(stubSource.getBytes()), true, new NullProgressMonitor());
		CodeGeneration.formatCode(stubFile);

		IFile mainFile = javaProject.getProject().getFile("src/" + brick.getClassName().replace('.', '/') + ".java");
		if (!mainFile.exists()) {
			String mainSource = MyxCompBuilder.generate(brick);
			mainFile.create(new ByteArrayInputStream(mainSource.getBytes()), true, new NullProgressMonitor());
			CodeGeneration.formatCode(mainFile);
		}
	}
}
