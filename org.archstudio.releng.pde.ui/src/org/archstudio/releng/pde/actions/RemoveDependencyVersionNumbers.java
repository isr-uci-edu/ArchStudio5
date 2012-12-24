package org.archstudio.releng.pde.actions;

import java.util.List;
import java.util.Set;

import org.archstudio.eclipse.ui.actions.AbstractObjectActionDelegate;
import org.archstudio.releng.pde.ui.Activator;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.utils.osgi.OSGiUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.eclipse.pde.core.project.IRequiredBundleDescription;
import org.eclipse.pde.internal.core.feature.WorkspaceFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureImport;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@SuppressWarnings("restriction")
public class RemoveDependencyVersionNumbers extends AbstractObjectActionDelegate {

	public RemoveDependencyVersionNumbers() {
	}

	@Override
	public void run(IAction action) {
		for (IProject project : getProjects(selection)) {
			run(action, project);
		}
	}

	private void run(IAction action, IProject project) {
		try {
			// remove dependencies in the MANIFEST.MF file
			if (project.getFile("META-INF/MANIFEST.MF").exists()) {
				BundleContext context = Activator.getSingleton().getContext();
				IBundleProjectService service = OSGiUtils.getServiceReference(context, IBundleProjectService.class);
				IBundleProjectDescription description = service.getDescription(project);
				List<IRequiredBundleDescription> requiredBundles = Lists.newArrayList(SystemUtils
						.emptyIfNull(description.getRequiredBundles()));

				List<IRequiredBundleDescription> newRequiredBundles = Lists.newArrayList();
				for (IRequiredBundleDescription b : requiredBundles) {
					newRequiredBundles
							.add(service.newRequiredBundle(b.getName(), null, b.isOptional(), b.isExported()));
				}

				// the old bundles aren't getting removed, we force this by clearing the corresponding header name
				description.setHeader("Require-Bundle", null);
				description.setRequiredBundles(null);
				description.apply(null);

				// we start with a fresh bundle because we cannot unclear the header name
				description = service.getDescription(project);
				description.setRequiredBundles(newRequiredBundles.toArray(new IRequiredBundleDescription[0]));
				description.apply(null);
			}

			// remove dependency version numbers in the feature.xml file
			if (project.getFile("feature.xml").exists()) {
				WorkspaceFeatureModel featureModel = new WorkspaceFeatureModel(project.getFile("feature.xml"));
				featureModel.load();
				IFeature feature = featureModel.getFeature();

				Set<String> pluginIDs = Sets.newHashSet();
				for (IFeaturePlugin featurePlugin : feature.getPlugins()) {
					pluginIDs.add(featurePlugin.getId());
				}
				Set<String> featureIDs = Sets.newHashSet();
				List<IFeatureImport> duplicates = Lists.newArrayList();
				for (IFeatureImport featureImport : feature.getImports()) {
					featureImport.setVersion(null);
					featureImport.setMatch(0);
					if (!featureIDs.add(featureImport.getId()) || pluginIDs.contains(featureImport.getId())) {
						duplicates.add(featureImport);
					}
				}
				feature.removeImports(duplicates.toArray(new IFeatureImport[0]));

				featureModel.save();
			}
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
