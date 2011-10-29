package org.archstudio.myxgen.eclipse.extension;

import java.util.Collection;
import java.util.Map;

import org.archstudio.myxgen.MyxGenBrick;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class MyxGenExtensions {

	public static final String EXTENSION_POINT_ID = "org.archstudio.myxgen.brick";
	public static final String EXTENSION_POINT_NAMESPACE = EXTENSION_POINT_ID.substring(0,
			EXTENSION_POINT_ID.lastIndexOf('.'));
	public static final String EXTENSION_POINT_NAME = EXTENSION_POINT_ID
			.substring(EXTENSION_POINT_ID.lastIndexOf('.') + 1);

	private static boolean pluginCacheNeedsRefresh = true;
	private final static Map<String, MyxGenBrick> pluginCache = Maps.newHashMap();
	private final static IExtensionRegistry pluginRegistry = RegistryFactory.getRegistry();
	static {
		pluginRegistry.addListener(new IRegistryEventListener() {

			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
				clear();
			}

			@Override
			public void removed(IExtension[] extensions) {
				clear();
			}

			@Override
			public void added(IExtensionPoint[] extensionPoints) {
				clear();
			}

			@Override
			public void added(IExtension[] extensions) {
				clear();
			}

			public void clear() {
				synchronized (pluginCache) {
					pluginCache.clear();
					pluginCacheNeedsRefresh = true;
				}
			}

		}, EXTENSION_POINT_ID);
	}

	private static void checkPluginCache() {
		if (pluginCacheNeedsRefresh) {
			for (IConfigurationElement c : pluginRegistry.getConfigurationElementsFor(EXTENSION_POINT_NAMESPACE,
					EXTENSION_POINT_NAME)) {
				MyxGenBrick b = new MyxGenBrick(c);
				pluginCache.put(b.getId(), b);
			}
			pluginCacheNeedsRefresh = false;
		}
	}

	private static boolean workspaceCacheNeedsRefresh = true;
	private static final Map<String, MyxGenBrick> workspaceCache = Maps.newHashMap();
	private static final Multimap<String, MyxGenBrick> projectCache = ArrayListMultimap.create();
	private static final IExtensionRegistry workspaceRegistry = new WorkspaceExtensionRegistry();
	static {
		workspaceRegistry.addListener(new IRegistryEventListener() {

			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
				clear();
			}

			@Override
			public void removed(IExtension[] extensions) {
				clear();
			}

			@Override
			public void added(IExtensionPoint[] extensionPoints) {
				clear();
			}

			@Override
			public void added(IExtension[] extensions) {
				clear();
			}

			public void clear() {
				synchronized (pluginCache) {
					workspaceCache.clear();
					projectCache.clear();
					workspaceCacheNeedsRefresh = true;
				}
			}

		}, EXTENSION_POINT_ID);
	}

	private static void checkWorkspaceCache() {
		if (workspaceCacheNeedsRefresh) {
			for (IConfigurationElement c : workspaceRegistry.getConfigurationElementsFor(EXTENSION_POINT_NAMESPACE,
					EXTENSION_POINT_NAME)) {
				MyxGenBrick b = new MyxGenBrick(c);
				workspaceCache.put(b.getId(), b);
				projectCache.put(c.getContributor().getName(), b);
			}
			workspaceCacheNeedsRefresh = false;
		}
	}

	public static MyxGenBrick getExtenalMyxGenBrick(String myxGenBrickId) {
		synchronized (pluginCache) {
			checkPluginCache();
			return pluginCache.get(myxGenBrickId);
		}
	}

	public static MyxGenBrick getWorkspaceMyxGenBrick(String myxGenBrickId) {
		synchronized (workspaceCache) {
			checkWorkspaceCache();
			return workspaceCache.get(myxGenBrickId);
		}
	}

	public static MyxGenBrick getActiveMyxGenBrick(String myxGenBrickId) {
		synchronized (workspaceCache) {
			checkWorkspaceCache();
			MyxGenBrick myxGenBrick = workspaceCache.get(myxGenBrickId);
			if (myxGenBrick != null) {
				return myxGenBrick;
			}
		}
		synchronized (pluginCache) {
			checkPluginCache();
			MyxGenBrick myxGenBrick = pluginCache.get(myxGenBrickId);
			if (myxGenBrick != null) {
				return myxGenBrick;
			}
		}
		return null;
	}

	public static Collection<MyxGenBrick> getWorkspaceMyxGenBricks(final IProject project) {
		synchronized (workspaceCache) {
			checkWorkspaceCache();
			return Lists.newArrayList(projectCache.get(project.getName()));
		}
	}
}
