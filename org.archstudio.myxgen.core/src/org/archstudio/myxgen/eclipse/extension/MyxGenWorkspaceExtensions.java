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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class MyxGenWorkspaceExtensions {

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
				synchronized (workspaceCache) {
					workspaceCache.clear();
					projectCache.clear();
					workspaceCacheNeedsRefresh = true;
				}
			}

		}, MyxGenExtensions.EXTENSION_POINT_ID);
	}

	private static void checkWorkspaceCache() {
		if (workspaceCacheNeedsRefresh) {
			for (IConfigurationElement c : workspaceRegistry.getConfigurationElementsFor(
					MyxGenExtensions.EXTENSION_POINT_NAMESPACE, MyxGenExtensions.EXTENSION_POINT_NAME)) {
				MyxGenBrick b = new MyxGenBrick(c);
				workspaceCache.put(b.getId(), b);
				projectCache.put(c.getContributor().getName(), b);
			}
			workspaceCacheNeedsRefresh = false;
		}
	}

	public static MyxGenBrick getActiveMyxGenBrick(String myxGenBrickId) {
		synchronized (workspaceCache) {
			checkWorkspaceCache();
			return workspaceCache.get(myxGenBrickId);
		}
	}

	public static Collection<MyxGenBrick> getMyxGenBricks(IProject project) {
		synchronized (workspaceCache) {
			checkWorkspaceCache();
			return Lists.newArrayList(projectCache.get(project.getName()));
		}
	}

	public static Multimap<String, MyxGenBrick> getActiveMyxGenBricks() {
		Multimap<String, MyxGenBrick> myxGenBricks = ArrayListMultimap.create();
		synchronized (workspaceCache) {
			checkWorkspaceCache();
			myxGenBricks.putAll(projectCache);
		}
		return myxGenBricks;
	}
}
