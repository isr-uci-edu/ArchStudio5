package org.archstudio.myxgen.eclipse.extension;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.osgi.framework.Bundle;

@SuppressWarnings("deprecation")
public class PDEUtils {

	private static EReference getEReference(EClass eClass, String name) {
		EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(name);
		if (eStructuralFeature instanceof EReference) {
			return (EReference) eStructuralFeature;
		}
		for (EReference eReference : eClass.getEAllReferences()) {
			if (name.equals(eReference.getName())) {
				return eReference;
			}
		}
		return null;
	}

	private static EAttribute getEAttribute(EClass eClass, String name) {
		EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(name);
		if (eStructuralFeature instanceof EAttribute) {
			return (EAttribute) eStructuralFeature;
		}
		for (EAttribute eAttribute : eClass.getEAllAttributes()) {
			if (name.equals(eAttribute.getName())) {
				return eAttribute;
			}
		}
		return null;
	}

	private static class IPluginModelBasesToIExtensionPoint implements IExtensionPoint {

		private final String extensionPointId;
		private final IPluginModelBase[] pluginModelBases;

		public IPluginModelBasesToIExtensionPoint(String extensionPointId, IPluginModelBase[] pluginModelBases) {
			this.extensionPointId = extensionPointId;
			this.pluginModelBases = pluginModelBases;
		}

		public synchronized IConfigurationElement[] getConfigurationElements() throws InvalidRegistryObjectException {
			List<IConfigurationElement> configurationElements = new ArrayList<IConfigurationElement>();
			for (IPluginModelBase pluginModelBase : pluginModelBases) {
				if (!pluginModelBase.isEnabled() || !pluginModelBase.isValid()) {
					continue;
				}
				URI resourceURI = null;
				try {
					InputStream in = null;
					IContributor contributor = null;

					String installLocation = pluginModelBase.getInstallLocation();
					if (installLocation != null) {
						IPath path = new Path(installLocation + "/plugin.xml");
						IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
						if (file != null && file.isAccessible()) {
							file.refreshLocal(IResource.DEPTH_ZERO, null);
							in = file.getContents();
							contributor = new ProjectContributor(pluginModelBase.getPluginBase().getId(),
									file.getProject());
						}
					}

					if (contributor == null) {
						BundleDescription bundleDescription = pluginModelBase.getBundleDescription();
						if (bundleDescription != null) {
							Bundle[] bundles = Platform.getBundles(bundleDescription.getSymbolicName(),
									bundleDescription.getVersion().toString());
							if (bundles != null && bundles.length > 0) {
								URL url = bundles[0].getEntry("plugin.xml");
								if (url != null) {
									in = url.openStream();
									contributor = new BundleContributor(bundleDescription.getSymbolicName(), bundles[0]);
								}
							}
						}
					}

					if (contributor != null) {
						resourceURI = URI.createURI(pluginModelBase.getInstallLocation() + "/plugin.xml");
						Resource pluginResource = new GenericXMLResourceFactoryImpl().createResource(resourceURI);
						pluginResource.load(in, null);

						EObject documentRoot = pluginResource.getContents().get(0);
						EObject pluginNode = (EObject) documentRoot
								.eGet(getEReference(documentRoot.eClass(), "plugin"));
						FeatureMap eMap = (FeatureMap) pluginNode.eGet(getEAttribute(pluginNode.eClass(), "any"));
						@SuppressWarnings("unchecked")
						EList<EObject> extensions = (EList<EObject>) eMap.get(
								getEReference(documentRoot.eClass(), "extension"), true);
						for (EObject extension : extensions) {
							if (extensionPointId.equals(extension.eGet(getEAttribute(documentRoot.eClass(), "point")))) {
								for (EObject configurationElementEObject : extension.eContents().toArray(
										new EObject[extension.eContents().size()])) {
									IConfigurationElement configurationElement = new EObjectToIConfigurationElement(
											pluginModelBase, contributor, null, configurationElementEObject);
									configurationElements.add(configurationElement);
								}
							}
						}
					}
				}
				catch (Exception e) {
					System.err.println(resourceURI);
					e.printStackTrace();
				}
			}
			return configurationElements.toArray(new IConfigurationElement[configurationElements.size()]);
		}

		public IContributor getContributor() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public IPluginDescriptor getDeclaringPluginDescriptor() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public IExtension getExtension(String extensionId) throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public IExtension[] getExtensions() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getLabel() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getNamespace() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getNamespaceIdentifier() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getSchemaReference() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getSimpleIdentifier() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getUniqueIdentifier() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public boolean isValid() {
			throw new UnsupportedOperationException();
		}

		public String getLabel(String locale) throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}
	}

	private static class BundleContributor implements IContributor {

		private final String name;
		private final Bundle bundle;

		public BundleContributor(String name, Bundle bundle) {
			this.name = name;
			this.bundle = bundle;
		}

		public String getName() {
			return name;
		}
	}

	private static class ProjectContributor implements IContributor {

		private final String name;
		private final IProject project;

		public ProjectContributor(String name, IProject project) {
			this.name = name;
			this.project = project;
		}

		public String getName() {
			return name;
		}
	}

	private static class EObjectToIConfigurationElement implements IConfigurationElement {

		private final IPluginModelBase pluginModel;
		private final IContributor contributor;
		private final EObjectToIConfigurationElement parent;
		private final EObject eObject;

		public EObjectToIConfigurationElement(IPluginModelBase pluginModel, IContributor contributor,
				EObjectToIConfigurationElement parent, EObject object) {
			this.pluginModel = pluginModel;
			this.contributor = contributor;
			this.parent = parent;
			this.eObject = object;
		}

		public Object createExecutableExtension(String propertyName) throws CoreException {
			throw new UnsupportedOperationException();
		}

		public String getAttribute(String name) throws InvalidRegistryObjectException {
			if (eObject instanceof AnyType) {
				FeatureMap fMap = ((AnyType) eObject).getAnyAttribute();
				for (int i = 0; i < fMap.size(); i++) {
					if (name.equals(fMap.get(i).getEStructuralFeature().getName())) {
						return (String) fMap.getValue(i);
					}
				}
			}
			return null;

		}

		public String getAttributeAsIs(String name) throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String[] getAttributeNames() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException {
			EList<EObject> contents = eObject.eContents();
			IConfigurationElement[] children = new IConfigurationElement[contents.size()];
			for (int i = 0; i < contents.size(); i++) {
				children[i] = new EObjectToIConfigurationElement(pluginModel, contributor, this, contents.get(i));
			}
			return children;
		}

		public IConfigurationElement[] getChildren(String name) throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public IContributor getContributor() throws InvalidRegistryObjectException {
			return contributor;
		}

		public IExtension getDeclaringExtension() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getName() throws InvalidRegistryObjectException {
			if (eObject.eContainmentFeature() != null) {
				return eObject.eContainmentFeature().getName();
			}
			return eObject.eContainingFeature().getName();
		}

		public String getNamespace() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getNamespaceIdentifier() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public Object getParent() throws InvalidRegistryObjectException {
			return parent;
		}

		public String getValue() throws InvalidRegistryObjectException {
			if (eObject instanceof AnyType) {
				AnyType anyType = (AnyType) eObject;
				if (!anyType.getMixed().isEmpty()) {
					return (String) anyType.getMixed().getValue(0);
				}
				else {
					return null;
				}
			}
			throw new UnsupportedOperationException();
		}

		public String getValueAsIs() throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public boolean isValid() {
			return true;
		}

		public String getAttribute(String attrName, String locale) throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}

		public String getValue(String locale) throws InvalidRegistryObjectException {
			throw new UnsupportedOperationException();
		}
	}

	public static IExtensionPoint getExtensionPoint(String extensionPointId, IPluginModelBase[] pluginModelBases) {
		return new IPluginModelBasesToIExtensionPoint(extensionPointId, pluginModelBases);
	}

	public static java.net.URI getResourceURI(IContributor contributor) {
		if (contributor instanceof BundleContributor) {
			try {
				return ((BundleContributor) contributor).bundle.getEntry("/").toURI();
			}
			catch (URISyntaxException e) {
			}
		}
		else if (contributor instanceof ProjectContributor) {
			return ((ProjectContributor) contributor).project.getLocationURI();
		}
		else {
			try {
				return Platform.getBundle(contributor.getName()).getEntry("/").toURI();
			}
			catch (URISyntaxException e) {
			}
		}
		return null;
	}
}
