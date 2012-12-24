package org.archstudio.myxgen.builder;

import java.util.Map;

import org.archstudio.myxgen.jet.codegen.MyxCodeGenerator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

public class MyxStubBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "org.archstudio.myxgen.myxStubBuilder";

	private class MyxStubResourceDeltaVisitor implements IResourceDeltaVisitor {
		public boolean projectNeedsRebuild = false;

		protected final IFile pluginFile;

		public MyxStubResourceDeltaVisitor(IProject project) {
			pluginFile = getProject().getFile("plugin.xml");
		}

		public boolean visit(IResourceDelta delta) {
			if (pluginFile == null) {
				return false;
			}
			IResource res = delta.getResource();
			if (pluginFile.equals(res)) {
				projectNeedsRebuild = true;
				return false;
			}
			return true;
		}
	}

	public MyxStubBuilder() {
	}

	protected IProject[] build(int kind, @SuppressWarnings("rawtypes") Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		}
		else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			}
			else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	private void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		MyxStubResourceDeltaVisitor visitor = new MyxStubResourceDeltaVisitor(getProject());
		delta.accept(visitor);
		if (visitor.projectNeedsRebuild) {
			fullBuild(monitor);
		}
	}

	private void fullBuild(IProgressMonitor monitor) {
		IJavaProject javaProject = JavaCore.create(getProject());
		if (javaProject != null) {
			new MyxCodeGenerator(javaProject).generateCode();
			needRebuild();
		}
	}
}
