package org.archstudio.dblgen.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.archstudio.dblgen.common.DataBindingGenerationStatus;
import org.archstudio.dblgen.common.IDataBindingGenerator;
import org.archstudio.dblgen.common.DataBindingGenerationStatus.Status;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.xsd.ecore.importer.XSDImporter;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DataBindingGeneratorImpl implements IDataBindingGenerator {
	public static final String XADL3_SCHEMA_NATURE_ID = "org.archstudio.eclipsedev.xadl3SchemaNature";
	
	static {
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(GenModelPackage.eNS_URI, GenModelGeneratorAdapterFactory.DESCRIPTOR);

		URIConverter.URI_MAP.put(URI.createURI(EcorePackage.eNS_URI), URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xsd"));
	}

	private Monitor emfMonitor;

	public DataBindingGeneratorImpl() {
	}

	public synchronized void setMonitor(Monitor emfMonitor) {
		if (emfMonitor == null) {
			this.emfMonitor = createBasicPrintingMonitor();
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
	 * @param fileInputStream
	 *            Input stream for the XML file
	 * @return Parsed DOM Document object
	 * @throws ParserConfigurationException
	 *             If the parser was misconfigured somehow.
	 * @throws SAXException
	 *             on a parsing error.
	 * @throws IOException
	 *             on an I/O error.
	 */
	public static Document parseFile(InputStream fileInputStream) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder docBuilder;
		Document doc = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setIgnoringElementContentWhitespace(true);

		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(fileInputStream);
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
				if (project.hasNature(XADL3_SCHEMA_NATURE_ID)) {
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
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						IFile file = (IFile) resource;
						String extension = file.getFileExtension();
						if ((extension != null) && (extension.equals("xsd"))) {
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
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						IFile file = (IFile) resource;
						String extension = file.getFileExtension();
						if ((extension != null) && (extension.equals("genmodel"))) {
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
	public String getNSURIForSchemaFile(IFile schemaFile) throws ParserConfigurationException, SAXException, IOException, CoreException {
		InputStream is = null;
		try {
			is = schemaFile.getContents();
			Document doc = parseFile(is);
			Element docElt = doc.getDocumentElement();
			String xmlns = docElt.getAttribute("xmlns");
			return xmlns;
		}
		finally {
			if (is != null) {
				is.close();
			}
		}
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
	public Map<IFile, String> getNSURIsForSchemaFiles(List<IFile> schemaFileList) throws ParserConfigurationException, SAXException, IOException, CoreException {
		Map<IFile, String> schemaToNSURIMap = new HashMap<IFile, String>();
		for (IFile schemaFile : schemaFileList) {
			String nsuri = getNSURIForSchemaFile(schemaFile);
			schemaToNSURIMap.put(schemaFile, nsuri);
		}
		return schemaToNSURIMap;
	}

	/**
	 * Returns a mapping of schema URIs (in the Eclipse universe) to namespace
	 * URIs for those schema files.
	 * 
	 * @param schemaURIStrings
	 *            A list of Eclipse URIs pointing to <tt>.xsd</tt> files.
	 * @param resourceSet
	 *            Resource set to use for resolving URIs in
	 *            <tt>schemaURIStrings</tt>
	 * @return Mapping from URIs in <tt>schemaURIStrings</tt> to namespace URIs
	 *         declared in those schemas. Errors and exceptions are ignored and
	 *         those URIs will not be present in the returned map.
	 */
	private static Map<String, String> getNSURIsForSchemaFiles(List<String> schemaURIStrings, ResourceSet resourceSet) throws ParserConfigurationException,
	        SAXException, IOException, CoreException {
		Map<String, String> schemaURItoNSURIMap = new HashMap<String, String>();
		for (String schemaURIString : schemaURIStrings) {
			URI uri = URI.createURI(schemaURIString);
			InputStream is = resourceSet.getURIConverter().createInputStream(uri);
			Document doc = parseFile(is);
			Element docElt = doc.getDocumentElement();
			String xmlns = docElt.getAttribute("xmlns");
			schemaURItoNSURIMap.put(schemaURIString, xmlns);
			is.close();
		}
		return schemaURItoNSURIMap;
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
		//Gets the path in the form /projectname/path/to/file, which is coincidentally the input
		//to (emf)URI.createPlatformResourceURI()
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
			if ((genPackageNSURI != null) && (genPackageNSURI.equals(EcorePackage.eNS_URI))) {
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
	 *            <tt>ResourceSet</tt> used to resolve URIs
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
	private List<SchemaRecord> getSchemaRecords(ModelImporter importer) throws ParserConfigurationException, SAXException, IOException, CoreException {
		List<SchemaRecord> schemaRecordList = new ArrayList<SchemaRecord>();

		ResourceSet resourceSet = importer.createResourceSet();

		try {
			// Get all the xADL schema projects in the workspace.
			List<IProject> xadlSchemaProjects = getXadlSchemaProjects();

			// Iterate through each project
			for (IProject xadlSchemaProject : xadlSchemaProjects) {

				// List of .xsd files in the project that we want to parse
				List<IFile> schemaFiles = getSchemaFilesInProject(xadlSchemaProject);
				System.err.println("Schema files: " + schemaFiles);

				// Schema URIs for each XSD file.  
				Map<IFile, String> schemaToNSURIMap = getNSURIsForSchemaFiles(schemaFiles);
				System.err.println("Schema URIs: " + schemaToNSURIMap);

				// List of .genmodel files in the project that we want to parse
				List<IFile> genModelFiles = getGenModelFilesInProject(xadlSchemaProject);

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
								if ((genPackageNSURI != null) && (genPackageNSURI.equals(recordNSURI))) {
									recordGenModelFile = entry.getKey();
									recordGenModel = entry.getValue();
									recordGenPackage = genPackage;
									found = true;
									break;
								}
							}
							if (found)
								break;
						}
					}

					SchemaRecord newSchemaRecord = new SchemaRecord(recordNSURI, xadlSchemaProject, recordGenModelFile, recordSchemaFile, recordGenModel,
					        recordGenPackage);
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

		public SchemaRecord(String nsuri, IProject project, IFile genModelFile, IFile schemaFile, GenModel genModel, GenPackage genPackage) {
			super();
			this.nsuri = nsuri;
			this.project = project;
			this.genModelFile = genModelFile;
			this.schemaFile = schemaFile;
			this.genModel = genModel;
			this.genPackage = genPackage;
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

	//projectName = e.g., "edu.uci.isr.xadl3bindings"
	public synchronized List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings, String projectName) {
		List<DataBindingGenerationStatus> statusList = new ArrayList<DataBindingGenerationStatus>();

		String shortProjectName = projectName.substring(projectName.lastIndexOf(".") + 1);

		XSDImporter importer = new XSDImporter();
		importer.setUsePlatformURI(false);
		importer.setCreateEcoreMap(true);

		IPath genModelContainerPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/" + projectName);
		importer.setGenModelContainerPath(genModelContainerPath);
		importer.setGenModelFileName(shortProjectName + ".genmodel");

		importer.setModelPluginID(projectName);
		importer.setModelPluginDirectory("/" + projectName + "/src");

		ResourceSet resourceSet = importer.createResourceSet();

		// Determine what schemas are available in the workspace, and parse their
		// genmodel files 
		List<SchemaRecord> schemaRecords = null;
		try {
			schemaRecords = getSchemaRecords(importer);
		}
		catch (ParserConfigurationException pce) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Parser configuration error parsing schemas", pce));
			return statusList;
		}
		catch (SAXException se) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Parsing exception parsing schemas", se));
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

		// primaryNSURIs is a list of NSURIs from schemas in the project
		Map<String, String> primaryNSURIs = null;
		try {
			primaryNSURIs = getNSURIsForSchemaFiles(schemaURIStrings, resourceSet);
		}
		catch (ParserConfigurationException pce) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Parser configuration error parsing schemas", pce));
			return statusList;
		}
		catch (SAXException se) {
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Parsing exception parsing schemas", se));
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
		String combinedModelURIString = sb.toString();

		importer.setModelLocation(combinedModelURIString);

		boolean computePackagesSucceeded = false;
		try {
			importer.computeEPackages(emfMonitor);
			computePackagesSucceeded = true;
		}
		catch (Exception e) {
			// Yes, in a remarkable display of programming good taste, computeEPackages
			// does declare that it throws "Exception"
			e.printStackTrace();
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Error computing packages for schemas " + combinedModelURIString, e));
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
						ePackageConvertInfo.setConvert(false);
						referencedEPackageList.add(ePackage);
					}
				}
			}

			GenModel genModel = importer.getGenModel();
			// Resource genModelResource = genModel.eResource();
			EList<GenPackage> usedGenPackages = genModel.getUsedGenPackages();

			// This causes the code to be generated with generics and such
			genModel.setComplianceLevel(GenJDKLevel.JDK60_LITERAL);

			// Inscrutable EMF Magic
			// Note that this has to be done before the below loop through
			// referenced packages or it will throw a NullPointerException when
			// adding packages to the ResourceSet
			importer.adjustEPackages(emfMonitor);
			importer.prepareGenModelAndEPackages(emfMonitor);

			// Iterate through the referenced packages and add the corresponding gen model to the used list.
			for (EPackage ePackage : referencedEPackageList) {
				String referencedPackageNSURI = ePackage.getNsURI();

				// If the referenced package is Ecore, don't process that the same way
				if (!referencedPackageNSURI.equals(EcorePackage.eNS_URI)) {
					boolean foundSchema = false;
					boolean foundGenPackage = false;
					for (SchemaRecord schemaRecord : schemaRecords) {
						if ((schemaRecord.getNsuri() != null) && (schemaRecord.getNsuri().equals(referencedPackageNSURI))) {
							foundSchema = true;
							if (schemaRecord.getGenPackage() != null) {
								usedGenPackages.add(schemaRecord.getGenPackage());
								foundGenPackage = true;
							}
						}
					}

					if (!foundSchema) {
						statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, 
								"Missing project containing schema file with namespace URI " + 
								referencedPackageNSURI, null));
					}
					else if (!foundGenPackage) {
						statusList.add(new DataBindingGenerationStatus(null, Status.WARNING, 
								"Missing GenModel/GenPackage for schema with namespace URI " + 
								referencedPackageNSURI, null));
					}
				}
				else {
					// If the referenced ePackage is the ecore ePackage, then we add
					// a reference to its GenPackage as stored in the platform plugin.
					GenPackage ecoreGenPackage = getEcoreGenPackage(resourceSet);
					usedGenPackages.add(ecoreGenPackage);
				}

				// This is primarily for the Ecore ePackage, which (when we go to save the files
				// all below) if it doesn't get added to the resource, will cause a DanglingHrefException.
				// More inscrutable EMF magic.
				if (ePackage.eResource() == null) {
					importer.addToResource(ePackage, importer.getGenModelResourceSet());
				}
			}

			boolean modelSaveSucceeded = false;
			try {
				importer.saveGenModelAndEPackages(emfMonitor);
				modelSaveSucceeded = true;
			}
			catch (Exception e) {
				// Another tasteful function that "throws Exception"
				e.printStackTrace();
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE, "Error saving generated content for " + combinedModelURIString, e));
			}

			if (modelSaveSucceeded) {
				genModel.setCanGenerate(true);

				Generator codeGenerator = new Generator();
				codeGenerator.setInput(genModel);
				codeGenerator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, emfMonitor);
			}
		}

		return statusList;
	}
}
