package org.archstudio.dblgen.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archstudio.dblgen.DataBindingGenerationStatus;
import org.archstudio.dblgen.DataBindingGeneratorImpl;
import org.archstudio.dblgen.core.Activator;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

public class Xadl3SchemaBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "org.archstudio.dblgen.xadl3SchemaBuilder";

	/*
	 * This ID has to match one in the plugin.xml file for this to work, and for these to show up in the problems view,
	 * the marker type has to have the Problem marker type set as a 'super' in its attributes.
	 */
	public static final String MARKER_TYPE = "org.archstudio.dblgen.xadl3SchemaBuilderProblem";

	protected final DataBindingGeneratorImpl dbGenerator;

	public Xadl3SchemaBuilder() {
		this.dbGenerator = new DataBindingGeneratorImpl();
	}

	private void addMarker(IFile file, String message, int lineNumber, int severity) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
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
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		}
		catch (CoreException ce) {
		}
	}

	private void deleteMarkers(IProject project) {
		try {
			project.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		}
		catch (CoreException ce) {
		}
	}

	@Override
	protected IProject[] build(int kind, @SuppressWarnings("rawtypes") Map args, IProgressMonitor monitor)
			throws CoreException {

		try {
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

			getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
		}
		catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.getSingleton().getId(), e.getMessage(), e));
		}
		return null;
	}

	private class Xadl3ResourceDeltaVisitor implements IResourceDeltaVisitor {
		public boolean projectNeedsRebuild = false;

		protected final IFolder modelFolder;
		protected final IFile pluginFile;

		public Xadl3ResourceDeltaVisitor(IProject project) {
			modelFolder = getProject().getFolder("model");
			pluginFile = getProject().getFile("plugin.xml");
		}

		@Override
		public boolean visit(IResourceDelta delta) {
			if (modelFolder != null) {
				IResource res = delta.getResource();

				IContainer parent = res.getParent();
				while (parent != null) {
					if (parent.equals(modelFolder)) {
						projectNeedsRebuild = true;
						return false;
					}
					parent = parent.getParent();
				}
			}
			if (pluginFile != null) {
				if (pluginFile.equals(delta.getResource())) {
					projectNeedsRebuild = true;
					return false;
				}
			}
			return true; // visit the children
		}
	}

	private void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws IOException, CoreException {
		/*
		 * See if anything in the model directory changed that would require a rebuild. If so, do a full build.
		 */
		Xadl3ResourceDeltaVisitor visitor = new Xadl3ResourceDeltaVisitor(getProject());
		delta.accept(visitor);
		if (visitor.projectNeedsRebuild) {
			fullBuild(monitor);
		}
	}

	private List<IFile> getFileChildren(IContainer container) throws CoreException {
		IResource[] resources = container.members(IResource.FILE);
		List<IFile> files = new ArrayList<IFile>();
		for (IResource resource : resources) {
			if (resource instanceof IFile) {
				files.add((IFile) resource);
			}
		}
		return files;
	}

	private List<IFolder> getFolderChildren(IContainer container) throws CoreException {
		List<IFolder> folders = new ArrayList<IFolder>();
		if (container.exists()) {
			IResource[] resources = container.members(IResource.FOLDER);
			for (IResource resource : resources) {
				if (resource instanceof IFolder) {
					folders.add((IFolder) resource);
				}
			}
		}
		return folders;
	}

	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		super.clean(monitor);
		deleteMarkers(getProject());
		IProject project = getProject();

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

		IFolder sourceFolder = project.getFolder("src");
		for (IFolder childFolder : getFolderChildren(sourceFolder)) {
			if (!(childFolder.isHidden() || childFolder.isDerived() || childFolder.isTeamPrivateMember())) {
				childFolder.delete(true, monitor);
			}
		}
	}

	private List<IFile> getSchemaFiles() throws CoreException {
		List<IFile> schemaFiles = new ArrayList<IFile>();

		IFolder modelFolder = getProject().getFolder("model");
		if (modelFolder.exists()) {
			IResource[] fileResources = modelFolder.members(IResource.FILE);
			for (IResource fileResource : fileResources) {
				if (fileResource instanceof IFile) {
					IFile file = (IFile) fileResource;
					if (file.exists()) {
						String extension = file.getFileExtension();
						if (extension != null && extension.equals("xsd")) {
							schemaFiles.add(file);
						}
					}
				}
			}
		}
		return schemaFiles;
	}

	protected void fullBuild(final IProgressMonitor monitor) throws IOException, CoreException {
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

		// Update schemas if necessary
		Xadl3SchemaUpdater.getInstance().updateSchemasIfNecessary(getProject());
		List<Xadl3SchemaLocation> schemaLocations = Xadl3SchemaLocation.parse(getProject());
		addURIMappings(getProject());

		// Now build with latest schemas.
		schemaFilesToProcess = getSchemaFiles();
		if (schemaFilesToProcess.size() > 0 || schemaLocations.size() > 0) {
			// Get URIs for each schema file
			List<String> fileURIs = new ArrayList<String>();
			Map<String, IFile> uriToFileMap = new HashMap<String, IFile>();
			for (IFile schemaFile : schemaFilesToProcess) {
				fileURIs.add(schemaFile.getLocationURI().toString());
				uriToFileMap.put(schemaFile.getLocationURI().toString(), schemaFile);
			}

			// Generate new bindings
			String projectName = getProject().getName();

			dbGenerator.setMonitor(BasicMonitor.toMonitor(monitor));
			List<DataBindingGenerationStatus> statusList = dbGenerator.generateBindings(fileURIs, schemaLocations,
					projectName);

			for (DataBindingGenerationStatus status : statusList) {
				if (status.getThrowable() != null) {
					status.getThrowable().printStackTrace();
				}
				String schemaURI = status.getSchemaURIString();
				IFile schemaFile = null;
				if (schemaURI != null) {
					schemaFile = uriToFileMap.get(schemaURI);
				}

				if (schemaFile != null) {
					String message = status.getMessage();
					if (status.getThrowable() != null) {
						status.getThrowable().printStackTrace();
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
				else {
					System.err.println(status.getMessage());
				}
			}
		}

		final IProject project = getProject();
		project.getFolder("src").refreshLocal(IResource.DEPTH_INFINITE, monitor);
		needRebuild();

		//		Job job = new Job("Formatting Project") {
		//
		//			@Override
		//			protected IStatus run(IProgressMonitor monitor) {
		//				try {
		//					// Wait until all builds are done
		//					Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
		//
		//					// Then refresh the project to make sure that everything is synced
		//					CodeGeneration.formatCode(project);
		//					project.getFolder("src").refreshLocal(IResource.DEPTH_INFINITE, monitor);
		//				}
		//				catch (Exception e) {
		//				}
		//				return Status.OK_STATUS;
		//			}
		//		};
		//		job.setPriority(Job.SHORT);
		//		job.schedule(); // start as soon as possible
	}

	private void addURIMappings(IProject project) {
		IPluginModelBase workspacePluginModelBase = PluginRegistry.findModel(project);
		if (workspacePluginModelBase != null) {
			IExtensions extensions = workspacePluginModelBase.getExtensions();
			if (extensions != null) {
				for (IPluginExtension pluginExtension : extensions.getExtensions()) {
					if (pluginExtension.getPoint() != null
							&& pluginExtension.getPoint().equals("org.eclipse.emf.ecore.uri_mapping")) {
						IPluginObject[] pluginObjects = pluginExtension.getChildren();
						if (pluginObjects != null && pluginObjects.length > 0) {
							for (IPluginElement pluginElement : Iterables.filter(Arrays.asList(pluginObjects),
									IPluginElement.class)) {
								URI source = URI.createURI(Preconditions.checkNotNull(pluginElement.getAttribute(
										"source").getValue()));
								URI target = URI.createURI(Preconditions.checkNotNull(pluginElement.getAttribute(
										"target").getValue()));
								if (source != null && target != null) {
									URIConverter.URI_MAP.put(source, target);
								}
							}
						}
					}
				}
			}
		}
	}

	class XMLErrorHandler extends DefaultHandler {
		private final IFile file;

		public XMLErrorHandler(IFile file) {
			this.file = file;
		}

		private void addMarker(SAXParseException e, int severity) {
			Xadl3SchemaBuilder.this.addMarker(file, e.getMessage(), e.getLineNumber(), severity);
		}

		@Override
		public void error(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		@Override
		public void fatalError(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_ERROR);
		}

		@Override
		public void warning(SAXParseException exception) throws SAXException {
			addMarker(exception, IMarker.SEVERITY_WARNING);
		}
	}
}
