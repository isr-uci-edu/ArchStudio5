package org.archstudio.releng.pde.actions;

import java.util.Arrays;

import org.archstudio.eclipse.ui.actions.AbstractObjectActionDelegate;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.pde.internal.core.feature.WorkspaceFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureImport;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

@SuppressWarnings("restriction")
public class AddRequiredWorkspacePlugins extends AbstractObjectActionDelegate {

	public AddRequiredWorkspacePlugins() {
	}

	@Override
	public void run(IAction action) {
		for (IProject project : getProjects(selection)) {
			run(action, project);
		}
	}

	private void run(IAction action, IProject project) {
		try {
			IFile featureFile = project.getFile("feature.xml");
			if (featureFile.exists()) {
				WorkspaceFeatureModel featureModel = new WorkspaceFeatureModel(featureFile);
				featureModel.load();
				IFeature feature = featureModel.getFeature();

				Multimap<String, IFeaturePlugin> pluginMultimap = Multimaps.index(Arrays.asList(feature.getPlugins()),
						new Function<IFeaturePlugin, String>() {

							@Override
							public String apply(IFeaturePlugin input) {
								return input.getId();
							}
						});
				for (IFeatureImport featureImport : feature.getImports()) {
					String id = featureImport.getId();
					if (!pluginMultimap.containsKey(id)) {
						IProject projectImport = project.getWorkspace().getRoot().getProject(id);
						if (projectImport.exists()) {
							IFeaturePlugin pluginImport = featureModel.getFactory().createPlugin();
							pluginImport.setVersion("0.0.0");
							pluginImport.setId(id);
							feature.addPlugins(new IFeaturePlugin[] { pluginImport });
						}
					}
				}
				featureModel.save();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
