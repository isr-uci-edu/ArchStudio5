package org.archstudio.dblgen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.archstudio.dblgen.DataBindingGenerationStatus.Status;
import org.archstudio.dblgen.builder.Xadl3SchemaLocation;
import org.archstudio.dblgen.core.Activator;
import org.archstudio.releng.pde.actions.SortManifests;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.utils.eclipse.jdt.CodeGeneration;
import org.archstudio.utils.osgi.OSGiUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.osgi.framework.util.Headers;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IExtensionsModelFactory;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
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
import org.osgi.framework.BundleException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;

@SuppressWarnings("restriction")
public class DataBindingGeneratorImpl implements IDataBindingGenerator {
	public static final String XADL3_SCHEMA_NATURE_ID = "org.archstudio.dblgen.xadl3SchemaNature";

	static {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(GenModelPackage.eNS_URI,
				GenModelGeneratorAdapterFactory.DESCRIPTOR);
		URIConverter.URI_MAP.put(URI.createURI(EcorePackage.eNS_URI),
				URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xsd"));
	}

	/**
	 * Parses an XML file.
	 *
	 * @param documentInputStream Input stream for XML content
	 * @return Parsed DOM Document object
	 * @throws ParserConfigurationException If the parser was misconfigured somehow.
	 * @throws SAXException on a parsing error.
	 * @throws IOException on an I/O error.
	 */
	private static Document parseDocument(InputStream documentInputStream)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder docBuilder;
		Document doc = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setIgnoringElementContentWhitespace(true);

		docBuilder = docBuilderFactory.newDocumentBuilder();
		doc = docBuilder.parse(documentInputStream);
		return doc;
	}

	private static String getProjectName(IProject project) throws BundleException, IOException, CoreException {
		Headers<String, String> manifestHeaders = Headers.parseManifest(
				new ByteArrayInputStream(SystemUtils.blt(project.getFile("META-INF/MANIFEST.MF").getContents())));
		ManifestElement[] elements =
				ManifestElement.parseHeader("Bundle-SymbolicName", manifestHeaders.get("Bundle-SymbolicName"));
		return elements[0].getValue();
	}

	private static String getSchemaNsUri(InputStream is)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc = DataBindingGeneratorImpl.parseDocument(is);
		Element elt = doc.getDocumentElement();
		String xmlns = elt.getAttribute("targetNamespace");
		if (xmlns == null || xmlns.trim().length() == 0) {
			xmlns = elt.getAttribute("xmlns");
		}
		return xmlns;
	}

	private static IPluginExtension copyPluginExtensions(IExtensionsModelFactory factory,
			IPluginExtension pluginExtension) throws CoreException {
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
			extension.add(copyPluginExtension(factory, extension, child));
		}
		return extension;
	}

	private static IPluginObject copyPluginExtension(IExtensionsModelFactory factory, IPluginObject parent,
			IPluginObject child) throws CoreException {
		IPluginElement newChild = factory.createElement(parent);
		if (child.getName() != null && child.getName().length() > 0) {
			newChild.setName(child.getName());
		}
		for (IPluginAttribute attribute : ((IPluginElement) child).getAttributes()) {
			newChild.setAttribute(attribute.getName(), attribute.getValue());
		}
		for (IPluginObject grandchild : ((IPluginElement) child).getChildren()) {
			newChild.add(copyPluginExtension(factory, child, grandchild));
		}
		if (((IPluginElement) child).getText() != null) {
			newChild.setText(((IPluginElement) child).getText());
		}
		return newChild;
	}

	/**
	 * Internal data structure for capturing data about a Schema in a xADL Schema project.
	 */
	private static class SchemaRecord {
		/** The name space URI of the schema. */
		final URI nsUri;

		/** The project containing the schema May be <code>null</code>. */
		final IProject project;

		/** The bundle containing the schema. May be <code>null</code>. */
		final Bundle bundle;

		/** Relative path from the bundle/project root to the schema. */
		String schemaPath;

		/** Absolute URL to schema. */
		String schemaUrl;

		/** URL of the schema file as resolved against the bundle/project. */
		URL xsdFileUrl;

		/** Parsed schema doc object. */
		final Document doc;

		/** The bundle/project name containing the schema. */
		final String name;

		/** The import nsUri's within the XSD file. */
		final List<String> imports;

		/** The target package name for the schema. */
		final String packageName;

		public SchemaRecord(URI nsuri, IProject project, Bundle bundle, String schemaPath, String schemaUrl,
				URL xsdFileUrl, String packageName)
						throws ParserConfigurationException, SAXException, IOException, CoreException, BundleException {
			this.nsUri = nsuri;
			this.project = project;
			this.bundle = bundle;
			this.schemaPath = schemaPath;
			this.schemaUrl = schemaUrl;
			this.xsdFileUrl = xsdFileUrl;
			this.packageName = packageName;
			doc = DataBindingGeneratorImpl.parseDocument(xsdFileUrl.openStream());
			name = getName();
			imports = getImports();
		}

		public String getName() throws BundleException, IOException, CoreException {
			if (project != null) {
				return getProjectName(project);
			}
			else if (bundle != null) {
				return bundle.getSymbolicName();
			}
			throw new NullPointerException("Project and bundle not defined.");
		}

		List<String> getImports() {
			List<String> imports = new ArrayList<>();
			Element docElt = doc.getDocumentElement();
			NodeList children = docElt.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node n = children.item(i);
				if (n.getNodeName().endsWith(":import") && n instanceof Element) {
					imports.add(((Element) n).getAttribute("namespace"));
				}
			}
			return imports;
		}
	}

	private Monitor emfMonitor;
	// Name space URI to schema record.
	private Map<String, SchemaRecord> schemaRecords = new HashMap<>();
	// A mapping from SchemaRecord.name (i.e., bundle or project name) to all SchemaRecord ns URI's in
	// that bundle/project.
	private ListMultimap<String, String> namedSchemaRecords = ArrayListMultimap.create();

	public DataBindingGeneratorImpl()
			throws ParserConfigurationException, SAXException, IOException, CoreException, BundleException {
		// Refresh the resources in case they've been modified.
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		workspaceRoot.refreshLocal(IResource.DEPTH_INFINITE, null);

		initializeSchemaRecordsFromBundles();
		// Scan projects second in order to overwrite any installed bundles.
		initializeSchemaRecordsFromProjects();
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

	private void initializeSchemaRecordsFromBundles()
			throws ParserConfigurationException, SAXException, IOException, CoreException, BundleException {
		for (IConfigurationElement c : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("org.archstudio.dblgen", "processedSchema")) {
			URI nsURI = URI.createURI(c.getAttribute("nsURI"));
			String schemaPath = c.getAttribute("file");
			String schemaUrl = c.getAttribute("url");
			Bundle bundle = Platform.getBundle(c.getContributor().getName());
			URL fileUrl;
			if (schemaPath != null) {
				fileUrl = bundle.getResource(schemaPath);
				if (fileUrl == null) {
					throw new NullPointerException("Cannot find schema for " + c.getContributor().getName() + "/"
							+ schemaPath + ". Did you include it in the build?");
				}
			}
			else if (schemaUrl != null) {
				fileUrl = new URL(schemaUrl);
			}
			else {
				throw new NullPointerException(
						"Neither file nor url are defined for " + c.getContributor().getName() + ".");
			}
			SchemaRecord record = new SchemaRecord(nsURI, null, bundle, schemaPath, schemaUrl, fileUrl, null);
			schemaRecords.put(nsURI.toString(), record);
			namedSchemaRecords.put(record.name, nsURI.toString());
		}
		for (IConfigurationElement c : RegistryFactory.getRegistry()
				.getConfigurationElementsFor("org.archstudio.dblgen", "schemalocation")) {
			URL url = new URL(c.getAttribute("url"));
			URI nsURI = URI.createURI(getSchemaNsUri(url.openStream()));
			String packageName = c.getAttribute("name");
			Bundle bundle = Platform.getBundle(c.getContributor().getName());
			SchemaRecord record = new SchemaRecord(nsURI, null, bundle, null, url.toString(), url, packageName);
			schemaRecords.put(nsURI.toString(), record);
			namedSchemaRecords.put(record.name, nsURI.toString());
		}
	}

	private void initializeSchemaRecordsFromProjects()
			throws ParserConfigurationException, SAXException, IOException, CoreException, BundleException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (final IProject project : workspace.getRoot().getProjects()) {
			if (project.isAccessible() && project.hasNature(DataBindingGeneratorImpl.XADL3_SCHEMA_NATURE_ID)) {
				final String name = getProjectName(project);
				// Clear out bundle schema for the same name.
				namedSchemaRecords.removeAll(name);
				project.getFolder("model").accept(new IResourceVisitor() {
					@Override
					public boolean visit(IResource resource) throws CoreException {
						try {
							if (resource instanceof IFile) {
								IFile file = (IFile) resource;
								if ("xsd".equalsIgnoreCase(file.getFileExtension())) {
									InputStream is = null;
									try {
										URI nsURI = URI.createURI(getSchemaNsUri(file.getContents()));
										SchemaRecord record = new SchemaRecord(nsURI, project, null,
												file.getFullPath().makeRelativeTo(project.getFullPath()).toString(),
												null, file.getLocationURI().toURL(), null);
										schemaRecords.put(nsURI.toString(), record);
										namedSchemaRecords.put(name, nsURI.toString());
									}
									finally {
										if (is != null) {
											is.close();
										}
									}
								}
							}
						}
						catch (Exception e) {
							throw new CoreException(new org.eclipse.core.runtime.Status(IStatus.ERROR,
									"org.archstudio.dblgen.core", e.getMessage(), e));
						}
						return true;
					}
				});
				// Scan plugin for schemalocations.
				for (Xadl3SchemaLocation location : Xadl3SchemaLocation.parse(project)) {
					URL url = new URL(location.getUrl().toString());
					URI nsURI = URI.createURI(getSchemaNsUri(url.openStream()));
					SchemaRecord record =
							new SchemaRecord(nsURI, project, null, null, url.toString(), url, location.getName());
					schemaRecords.put(nsURI.toString(), record);
					namedSchemaRecords.put(record.name, nsURI.toString());
				}
			}
		}
	}

	@Override
	public synchronized List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings,
			String projectName) {
		return generateBindings(schemaURIStrings, Collections.<Xadl3SchemaLocation> emptyList(), projectName);
	}

	// projectName = e.g., "org.archstudio.xadl3.bindings"
	public synchronized List<DataBindingGenerationStatus> generateBindings(List<String> schemaURIStrings,
			List<Xadl3SchemaLocation> schemaLocations, final String projectName) {
		List<DataBindingGenerationStatus> statusList = new ArrayList<DataBindingGenerationStatus>();
		try {
			// Map available schema for offline use.
			for (SchemaRecord schemaRecord : schemaRecords.values()) {
				URIConverter.URI_MAP.put(schemaRecord.nsUri, URI.createURI(schemaRecord.xsdFileUrl.toURI().toString()));
			}

			final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

			XSDImporter importer = new XSDImporter() {
				/*
				 * Note: XSDImporter seems to not support non-local files (e.g., projects imported from an external
				 * folder, linked files, etc.). This is a problem because, in such cases, the generated resources are
				 * saved in the wrong location. Later, when searching for the resources, they cannot be found and the
				 * process fails. To resolve this, we extend the base class and move the resources to the correct
				 * location before saving them.
				 */
				@Override
				protected List<Resource> computeResourcesToBeSaved() {
					List<Resource> resources = super.computeResourcesToBeSaved();
					// Move these resources to the correct location.
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

			// We still have to assign some value here, even though it doesn't work.
			importer.setGenModelContainerPath(project.getFullPath());
			importer.setGenModelFileName("xadl.genmodel");

			String subfolder = "src";
			final IPath subfolderPath = project.getFolder(subfolder).getFullPath();
			importer.setModelPluginID(projectName);
			importer.setModelPluginDirectory("/" + projectName + "/" + subfolder);
			StringBuilder combinedModelURIs = new StringBuilder();
			for (String modelURIString : schemaURIStrings) {
				combinedModelURIs.append(modelURIString);
			}
			for (Xadl3SchemaLocation schemaLocation : schemaLocations) {
				if (!schemaLocation.isCopyLocally()) {
					combinedModelURIs.append(schemaLocation.getUrl().toString()).append(" ");
				}
			}
			importer.setModelLocation(combinedModelURIs.toString());

			boolean computePackagesSucceeded = false;
			try {
				importer.computeEPackages(emfMonitor);
				computePackagesSucceeded = true;

				// Fix names of external packages.
				Multimap<String, EPackage> packages =
						Multimaps.index(importer.getEPackages(), new Function<EPackage, String>() {
							@Override
							public String apply(EPackage input) {
								return input.getNsURI();
							}
						});
				for (SchemaRecord record : schemaRecords.values()) {
					if (record.packageName != null && packages.get(record.nsUri.toString()).size() > 0) {
						EPackage e = packages.get(record.nsUri.toString()).iterator().next();
						e.setName(record.packageName);
					}
				}
			}
			catch (Exception e) {
				// Yes, in a remarkable display of programming good taste, computeEPackages does declare
				// that it throws "Exception"
				e.printStackTrace();
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
						"Error computing packages for schemas: " + importer.getModelLocation(), e));
				return statusList;
			}
			if (!computePackagesSucceeded) {
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
						"Computing packages unsuccessful for schema: " + importer.getModelLocation(), null));
				return statusList;
			}

			// A list of EPackages that are referenced by the main schemas but for which we're not going
			// to generate bindings. We assume these are living in other schema projects.
			for (EPackage ePackage : importer.getEPackages()) {
				EPackageConvertInfo ePackageConvertInfo = importer.getEPackageConvertInfo(ePackage);
				if (ePackage.getNsURI().equals(EcorePackage.eNS_URI)) {
					// Don't convert the Ecore dependency; this seems to get included automagically.
					ePackageConvertInfo.setConvert(false);
					continue;
				}
				ePackageConvertInfo.setConvert(true);
			}

			// This causes the code to be generated with generics and such.
			GenModel genModel = importer.getGenModel();
			genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);

			// Inscrutable EMF Magic: Note that this has to be done before the below loop through
			// referenced packages or it will throw a NullPointerException when adding packages to the
			// ResourceSet.
			importer.adjustEPackages(emfMonitor);
			importer.prepareGenModelAndEPackages(emfMonitor);

			boolean modelSaveSucceeded = false;
			try {
				importer.saveGenModelAndEPackages(emfMonitor);
				modelSaveSucceeded = true;
			}
			catch (Exception e) {
				// Another tasteful function that "throws Exception".
				e.printStackTrace();
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
						"Error saving generated content for schema: " + importer.getModelLocation(), e));
				return statusList;
			}
			if (!modelSaveSucceeded) {
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
						"Error saving generated content for schema: " + importer.getModelLocation(), null));
				return statusList;
			}

			try {
				genModel.setCanGenerate(true);
				Generator codeGenerator = new Generator();
				codeGenerator.setInput(genModel);
				// Find the gen package for the targeted schema.
				for (GenPackage genPackage : genModel.getGenPackages()) {
					// Only generate code for the schema in this project.
					if (namedSchemaRecords.get(projectName).contains(genPackage.getNSURI())) {
						codeGenerator.generate(genPackage, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, emfMonitor);
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
						"Error generating content for schema: " + importer.getModelLocation(), e));
				return statusList;
			}

			// Update plugin's dependencies to include referenced schema.
			BundleContext context = Activator.getSingleton().getContext();
			final IBundleProjectService service = OSGiUtils.getServiceReference(context, IBundleProjectService.class);
			IBundleProjectDescription description = service.getDescription(project);
			Map<String, IRequiredBundleDescription> requiredBundles = new HashMap<>();
			for (IRequiredBundleDescription requiredBundle : SystemUtils
					.emptyIfNull(description.getRequiredBundles())) {
				requiredBundles.put(requiredBundle.getName(), requiredBundle);
			}
			requiredBundles.put("org.eclipse.emf.ecore",
					service.newRequiredBundle("org.eclipse.emf.ecore", null, false, true));
			requiredBundles.put("org.eclipse.emf.ecore.xmi",
					service.newRequiredBundle("org.eclipse.emf.ecore.xmi", null, false, true));
			requiredBundles.put("org.archstudio.dblgen",
					service.newRequiredBundle("org.archstudio.dblgen", null, false, true));
			for (String nsUri : SystemUtils.emptyIfNull(namedSchemaRecords.get(projectName))) {
				if (schemaRecords.containsKey(nsUri)) {
					for (String importedNsUri : schemaRecords.get(nsUri).imports) {
						if (schemaRecords.containsKey(importedNsUri)) {
							String name = schemaRecords.get(importedNsUri).name;
							if (!projectName.equals(name)) {
								requiredBundles.put(name, service.newRequiredBundle(name, null, false, true));
							}
						}
					}
				}
			}
			description.setRequiredBundles(
					requiredBundles.values().toArray(new IRequiredBundleDescription[requiredBundles.size()]));

			// Updated plugin's exported packages to include the java packages created. Actually, only
			// export created packages.
			final Map<String, IPackageExportDescription> packageExports = new HashMap<>();
			project.getFolder(subfolder).accept(new IResourceVisitor() {
				@Override
				public boolean visit(IResource resource) throws CoreException {
					try {
						if (resource instanceof IFile) {
							IFile file = (IFile) resource;
							IPath path = file.getParent().getFullPath();
							String packageName = path.makeRelativeTo(subfolderPath).toString().replace('/', '.');
							packageExports.put(packageName, service.newPackageExport(packageName, null, true, null));
						}
					}
					catch (Exception e) {
						throw new CoreException(new org.eclipse.core.runtime.Status(IStatus.ERROR,
								"org.archstudio.dblgen.core", "Error visiting " + resource.toString(), e));
					}
					return true;
				}
			});
			description.setPackageExports(
					packageExports.values().toArray(new IPackageExportDescription[packageExports.size()]));

			// Update plugin's bin includes to include the model folder.
			Set<IPath> binIncludes = Sets.newHashSet(SystemUtils.emptyIfNull(description.getBinIncludes()));
			binIncludes.add(new Path("model/"));
			List<IPath> binIncludesSorted = Lists.newArrayList(binIncludes);
			Collections.sort(binIncludesSorted, new Comparator<IPath>() {
				@Override
				public int compare(IPath o1, IPath o2) {
					return o1.toString().compareTo(o2.toString());
				}
			});
			description.setBinIncludes(binIncludesSorted.toArray(new IPath[binIncludesSorted.size()]));

			// Make sure that extensions are allowed.
			description.setExtensionRegistry(true);

			// Store the changes.
			description.apply(null);
			project.refreshLocal(IResource.DEPTH_INFINITE, null);

			// Add the package and parser extensions to the plugin.
			IFile manifestFile = project.getFile("META-INF/MANIFEST.MF");
			manifestFile.refreshLocal(IResource.DEPTH_ONE, null);
			IFile pluginFile = project.getFile("plugin.xml");
			pluginFile.refreshLocal(IResource.DEPTH_ONE, null);
			WorkspaceBundlePluginModel pluginModel = new WorkspaceBundlePluginModel(manifestFile, pluginFile);
			IExtensionsModelFactory pluginFactory = pluginModel.getFactory();

			// The workspace extensions are not loaded by default, so manually copy them.
			IPluginModelBase existingPluginModel = PluginRegistry.findModel(project);
			if (existingPluginModel != null) {
				IExtensions extensions = existingPluginModel.getExtensions();
				if (extensions != null) {
					for (IPluginExtension pluginExtension : extensions.getExtensions()) {
						pluginModel.getExtensions().add(copyPluginExtensions(pluginFactory, pluginExtension));
					}
				}
			}

			// Remove existing generated extensions.
			for (IPluginExtension extension : Lists.newArrayList(pluginModel.getExtensions().getExtensions())) {
				if ("org.eclipse.emf.ecore.generated_package".equals(extension.getPoint())) {
					pluginModel.getExtensions().remove(extension);
				}
				if ("org.eclipse.emf.ecore.extension_parser".equals(extension.getPoint())) {
					pluginModel.getExtensions().remove(extension);
				}
				if ("org.archstudio.dblgen.processedSchema".equals(extension.getPoint())) {
					pluginModel.getExtensions().remove(extension);
				}
			}

			// Add the generated package extensions.
			for (GenPackage genPackage : genModel.getGenPackages()) {
				// Only generate code for the schema in this project.
				if (namedSchemaRecords.get(projectName).contains(genPackage.getNSURI())) {
					String nsURI = genPackage.getNSURI();
					String packageClassName = "" + genPackage.getBasePackage() + "." + genPackage.getPackageName() + "."
							+ genPackage.getPackageInterfaceName();
					IPluginExtension extension = pluginModel.createExtension();
					extension.setPoint("org.eclipse.emf.ecore.generated_package");
					IPluginElement element = pluginModel.createElement(extension);
					element.setName("package");
					element.setAttribute("uri", nsURI);
					element.setAttribute("class", packageClassName);
					extension.add(element);
					pluginModel.getExtensions().add(extension);
				}
			}

			// Add an entry for each processed schema.
			for (String nsUri : namedSchemaRecords.get(projectName)) {
				SchemaRecord schemaRecord = schemaRecords.get(nsUri);
				IPluginExtension extension = pluginModel.createExtension();
				extension.setPoint("org.archstudio.dblgen.processedSchema");
				IPluginElement element = pluginModel.createElement(extension);
				element.setName("Schema");
				element.setAttribute("nsURI", nsUri);
				if (schemaRecord.schemaPath != null) {
					element.setAttribute("file", schemaRecord.schemaPath);
				}
				if (schemaRecord.schemaUrl != null) {
					element.setAttribute("url", schemaRecord.schemaUrl);
				}
				extension.add(element);
				pluginModel.getExtensions().add(extension);
			}

			// Save plug-in model.
			pluginModel.save();

			// Format generated files.
			CodeGeneration.formatCode(project);
			SortManifests.sortManifest(project);
		}
		catch (Exception e) {
			e.printStackTrace();
			statusList.add(new DataBindingGenerationStatus(null, Status.FAILURE,
					"Error processing schema: " + e.getMessage(), e));
			return statusList;
		}
		return statusList;
	}
}
