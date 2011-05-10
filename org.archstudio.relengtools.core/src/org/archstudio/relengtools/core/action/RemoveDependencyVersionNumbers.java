package org.archstudio.relengtools.core.action;

import static org.archstudio.sysutils.SystemUtils.emptyIfNull;

import java.util.List;

import org.archstudio.eclipse.action.AbstractObjectActionDelegate;
import org.archstudio.relengtools.core.Activator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.eclipse.pde.core.project.IRequiredBundleDescription;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

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
			ServiceReference ref = context.getServiceReference(IBundleProjectService.class.getName());
			IBundleProjectService service = (IBundleProjectService) context.getService(ref);
			IBundleProjectDescription description = service.getDescription(project);
			List<IRequiredBundleDescription> requiredBundles = Lists.newArrayList(emptyIfNull(description
					.getRequiredBundles()));

			List<IRequiredBundleDescription> newRequiredBundles = Lists.newArrayList();
			for (IRequiredBundleDescription b : requiredBundles) {
				newRequiredBundles.add(service.newRequiredBundle(b.getName(), null, b.isOptional(), b.isExported()));
			}

			description.setRequiredBundles(requiredBundles.toArray(new IRequiredBundleDescription[0]));
			description.apply(null);
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
