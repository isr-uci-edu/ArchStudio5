package org.archstudio.releng.pde.actions;

import static org.archstudio.sysutils.SystemUtils.emptyIfNull;

import java.util.List;

import org.archstudio.eclipse.ui.actions.AbstractObjectActionDelegate;
import org.archstudio.releng.pde.Activator;
import org.archstudio.utils.osgi.OSGiUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.eclipse.pde.core.project.IRequiredBundleDescription;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Lists;

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
			BundleContext context = Activator.getSingleton().getContext();
			IBundleProjectService service = OSGiUtils.getServiceReference(context, IBundleProjectService.class);
			IBundleProjectDescription description = service.getDescription(project);
			List<IRequiredBundleDescription> requiredBundles = Lists.newArrayList(emptyIfNull(description
					.getRequiredBundles()));

			List<IRequiredBundleDescription> newRequiredBundles = Lists.newArrayList();
			for (IRequiredBundleDescription b : requiredBundles) {
				newRequiredBundles.add(service.newRequiredBundle(b.getName(), null, b.isOptional(), b.isExported()));
			}

			// the old bundles aren't getting removed, we force this by clearing the corresponding header name
			description.setHeader("Require-Bundle", null);
			description.setRequiredBundles(null);
			description.apply(null);

			// the hack above requires that we start with a fresh bundle, because we cannot unclear the header name
			description = service.getDescription(project);
			description.setRequiredBundles(newRequiredBundles.toArray(new IRequiredBundleDescription[0]));
			description.apply(null);
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
