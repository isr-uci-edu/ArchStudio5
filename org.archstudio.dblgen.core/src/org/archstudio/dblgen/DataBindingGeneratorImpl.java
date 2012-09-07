package org.archstudio.dblgen;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.archstudio.dblgen.DataBindingGenerationStatus.Status;
import org.archstudio.dblgen.builder.Xadl3SchemaLocation;
import org.archstudio.dblgen.core.Activator;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.utils.osgi.OSGiUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ModelConverter.EPackageConvertInfo;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IExtensionsModelFactory;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.ISharedExtensionsModel;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.eclipse.pde.core.project.IPackageExportDescription;
import org.eclipse.pde.core.project.IRequiredBundleDescription;
import org.eclipse.pde.internal.core.bundle.WorkspaceBundlePluginModel;
import org.eclipse.xsd.ecore.importer.XSDImporter;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;

@SuppressWarnings("restriction")
public class DataBindingGeneratorImpl implements IDataBindingGenerator {
	public static final String XADL3_SCHEMA_NATURE_ID = "org.archstudio.dblgen.xadl3SchemaNature";

	static {
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(GenModelPackage.eNS_URI,
				GenModelGeneratorAdapterFactory.DESCRIPTOR);
		URIConverter.URI_MAP.put(URI.createURI(EcorePackage.eNS_URI),
				URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xsd"));
	}

	private Monitor emfMonitor;

	public DataBindingGeneratorImpl() {
	}

	public synchronized void setMonitor(Monitor emfMonitor) {
		if (emfMonitor == null) {
			this.emfMonitor = DataBindingGeneratorImpl.createBasicPrintingMonitor();
		}
		else {
			this.emfMonitor = emfMonitor;
		}
	}

	public static Monitor createBasicPrintingMonitor() {
		return new BasicMonitor.Printing(System.out);
	}

	/**
	 * Parses an XML file.
	 * 
	 * @param documentInputStream
	 *            Input stream for XML content
	 * @return Parsed DOM Document object
	 * @throws ParserConfigurationException
	 *             If the parser was misconfigured somehow.
	 * @throws SAXException
	 *             on a parsing error.
	 * @throws IOException
	 *             on an I/O error.
	 */
	public static Document parseDocument(InputStream documentInputStream) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilder docBuilder;
		Document doc = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setIgnoringElementContentWhitespace(true);

		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(documentInputStream);
		return doc;
	}

	/**
	 * Gets all the projects in the workspace that have the xADL 3 Schema Nature
	 * added to them. Projects must be open and accessible to be returned.
	 * 
	 * @return A list of <tt>IProject</tt>s that have the appropriate nature.
	 * @throws CoreException
	 */
	public List<IProject> getXadlSchemaProjects() throws CoreException {
		List<IProject> projectList = new ArrayList<IProject>();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject[] projects = workspace.getRoot().getProjects();
		for (IProject project : projects) {
			if (project.isAccessible()) {
				if (project.hasNature(DataBindingGeneratorImpl.XADL3_SCHEMA_NATURE_ID)) {
					projectList.add(project);
				}
			}
		}
		return projectList;
	}

	/**
	 * Gets a list of .xsd files that exist in the project root or in the
	 * <tt>model/</tt> directory of the given project.
	 * 
	 * @param project
	 *            Project to test.
	 * @return List of <tt>IFile</tt>s representing XSD files.
	 */
	private List<IFile> getSchemaFilesInProject(IProject project) {
		final List<IFile> schemaFiles = new ArrayList<IFile>();

		try {
			project.accept(new IResourceVisitor() {
				@Override
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						IFile file = (IFile) resource;
						String extension = file.getFileExtension();
						if (extension != null && extension.equals("xsd")) {
							// Get the file path, relative to the project root
							IPath projectRelativePath = file.getProjectRelativePath();

							// Get the parent path
							IPath projectRelativePathParent = projectRelativePath.removeLastSegments(1);

							// If the parent path is empty, the file is effectively at the project root.
							if (projectRelativePathParent.isEmpty()) {
								schemaFiles.add(file);
							}
							else if (projectRelativePathParent.lastSegment().equals("model")) {
								// It's in a directory called "model." Is this in the root?
								IPath projectRelativePathGrandparent = projectRelativePathParent.removeLastSegments(1);
								if (projectRelativePathGrandparent.isEmpty()) {
									// It's in /model/
									schemaFiles.add(file);
								}
							}
						}
					}
					return true;
				}
			});
		}
		catch (CoreException ce) {
		}

		return schemaFiles;
	}

	/**
	 * Gets a list of <tt>.genmodel</tt> files that exist at the project root of
	 * the given project.
	 * 
	 * @param project
	 *            Project to test.
	 * @return List of <tt>IFile</tt>s representing <tt>.genmodel</tt> files.
	 */
	private List<IFile> getGenModelFilesInProject(IProject project) {
		final List<IFile> genModelFiles = new ArrayList<IFile>();

		try {
			project.accept(new IResourceVisitor() {
				@Override
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						IFile file = (IFile) resource;
						String extension = file.getFileExtension();
						if (extension != null && extension.equals("genmodel")) {
							// Get the file path, relative to the project root
							IPath projectRelativePath = file.getProjectRelativePath();

							// Get the parent path
							IPath projectRelativePathParent = projectRelativePath.removeLastSegments(1);

							// If the parent path is empty, the file is effectively at the project root.
							if (projectRelativePathParent.isEmpty()) {
								genModelFiles.add(file);
							}
						}
					}
					return true;
				}
			});
		}
		catch (CoreException ce) {
		}

		return genModelFiles;
	}

	/**
	 * Gets the namespace URI (NSURI) for a given .xsd file. Parses the file,
	 * extracts the <tt>xmlns</tt> attribute from the root element, and returns
	 * its contents.
	 * 
	 * @param schemaFile
	 *            <tt>IFile</tt> representing schema file to parse
	 * @return namespace URI; possibly <tt>null</tt> if one is missing.
	 * @throws ParserConfigurationException
	 *             if the parser was misconfigured somehow.
	 * @throws SAXException
	 *             if there was an error parsing the <tt>.xsd</tt> file.
	 * @throws IOException
	 *             if there was an I/O error
	 * @throws CoreException
	 *             if there was an internal Eclipse error.
	 */
	public String getNSURIForSchemaFile(IFile schemaFile) throws ParserConfigurationException, SAXException,
			IOException, CoreException {
		InputStream is = null;
		try {
			is = schemaFile.getContents();
			Document doc = DataBindingGeneratorImpl.parseDocument(is);
			return DataBindingGeneratorImpl.getNSURIForDocument(doc);
		}
		finally {
			if (is != null) {
				is.close();
			}
		}
	}

	private static String getNSURIForDocument(Document doc) {
		Element docElt = doc.getDocumentElement();
		String xmlns = docElt.getAttribute("targetNamespace");
		if (xmlns == null || xmlns.trim().length() == 0) {
			xmlns = docElt.getAttribute("xmlns");
		}
		return xmlns;
	}

	/**
	 * Returns a mapping of schema files to NSURIs for those schema files
	 * 
	 * @param schemaFileList
	 *            List of <tt>IFile</tt> representing <tt>.xsd</tt> files.
	 * @return map mapping <tt>IFile</tt>s in <tt>schemaFileList</tt> to
	 *         Strings, representing their namespace URIs. Parsing and other
	 *         errors are ignored and those URIs will not be present in the
	 *         returned map.
	 */
	public Map<IFile, String> getNSURIsForSchemaFiles(List<IFile> schemaFileList) throws ParserConfigurationException,
			SAXException, IOException, CoreException {
		Map<IFile, String> schemaToNSURIMap = new HashMap<IFile, String>();
		for (IFile schemaFile : schemaFileList) {
			String nsuri = getNSURIForSchemaFile(schemaFile);
			schemaToNSURIMap.put(schemaFile, nsuri);
		}
		return schemaToNSURIMap;
	}

	/**
	 * Returns a namespace URI for a schema URI (in the Eclipse universe).
	 * 
	 * @param schemaURIString
	 *            an Eclipse URI pointing to an <tt>.xsd</tt> resource.
	 * @param resourceSet
	 *            Resource set to use for resolving URIs in
	 *            <tt>schemaURIStrings</tt>
	 * @return namespace URI declared in the schema
	 */
	private static String getNSURIForSchema(String schemaURIString, ResourceSet resourceSet)
			throws ParserConfigurationException, SAXException, IOException, CoreException {
		InputStream is = null;
		try {
			URI uri = URI.createURI(schemaURIString);
			is = resourceSet.getURIConverter().createInputStream(uri);
			Document doc = DataBindingGeneratorImpl.parseDocument(is);
			return DataBindingGeneratorImpl.getNSURIForDocument(doc);
		}
		finally {
			is = SystemUtils.closeQuietly(is);
		}
	}

	/**
	 * Returns the namespace to location mappings contained within a schema URI
	 * (in the Eclipse universe).
	 * 
	 * @param schemaURIString
	 *            an Eclipse URI pointing to an <tt>.xsd</tt> resource.
	 * @param resourceSet
	 *            Resource set to use for resolving URIs in
	 *            <tt>schemaURIStrings</tt>
	 * @return namespace URI to location mappings declared in the schema
	 */
	public Map<String, String> getImportsForSchema(String schemaURIString, ResourceSet resourceSet)
			throws ParserConfigurationException, SAXException, IOException, CoreException {
		InputStream is = null;
		try {
			URI uri = URI.createURI(schemaURIString);
			is = resourceSet.getURIConverter().createInputStream(uri);
			Document doc = DataBindingGeneratorImpl.parseDocument(is);
			return DataBindingGeneratorImpl.getImportsForDocument(doc);
		}
		finally {
			is = SystemUtils.closeQuietly(is);
		}
	}

	private static Map<String, String> getImportsForDocument(Document doc) {
		Map<String, String> imports = Maps.newHashMap();
		Element docElt = doc.getDocumentElement();
		NodeList children = docElt.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node n = children.item(i);
			if (n.getNodeName().endsWith(":import") && n instanceof Element) {
				Element child = (Element) n;
				String namespace = child.getAttribute("namespace");
				String schemaLocation = child.getAttribute("schemaLocation");
				if (namespace != null && schemaLocation != null && schemaLocation.length() > 0) {
					imports.put(namespace, schemaLocation);
				}
			}
		}
		return imports;
	}

	/**
	 * Parses a <tt>.genmodel</tt> file and returns the EMF <tt>GenModel</tt>
	 * class representing that file.
	 * 
	 * @param resourceSet
	 *            Resource set to use for URI resolution (EMF location requires
	 *            the IFile to be turned into a URI internally).
	 * @param genModelFile
	 *            <tt>IFile</tt> representing <tt>.genmodel</tt> file.
	 * @return <tt>GenModel</tt> object from parsed file.
	 */
	public GenModel parseGenModelFile(ResourceSet resourceSet, IFile genModelFile) {
		// Gets the path in the form /projectname/path/to/file, which is coincidentally the input
		// to (emf)URI.createPlatformResourceURI()
		String filePathString = genModelFile.getFullPath().toString();
		URI fileURI = URI.createPlatformResourceURI(filePathString, false);

		Resource emfResource = resourceSet.getResource(fileURI, true);
		GenModel genModel = (GenModel) emfResource.getContents().get(0);
		return genModel;
	}

	/**
	 * Get the GenModel object for the Ecore package.
	 * 
	 * @param resourceSet
	 *            resource set to use to get the Ecore package.
	 * @return Ecore <tt>GenModel</tt> object.
	 */
	public GenModel getEcoreGenModel(ResourceSet resourceSet) {
		URI ecoreGenModelURI = EcorePlugin.getEPackageNsURIToGenModelLocationMap().get(EcorePackage.eNS_URI);
		Resource emfEcoreResource = resourceSet.getResource(ecoreGenModelURI, true);
		GenModel ecoreGenModel = (GenModel) emfEcoreResource.getContents().get(0);
		return ecoreGenModel;
	}

	/**
	 * Get the GenPackage object for the Ecore package.
	 * 
	 * @param resourceSet
	 *            resource set to use to get the Ecore package.
	 * @return Ecore <tt>GenPackage</tt> object.
	 */
	public GenPackage getEcoreGenPackage(ResourceSet resourceSet) {
		for (GenPackage genPackage : getEcoreGenModel(resourceSet).getGenPackages()) {
			String genPackageNSURI = genPackage.getNSURI();
			if (genPackageNSURI != null && genPackageNSURI.equals(EcorePackage.eNS_URI)) {
				return genPackage;
			}
		}
		return null;
	}

	/**
	 * Returns a mapping of <tt>.genmodel</tt> files to parsed <tt>GenModel</tt>
	 * objects representing those files.
	 * 
	 * @param resourceSet
	 *            <tt>ResourceSet</tt> used to rString esolve URIs
	 * @param genModelFileList
	 *            List of <tt>IFile</tt> objects representing GenModel files.
	 * @return Map from <tt>IFile</tt> objects passed in to <tt>GenModel</tt>
	 *         parsed objects.
	 */
	public Map<IFile, GenModel> parseGenModelFiles(ResourceSet resourceSet, List<IFile> genModelFileList) {
		Map<IFile, GenModel> fileToGenModelMap = new HashMap<IFile, GenModel>();
		for (IFile genModelFile : genModelFileList) {
			try {
				GenModel genModel = parseGenModelFile(resourceSet, genModelFile);
				fileToGenModelMap.put(genModelFile, genModel);
			}
			catch (Exception e) {
				fileToGenModelMap.put(genModelFile, null);
			}
		}
		return fileToGenModelMap;
	}

	/**
	 * Clearinghouse method to get information about all the xADL schema
	 * projects in the workspace, what schemas they contain, and what
	 * <tt>.genmodel</tt> files they have.
	 * 
	 * @param importer
	 *            a ModelImporter object (used for URI resolution)
	 * @return A list of <tt>SchemaRecord</tt> objects containing information
	 *         about other schema projects in the workspace.
	 */
	private List<SchemaRecord> getSchemaRecords(ModelImporter importer) throws ParserConfigurationException,
			SAXException, IOException, CoreException {
		List<SchemaRecord> schemaRecordList = new ArrayList<SchemaRecord>();

		ResourceSet resourceSet = importer.createResourceSet();

		try {
			// Get all the xADL schema projects in the workspace.
			List<IProject> xadlSchemaProjects = getXadlSchemaProjects();

			// Store all the schema imports
			Map<String, String> imports = Maps.newHashMap();

			// Iterate through each project
			for (IProject xadlSchemaProject : xadlSchemaProjects) {

				// List of .xsd files in the project that we want to parse
				List<IFile> schemaFiles = getSchemaFilesInProject(xadlSchemaProject);
				//System.err.println("Schema files: " + schemaFiles);

				// Schema URIs for each XSD file.  
				Map<IFile, String> schemaToNSURIMap = getNSURIsForSchemaFiles(schemaFiles);
				//System.err.println("Schema URIs: " + schemaToNSURIMap);

				// Imports for each XSD file.
				for (IFile file : schemaFiles) {
					imports.putAll(getImportsForSchema(file.getLocationURI().toString(), resourceSet));
				}

				// List of .genmodel files in the project that we want to parse
				List<IFile> genModelFiles = getGenModelFilesInProject(xadlSchemaProject);
				//System.err.println("GenModel files: " + genModelFiles);

				// Parsed GenModel files for each GenModel file
				Map<IFile, GenModel> genModelFileToGenModelMap = parseGenModelFiles(resourceSet, genModelFiles);

				// For each schema file, develop as complete a SchemaRecord as possible
				for (IFile schemaFile : schemaFiles) {
					String recordNSURI = null;
					IFile recordSchemaFile = schemaFile;
					IFile recordGenModelFile = null;
					GenModel recordGenModel = null;
					GenPackage recordGenPackage = null;

					recordNSURI = schemaToNSURIMap.get(schemaFile);
					if (recordNSURI != null) {
						boolean found = false;
						for (Map.Entry<IFile, GenModel> entry : genModelFileToGenModelMap.entrySet()) {
							for (GenPackage genPackage : entry.getValue().getGenPackages()) {
								String genPackageNSURI = genPackage.getNSURI();
								if (genPackageNSURI != null && genPackageNSURI.equals(recordNSURI)) {
									recordGenModelFile = entry.getKey();
									recordGenModel = entry.getValue();
									recordGenPackage = genPackage;
									found = true;
									break;
								}
							}
							if (found) {
								break;
							}
						}
					}

					SchemaRecord newSchemaRecord = new SchemaRecord(recordNSURI, xadlSchemaProject, recordGenModelFile,
							recordSchemaFile, recordGenModel, recordGenPackage, imports);
					schemaRecordList.add(newSchemaRecord);
				}
			}
		}
		catch (CoreException ce) {
		}

		return schemaRecordList;
	}

	/**
	 * Internal data structure for capturing data about a Schema in a xADL
	 * Schema project. Includes namespace URI of the schema, the project
	 * containing the schema, the <tt>IFile</tt>s representing the schema itself
	 * and its GenModel, and the parsed GenModel representation and individual
	 * GenPackage corresponding to the schema.
	 */
	@SuppressWarnings("unused")
	private static class SchemaRecord {
		/** The namespace URI of the schema */
		private final String nsuri;

		/** The project containing the schema */
		private final IProject project;

		/**
		 * The genmodel file containing the GenPackage that refers to the schema
		 */
		private final IFile genModelFile;

		/** The schema file itself within the project */
		private final IFile schemaFile;

		/**
		 * The parsed GenModel object containing the GenPackage that refers to
		 * the schema
		 */
		private final GenModel genModel;

		/** The parsed GenPackage object referring to the schema. */
		private final GenPackage genPackage;

		/**
		 * The imports within the XSD file
		 */
		private final Map<String, String> imports;

		public SchemaRecord(String nsuri, IProject project, IFile genModelFile, IFile schemaFile, GenModel genModel,
				GenPackage genPackage, Map<String, String> imports) {
			super();
			this.nsuri = nsuri;
			this.project = project;
			this.genModelFile = genModelFile;
			this.schemaFile = schemaFile;
			this.genModel = genModel;
			this.genPackage = genPackage;
			this.imports = imports;
		}

		public String getNsuri() {
			return nsuri;
		}

		public IProject getProject() {
			return project;
		}

		public IFile getGenModelFile() {
			return genModelFile;
		}

		public IFile getSchemaFile() {
			return schemaFile;
		}

		public GenModel getGenModel() {
			return genModel;
		}

		public GenPackage getGenPackage() {
			return genPackage;
		}

		public Map<String, String> getImports() {
			return imports;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer("SchemaRecord{");
			sb.append("nsuri = ").append(nsuri).append("; ");
			sb.append("project = ").append(project).append("; ");
			sb.append("schemaFile = ").append(schemaFile).append("; ");
			sb.append("genModelFile = ").append(genModelFile).append("; ");
			sb.append("genModel = ").append(genModel).append("; ");
			sb.append("genPackage = ").append(genPackage);
			sb.append("}");
			return sb.toString();
		}
	}

	@Override
	public synchronized List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings,
			String projectName) {
		return generateBindings(schemaURIStrings, Collections.<Xadl3SchemaLocation> emptyList(), projectName);
	}

	//projectName = e.g., "org.archstudio.xadl3bindings"
	public synchronized List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings,
			List<Xadl3SchemaLocation> schemaLocations, final String projectName) {
		List<DataBindingGenerationStatus> statusList = new ArrayList<DataBindingGenerationStatus>();

		// refresh the resources in case they've been modified
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		IProject project = workspaceRoot.getProject(projectName);
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		}
		catch (CoreException e) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Cannot refresh project content", e));
			return statusList;
		}

		String shortProjectName = projectName.substring(projectName.lastIndexOf(".") + 1);
		final String finalProjectName = projectName;

		XSDImporter importer = new XSDImporter() {
			/*
			 * Note: XSDImporter seems to not support non-local files (e.g.,
			 * projects imported from an external folder, linked files, etc.).
			 * This is a problem because, in such cases, the generated resources
			 * are saved in the wrong location. Later, when searching for the
			 * resources, they cannot be found and the process fails. To resolve
			 * this, we extend the base class and move the resources to the
			 * correct location before saving them.
			 */
			@Override
			protected List<Resource> computeResourcesToBeSaved() {
				List<Resource> resources = super.computeResourcesToBeSaved();
				// move these resources to the correct location
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(finalProjectName);
				for (Resource resource : resources) {
					URI uri = resource.getURI();
					IFile newFile = project.getFile(uri.lastSegment());
					resource.setURI(URI.createURI(newFile.getLocationURI().toString()));
				}
				return resources;
			}
		};
		importer.setUsePlatformURI(false);
		importer.setCreateEcoreMap(true);

		// we still have to assign some value here, even though it doesn't work
		IPath genModelContainerPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/" + projectName);
		importer.setGenModelContainerPath(genModelContainerPath);
		importer.setGenModelFileName(shortProjectName + ".genmodel");

		String subfolder = "src";
		importer.setModelPluginID(projectName);
		importer.setModelPluginDirectory("/" + projectName + "/" + subfolder);

		ResourceSet resourceSet = importer.createResourceSet();

		// Determine what schemas are available in the workspace, and parse their genmodel files 
		List<SchemaRecord> schemaRecords = null;
		try {
			schemaRecords = getSchemaRecords(importer);
		}
		catch (ParserConfigurationException pce) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
					"Parser configuration error parsing schemas", pce));
			return statusList;
		}
		catch (SAXException se) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Parsing exception parsing schemas",
					se));
			return statusList;
		}
		catch (IOException ioe) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "I/O exception parsing schemas", ioe));
			return statusList;
		}
		catch (CoreException ce) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Core exception parsing schemas", ce));
			return statusList;
		}

		// Gather the list of previously processed schema
		Map<URI, URI> processedSchema = Maps.newHashMap();
		for (IConfigurationElement c : RegistryFactory.getRegistry().getConfigurationElementsFor(
				"org.archstudio.dblgen", "processedSchema")) {
			URI nsURI = URI.createURI(c.getAttribute("nsURI"));
			String localFile = c.getAttribute("file");
			Bundle bundle = Platform.getBundle(c.getContributor().getName());
			URI xsdURI = URI.createURI(bundle.getResource(localFile).toString());
			processedSchema.put(nsURI, xsdURI);
		}

		// keep a list of the schema that we should recursively scan for offline use
		Set<URI> schemaToScanForOfflineUse = Sets.newHashSet();

		// Map imports from schema, for offline use
		for (SchemaRecord schemaRecord : schemaRecords) {
			for (Entry<String, String> anImport : schemaRecord.getImports().entrySet()) {
				URI importedNsURI = URI.createURI(anImport.getKey());
				if (processedSchema.containsKey(importedNsURI)) {
					// we have to use the global map as the importer doesn't expose a local one
					URIConverter.URI_MAP.put(URI.createURI(anImport.getValue()), processedSchema.get(importedNsURI));
					schemaToScanForOfflineUse.add(URI.createURI(anImport.getValue()));
				}
			}
		}

		// Map the schemaRecords to the local files to support offline use as well as locally modified schema
		// Note: this should be done after previously processed schema so that workspace schema take precedence
		Multimap<String, SchemaRecord> nsuriToSchemaRecord = Multimaps.index(schemaRecords,
				new Function<SchemaRecord, String>() {
					@Override
					public String apply(SchemaRecord input) {
						return input.getNsuri();
					}
				});
		for (SchemaRecord schemaRecord : schemaRecords) {
			for (Entry<String, String> e : schemaRecord.getImports().entrySet()) {
				URI schemaLocation = URI.createURI(e.getValue());
				SchemaRecord sourceRecord = SystemUtils.firstOrNull(nsuriToSchemaRecord.get(e.getKey()));
				if (sourceRecord != null && sourceRecord.getSchemaFile() != null) {
					java.net.URI fileURI = sourceRecord.getSchemaFile().getLocationURI();
					if (fileURI != null) {
						// we have to use the global map as the importer doesn't expose a local one
						URIConverter.URI_MAP.put(schemaLocation, URI.createURI(fileURI.toASCIIString()));
						schemaToScanForOfflineUse.add(URI.createURI(fileURI.toASCIIString()));
					}
				}
			}
		}

		// recursively scan offline schema and find other imported offline schema
		Set<URI> schemaScannedForOfflineUse = Sets.newHashSet();
		try {
			while (!schemaScannedForOfflineUse.equals(schemaToScanForOfflineUse)) {
				URI schemaToScan = Sets.difference(schemaToScanForOfflineUse, schemaScannedForOfflineUse).iterator()
						.next();
				schemaScannedForOfflineUse.add(schemaToScan);
				for (Entry<String, String> e : getImportsForSchema(schemaToScan.toString(), resourceSet).entrySet()) {
					if(processedSchema.containsKey(URI.createURI(e.getKey()))){
						URIConverter.URI_MAP.put(URI.createURI(e.getValue()), processedSchema.get(URI.createURI(e.getKey())));
						schemaToScanForOfflineUse.add(URI.createURI(e.getValue()));
					}
				}
			}
		}
		catch (Exception e) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Exception parsing schemas", e));
			return statusList;
		}

		// primaryNSURIs is a list of NSURIs from schemas in the project
		Map<String, String> primaryNSURIs = Maps.newHashMap();
		try {
			for (String schemaURIString : schemaURIStrings) {
				primaryNSURIs.put(schemaURIString,
						DataBindingGeneratorImpl.getNSURIForSchema(schemaURIString, resourceSet));
			}
			for (Xadl3SchemaLocation schemaLocation : schemaLocations) {
				String schemaURIString = schemaLocation.getUrl().toString();
				try {
					primaryNSURIs.put(schemaURIString,
							DataBindingGeneratorImpl.getNSURIForSchema(schemaURIString, resourceSet));
				}
				catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		catch (ParserConfigurationException pce) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
					"Parser configuration error parsing schemas", pce));
			return statusList;
		}
		catch (SAXException se) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Parsing exception parsing schemas",
					se));
			return statusList;
		}
		catch (IOException ioe) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "I/O exception parsing schemas", ioe));
			return statusList;
		}
		catch (CoreException ce) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Core exception parsing schemas", ce));
			return statusList;
		}

		StringBuffer sb = new StringBuffer();
		for (String modelURIString : schemaURIStrings) {
			sb.append(modelURIString).append(" ");
		}
		for (Xadl3SchemaLocation schemaLocation : schemaLocations) {
			if (!schemaLocation.isCopyLocally()) {
				sb.append(schemaLocation.getUrl().toString()).append(" ");
			}
		}
		String combinedModelURIString = sb.toString();

		importer.setModelLocation(combinedModelURIString);

		boolean computePackagesSucceeded = false;
		try {
			importer.computeEPackages(emfMonitor);
			computePackagesSucceeded = true;

			// fix names of external packages
			Multimap<String, EPackage> m = Multimaps.index(importer.getEPackages(), new Function<EPackage, String>() {
				@Override
				public String apply(EPackage input) {
					return input.getNsURI();
				}
			});
			for (Xadl3SchemaLocation schemaLocation : schemaLocations) {
				EPackage e = m.get(primaryNSURIs.get(schemaLocation.getUrl().toString())).iterator().next();
				e.setName(schemaLocation.getName());
			}
		}
		catch (Exception e) {
			// Yes, in a remarkable display of programming good taste, computeEPackages
			// does declare that it throws "Exception"
			e.printStackTrace();
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
					"Error computing packages for schemas " + combinedModelURIString, e));
		}

		if (computePackagesSucceeded) {
			// referencedEPackageList is a list of EPackages that 
			// are referenced by the main schemas but for which we're not
			// going to generate bindings.  We assume these are living in other
			// schema projects.
			List<EPackage> referencedEPackageList = new ArrayList<EPackage>();

			for (EPackage ePackage : importer.getEPackages()) {
				EPackageConvertInfo ePackageConvertInfo = importer.getEPackageConvertInfo(ePackage);
				if (ePackage.getNsURI().equals(EcorePackage.eNS_URI)) {
					// Don't convert the Ecore dependency; this seems to get included
					// automagically
					ePackageConvertInfo.setConvert(false);
					referencedEPackageList.add(ePackage);
				}
				else {
					// Only convert the packages (e.g., schemas) that are in this project.
					if (primaryNSURIs.containsValue(ePackage.getNsURI())) {
						ePackageConvertInfo.setConvert(true);
					}
					else {
						ePackageConvertInfo.setConvert(true);
						referencedEPackageList.add(ePackage);
					}
				}
			}

			GenModel genModel = importer.getGenModel();

			// This causes the code to be generated with generics and such
			genModel.setComplianceLevel(GenJDKLevel.JDK60_LITERAL);

			// Inscrutable EMF Magic
			// Note that this has to be done before the below loop through
			// referenced packages or it will throw a NullPointerException when
			// adding packages to the ResourceSet
			importer.adjustEPackages(emfMonitor);
			importer.prepareGenModelAndEPackages(emfMonitor);

			boolean modelSaveSucceeded = false;
			try {
				importer.saveGenModelAndEPackages(emfMonitor);
				modelSaveSucceeded = true;
			}
			catch (Exception e) {
				// Another tasteful function that "throws Exception"
				e.printStackTrace();
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
						"Error saving generated content for " + combinedModelURIString, e));
			}

			if (modelSaveSucceeded) {
				try {
					genModel.setCanGenerate(true);

					Generator codeGenerator = new Generator();
					codeGenerator.setInput(genModel);

					// find the gen package for the targeted schema
					for (GenPackage genPackage : genModel.getGenPackages()) {
						// only generate code for the schema in this project
						if (primaryNSURIs.containsValue(genPackage.getNSURI())) {
							codeGenerator.generate(genPackage, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, emfMonitor);
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
							"Error generating content for " + combinedModelURIString, e));
				}
				try {
					BundleContext context = Activator.getSingleton().getContext();
					IBundleProjectService service = OSGiUtils.getServiceReference(context, IBundleProjectService.class);
					IBundleProjectDescription description = service.getDescription(project);

					// Update plugin's dependencies to include those needed
					List<IRequiredBundleDescription> requiredBundles = notNull(description.getRequiredBundles());
					Multimap<String, IRequiredBundleDescription> requiredBundlesMap = Multimaps.index(requiredBundles,
							new Function<IRequiredBundleDescription, String>() {
								@Override
								public String apply(IRequiredBundleDescription input) {
									return input.getName();
								}
							});
					if (!requiredBundlesMap.containsKey("org.eclipse.emf.ecore")) {
						requiredBundles.add(service.newRequiredBundle("org.eclipse.emf.ecore", null, false, false));
					}
					if (!requiredBundlesMap.containsKey("org.eclipse.emf.ecore.xmi")) {
						requiredBundles.add(service.newRequiredBundle("org.eclipse.emf.ecore.xmi", null, false, false));
					}
					if (!requiredBundlesMap.containsKey("org.archstudio.dblgen")) {
						requiredBundles.add(service.newRequiredBundle("org.archstudio.dblgen", null, false, false));
					}
					description.setRequiredBundles(requiredBundles
							.toArray(new IRequiredBundleDescription[requiredBundles.size()]));

					// updated plugin's exported packages to include the java packages created
					List<IPackageExportDescription> packageExports = notNull(description.getPackageExports());
					packageExports.clear(); // actually, only export created packages
					Multimap<String, IPackageExportDescription> packageExportsMap = Multimaps.index(packageExports,
							new Function<IPackageExportDescription, String>() {
								@Override
								public String apply(IPackageExportDescription input) {
									return input.getName();
								};
							});
					Set<IFolder> packageFolders = Sets.newHashSet();
					IFolder rootFolder = project.getFolder(subfolder);
					findFoldersWithFiles(rootFolder, packageFolders);
					for (IFolder folder : packageFolders) {
						String packageName = folder.toString().substring(rootFolder.toString().length() + 1)
								.replace('/', '.');
						if (!packageExportsMap.containsKey(packageName)) {
							packageExports.add(service.newPackageExport(packageName, null, true, null));
						}
					}
					description.setPackageExports(packageExports.toArray(new IPackageExportDescription[packageExports
							.size()]));

					// make sure that extensions are allowed
					description.setExtensionRegistry(true);

					// store the changes
					description.apply(null);
					project.refreshLocal(IResource.DEPTH_INFINITE, null);

					// add the package and parser extensions to the plugin
					IPluginModelBase plugin = PluginRegistry.findModel(project);
					ISharedExtensionsModel extensions = plugin;
					IExtensionsModelFactory factory;
					{
						// get an editable IPluginModelBase
						IFile manifestFile = project.getFile("META-INF/MANIFEST.MF");
						manifestFile.refreshLocal(IResource.DEPTH_ONE, null);
						IFile pluginFile = project.getFile("plugin.xml");
						pluginFile.refreshLocal(IResource.DEPTH_ONE, null);

						WorkspaceBundlePluginModel model = new WorkspaceBundlePluginModel(manifestFile, pluginFile);
						//model.setExtensionsModel(new WorkspaceExtensionsModel(pluginFile));
						plugin = model;
						extensions = model;
						factory = model.getFactory();
						// the workspace extensions are not loaded, manually copy
						{
							IPluginModelBase workspacePluginModelBase = PluginRegistry.findModel(project);
							if (workspacePluginModelBase != null) {
								IExtensions wextensions = workspacePluginModelBase.getExtensions();
								if (wextensions != null) {
									for (IPluginExtension pluginExtension : wextensions.getExtensions()) {
										extensions.getExtensions().add(copy(factory, pluginExtension));
									}
								}
							}
						}
					}

					// remove existing generated package extension entries
					for (IPluginExtension extension : extensions.getExtensions().getExtensions()) {
						if ("org.eclipse.emf.ecore.generated_package".equals(extension.getPoint())) {
							extensions.getExtensions().remove(extension);
						}
					}

					// add the generated package extension
					for (GenPackage genPackage : genModel.getGenPackages()) {
						// only generate code for the schema in this project
						if (primaryNSURIs.containsValue(genPackage.getNSURI())) {
							String nsURI = genPackage.getNSURI();
							String packageClassName = "" + genPackage.getBasePackage() + "."
									+ genPackage.getPackageName() + "." + genPackage.getPackageInterfaceName();

							IPluginExtension extension = factory.createExtension();
							extension.setPoint("org.eclipse.emf.ecore.generated_package");
							IPluginElement element = factory.createElement(extension);
							element.setName("package");
							element.setAttribute("uri", nsURI);
							element.setAttribute("class", packageClassName);
							extension.add(element);
							extensions.getExtensions().add(extension);
						}
					}

					// remove existing processed schema entries
					for (IPluginExtension extension : extensions.getExtensions().getExtensions()) {
						if ("org.archstudio.dblgen.processedSchema".equals(extension.getPoint())) {
							extensions.getExtensions().remove(extension);
						}
					}

					// add an entry for each processed schema
					for (Entry<String, String> entry : primaryNSURIs.entrySet()) {
						String nsURI = entry.getValue();
						if (nsuriToSchemaRecord.get(nsURI).size() > 0) {
							SchemaRecord schemaRecord = nsuriToSchemaRecord.get(nsURI).iterator().next();
							IFile schemaFile = schemaRecord.getSchemaFile();
							String localFile = schemaFile.getProjectRelativePath().toString();

							IPluginExtension extension = factory.createExtension();
							extension.setPoint("org.archstudio.dblgen.processedSchema");
							IPluginElement element = factory.createElement(extension);
							element.setName("Schema");
							element.setAttribute("nsURI", nsURI);
							element.setAttribute("file", localFile);
							extension.add(element);
							extensions.getExtensions().add(extension);
						}
					}

					{
						// save the editable IPluginModelBase
						WorkspaceBundlePluginModel model = (WorkspaceBundlePluginModel) plugin;
						model.save();
					}
				}
				catch (Throwable t) {
					// well, we tried
					t.printStackTrace();
				}
			}
		}

		return statusList;
	}

	private IPluginExtension copy(IExtensionsModelFactory factory, IPluginExtension pluginExtension)
			throws CoreException {
		IPluginExtension extension = factory.createExtension();
		if (pluginExtension.getId() != null && pluginExtension.getId().length() > 0) {
			extension.setId(pluginExtension.getId());
		}
		if (pluginExtension.getName() != null && pluginExtension.getName().length() > 0) {
			extension.setName(pluginExtension.getName());
		}
		if (pluginExtension.getPoint() != null && pluginExtension.getPoint().length() > 0) {
			extension.setPoint(pluginExtension.getPoint());
		}
		for (IPluginObject child : pluginExtension.getChildren()) {
			extension.add(copy(factory, extension, child));
		}
		return extension;
	}

	private IPluginObject copy(IExtensionsModelFactory factory, IPluginObject parent, IPluginObject child)
			throws CoreException {
		IPluginElement newChild = factory.createElement(parent);
		if (child.getName() != null && child.getName().length() > 0) {
			newChild.setName(child.getName());
		}
		for (IPluginAttribute attribute : ((IPluginElement) child).getAttributes()) {
			newChild.setAttribute(attribute.getName(), attribute.getValue());
		}
		return newChild;
	}

	private <T> List<T> notNull(T[] e) {
		if (e == null) {
			return Lists.newArrayList();
		}
		return Lists.newArrayList(e);
	}

	private void findFoldersWithFiles(IFolder folder, Collection<IFolder> foldersWithFiles) throws CoreException {
		for (IResource r : folder.members()) {
			if (r instanceof IFile) {
				foldersWithFiles.add(folder);
			}
			else {
				findFoldersWithFiles((IFolder) r, foldersWithFiles);
			}
		}
	}
}
