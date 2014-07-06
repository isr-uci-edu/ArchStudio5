package org.archstudio.bna.builder;

import java.util.Map;
import java.util.Set;

import org.archstudio.utils.eclipse.PDEUtils;
import org.archstudio.utils.eclipse.WorkspaceExtensionRegistry;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ThingIncrementalProjectBuilder extends IncrementalProjectBuilder {

	private static final IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
	private static final IExtensionRegistry workspaceRegistry = new WorkspaceExtensionRegistry();

	public ThingIncrementalProjectBuilder() {
	}

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {

		IResourceDelta delta = getDelta(getProject());
		if (delta != null) {
			IResourceDelta pluginDelta = delta.findMember(getProject().getFile("plugin.xml").getProjectRelativePath());
			if (pluginDelta == null) {
				return null;
			}
		}

		Set<String> bundles = Sets.newHashSet();
		Map<String, IConfigurationElement> facets = Maps.newHashMap();
		Map<String, IConfigurationElement> things = Maps.newHashMap();

		for (IConfigurationElement c : workspaceRegistry
				.getConfigurationElementsFor("org.archstudio.bna.thingdefinition")) {
			bundles.add(c.getContributor().getName());
			for (IConfigurationElement f : PDEUtils.getConfigurationElements(c, "Facet")) {
				facets.put(f.getAttribute("name"), f);
			}
			for (IConfigurationElement t : PDEUtils.getConfigurationElements(c, "Thing")) {
				things.put(t.getAttribute("name"), t);
			}
		}

		for (IConfigurationElement c : pluginRegistry.getConfigurationElementsFor("org.archstudio.bna.thingdefinition")) {
			if (!bundles.contains(c.getContributor().getName())) {
				for (IConfigurationElement f : PDEUtils.getConfigurationElements(c, "Facet")) {
					facets.put(f.getAttribute("name"), f);
				}
				for (IConfigurationElement t : PDEUtils.getConfigurationElements(c, "Thing")) {
					things.put(t.getAttribute("name"), t);
				}
			}
		}

		for (IConfigurationElement c : workspaceRegistry
				.getConfigurationElementsFor("org.archstudio.bna.thingdefinition")) {
			if (c.getContributor().getName().equals(getProject().getName())) {
				Generator.generatePackage(getProject(), new Mappings(facets, things), c);
			}
		}

		return null;
	}
}
