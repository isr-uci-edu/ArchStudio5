package org.archstudio.eclipsedev.core.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archstudio.dblgen.common.DataBindingGenerationStatus;
import org.archstudio.dblgen.common.IDataBindingGenerator;
import org.archstudio.dblgen.common.PackageComputeException;
import org.archstudio.eclipsedev.common.EclipseDevConstants;
import org.archstudio.eclipsedev.common.EclipseDevPreferences;
import org.archstudio.eclipsedev.common.EclipseDevConstants.ProjectCleanBehaviorType;
import org.archstudio.eclipsedev.core.EclipseDevMyxComponent;
import org.archstudio.myx.fw.MyxRegistry;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Lists;

public class Xadl3SchemaBuilder extends IncrementalProjectBuilder {
	protected EclipseDevMyxComponent myxComponent = null;

	protected EclipseDevMyxComponent getMyxComponent() {
		if (myxComponent != null) {
			return myxComponent;
		}
		else {
			myxComponent = (EclipseDevMyxComponent) MyxRegistry.getSharedInstance().waitForBrick(
					EclipseDevMyxComponent.class);
			return myxComponent;
		}
	}

	private ProjectCleanBehaviorType getCleanBehaviorType() {
		IPreferenceStore preferenceStore = getMyxComponent().getPreferenceStore();
		return EclipseDevPreferences.getProjectCleanBehavior(preferenceStore);
	}

	private void addMarker(IProject project, String message, int severity) {
		try {
			IMarker marker = project.createMarker(EclipseDevConstants.MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
		}
		catch (CoreException e) {
		}
	}

	private void addMarker(IFile file, String message, int lineNumber, int severity) {
		try {
			IMarker marker = file.createMarker(EclipseDevConstants.MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		}
		catch (CoreException e) {
		}
	}

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(EclipseDevConstants.MARKER_TYPE, false, IResource.DEPTH_ZERO);
		}
		catch (CoreException ce) {
		}
	}

	private void deleteMarkers(IProject project) {
		try {
			project.deleteMarkers(EclipseDevConstants.MARKER_TYPE, false, IResource.DEPTH_ZERO);
		}
		catch (CoreException ce) {
		}
	}

	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
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
		//getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
		return null;
	}

	private class Xadl3ResourceDeltaVisitor implements IResourceDeltaVisitor {
		public boolean projectNeedsRebuild = false;

		protected final IProject project;
		protected final IFolder modelFolder;

		public Xadl3ResourceDeltaVisitor(IProject project) {
			this.project = project;
			modelFolder = getProject().getFolder("model");
		}

		public boolean visit(IResourceDelta delta) {
			if (modelFolder == null) {
				return false;
			}

			IResource res = delta.getResource();

			IContainer parent = res.getParent();
			while (parent != null) {
				if (parent.equals(modelFolder)) {
					projectNeedsRebuild = true;
					return false;
				}
				parent = parent.getParent();
			}
			return true; // visit the children
		}

	}

	private void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		// See if anything in the model directory changed that would require a rebuild.  If so,
		// do a full build.
		Xadl3ResourceDeltaVisitor visitor = new Xadl3ResourceDeltaVisitor(getProject());
		delta.accept(visitor);
		if (visitor.projectNeedsRebuild) {
			fullBuild(monitor);
		}
	}

	private List<IFile> getFileChildren(IContainer container) throws CoreException {
		IResource[] resources = container.members(IContainer.FILE);
		List<IFile> files = new ArrayList<IFile>();
		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				files.add((IFile) resource);
			}
		}
		return files;
	}

	private List<IFolder> getFolderChildren(IContainer container) throws CoreException {
		IResource[] resources = container.members(IContainer.FOLDER);
		List<IFolder> folders = new ArrayList<IFolder>();
		for (IResource resource : resources) {
			if (resource instanceof IFolder) {
				folders.add((IFolder) resource);
			}
		}
		return folders;
	}

	protected void clean(IProgressMonitor monitor) throws CoreException {
		super.clean(monitor);
		deleteMarkers(getProject());
		IProject project = getProject();

		ProjectCleanBehaviorType cleanBehaviorType = getCleanBehaviorType();

		switch (cleanBehaviorType) {
		case DELETE_FILES:
		case DELETE_FOLDERS:
			// Delete any .genmodel, .xsd2ecore, and .ecore files in the project root.
			List<IFile> filesAtProjectRoot = getFileChildren(project);
			for (IFile file : filesAtProjectRoot) {
				if (file.getFileExtension().equals("genmodel")) {
					file.delete(true, monitor);
				}
				else if (file.getFileExtension().equals("xsd2ecore")) {
					file.delete(true, monitor);
				}
				else if (file.getFileExtension().equals("ecore")) {
					file.delete(true, monitor);
				}
			}
			break;
		case DELETE_NOTHING:
			break;
		}

		// Get all folders under the 'src' folder
		IFolder sourceFolder = project.getFolder("src");
		if ((sourceFolder != null) && (sourceFolder.exists())) {
			switch (cleanBehaviorType) {
			case DELETE_FOLDERS:
				// Nuke the folders
				for (IFolder childFolder : getFolderChildren(sourceFolder)) {
					if (!(childFolder.isHidden() || childFolder.isDerived() || childFolder.isTeamPrivateMember())) {
						childFolder.delete(true, monitor);
					}
				}
				break;
			case DELETE_FILES:
				// Just nuke the files
				recursiveDeleteJavaFilesInFolder(sourceFolder, monitor);
				break;
			case DELETE_NOTHING:
				// Don't touch old files
				break;
			}
		}
	}

	private void recursiveDeleteJavaFilesInFolder(IFolder folder, IProgressMonitor monitor) throws CoreException {
		if (folder.isHidden() || folder.isTeamPrivateMember()) {
			return;
		}
		List<IFile> childFiles = getFileChildren(folder);
		for (IFile childFile : childFiles) {
			if (childFile.getFileExtension().equals("java")) {
				childFile.delete(true, monitor);
			}
		}
		List<IFolder> childFolders = getFolderChildren(folder);
		for (IFolder childFolder : childFolders) {
			recursiveDeleteJavaFilesInFolder(childFolder, monitor);
		}
	}

	private List<IFile> getSchemaFiles() throws CoreException {
		List<IFile> schemaFiles = new ArrayList<IFile>();

		IFolder modelFolder = getProject().getFolder("model");
		if (modelFolder.exists()) {
			IResource[] fileResources = modelFolder.members(IFolder.FILE);
			for (IResource fileResource : fileResources) {
				if (fileResource instanceof IFile) {
					IFile file = (IFile) fileResource;
					String extension = file.getFileExtension();
					if ((extension != null) && (extension.equals("xsd"))) {
						schemaFiles.add(file);
					}
				}
			}
		}
		return schemaFiles;
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		// Always clean up old stuff
		clean(monitor);

		// Delete any markers on the project itself.
		deleteMarkers(getProject());

		List<IFile> schemaFilesToProcess = getSchemaFiles();
		if (schemaFilesToProcess.size() > 0) {
			// Delete any existing markers on the schema files
			for (IFile schemaFile : schemaFilesToProcess) {
				deleteMarkers(schemaFile);
			}
		}

		//Update schemas if necessary
		Xadl3SchemaUpdater.getInstance().updateSchemasIfNecessary(getProject());
		List<String> externalSchemaURIs = Xadl3SchemaUpdater.getInstance().getNonCopiedSchemaURIs(getProject());

		//Now build with latest schemas.
		schemaFilesToProcess = getSchemaFiles();
		if (schemaFilesToProcess.size() > 0 || externalSchemaURIs.size() > 0) {
			// Get URIs for each schema file
			List<String> fileURIs = new ArrayList<String>();
			Map<String, IFile> uriToFileMap = new HashMap<String, IFile>();
			for (IFile schemaFile : schemaFilesToProcess) {
				fileURIs.add(schemaFile.getLocationURI().toString());
				uriToFileMap.put(schemaFile.getLocationURI().toString(), schemaFile);
			}

			// Generate new bindings
			IDataBindingGenerator dataBindingGenerator = getDataBindingGenerator();
			String projectName = getProject().getName();

			try {
				List<String> allURIs = Lists.newArrayList();
				allURIs.addAll(fileURIs);
				allURIs.addAll(externalSchemaURIs);
				List<DataBindingGenerationStatus> statusList = dataBindingGenerator.generateBindings(allURIs,
						projectName);

				for (DataBindingGenerationStatus status : statusList) {
					String schemaURI = status.getSchemaURIString();
					IFile schemaFile = null;
					if (schemaURI != null) {
						schemaFile = uriToFileMap.get(schemaURI);
					}
					//if ((schemaFile == null) && (schemaFilesToProcess.size() > 0)) {
					//	schemaFile = schemaFilesToProcess.get(0);
					//}

					if (schemaFile != null) {
						String message = status.getMessage();
						if (status.getThrowable() != null) {
							message += "; " + status.getThrowable().getMessage();
						}
						DataBindingGenerationStatus.Status statusType = status.getStatus();
						if (statusType.equals(DataBindingGenerationStatus.Status.WARNING)) {
							addMarker(schemaFile, message, 1, IMarker.SEVERITY_WARNING);
						}
						else if (statusType.equals(DataBindingGenerationStatus.Status.FAILURE)) {
							addMarker(schemaFile, message, 1, IMarker.SEVERITY_ERROR);
						}
					}
				}
			}
			catch (PackageComputeException pce) {
				if (schemaFilesToProcess.size() == 0) {
					addMarker(getProject(),
							"Package Compute Error while Generating Data Bindings; " + pce.getMessage(),
							IMarker.SEVERITY_ERROR);
				}
				else {
					addMarker(schemaFilesToProcess.get(0), "Package Compute Error while Generating Data Bindings; "
							+ pce.getMessage(), 1, IMarker.SEVERITY_ERROR);
				}
			}
			catch (IOException ioe) {
				if (schemaFilesToProcess.size() == 0) {
					addMarker(getProject(), "I/O Error while Generating Data Bindings; " + ioe.getMessage(),
							IMarker.SEVERITY_ERROR);
				}
				else {
					addMarker(schemaFilesToProcess.get(0),
							"I/O Error while Generating Data Bindings; " + ioe.getMessage(), 1, IMarker.SEVERITY_ERROR);
				}
			}
		}

		final IProject project = getProject();

		Job job = new Job("Refreshing Project") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					// Wait until all builds are done
					// Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);

					// Then refresh the project to make sure that everything is synced
					project.refreshLocal(IProject.DEPTH_INFINITE, monitor);
				}
				catch (CoreException ce) {
				}
				// catch (InterruptedException ie) {
				// }
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.schedule(); // start as soon as possible
	}

	private IDataBindingGenerator getDataBindingGenerator() {
		return getMyxComponent().getDataBindingGenerator();
	}

	class XMLErrorHandler extends DefaultHandler {
		private IFile file;

		public XMLErrorHandler(IFile file) {
			this.file = file;
		}

		private void addMarker(SAXParseException e, int severity) {
			Xadl3SchemaBuilder.this.addMarker(file, e.getMessage(), e.getLineNumber(), severity);
		}

		public void error(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		public void warning(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_WARNING);
		}
	}

}
