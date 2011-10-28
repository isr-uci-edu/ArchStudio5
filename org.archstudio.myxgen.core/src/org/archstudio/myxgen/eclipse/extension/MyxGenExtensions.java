package org.archstudio.myxgen.eclipse.extension;

import java.util.Arrays;

import org.archstudio.myxgen.MyxGenBrick;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Iterables;

public class MyxGenExtensions {

	public static final String EXTENSION_POINT_ID = "org.archstudio.myxgen.brick";
	public static final String EXTENSION_POINT_NAMESPACE = EXTENSION_POINT_ID.substring(0,
			EXTENSION_POINT_ID.lastIndexOf('.'));
	public static final String EXTENSION_POINT_NAME = EXTENSION_POINT_ID
			.substring(EXTENSION_POINT_ID.lastIndexOf('.') + 1);

	final static private IExtensionRegistry pluginRegistry = RegistryFactory.getRegistry();
	final static private Cache<String, MyxGenBrick> pluginMyxGenBricksCache = CacheBuilder.newBuilder().build(
			new CacheLoader<String, MyxGenBrick>() {
				@Override
				public MyxGenBrick load(String myxGenBrickID) {
					for (IConfigurationElement c : workspaceRegistry.getConfigurationElementsFor(
							EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME)) {
						if (myxGenBrickID.equals(c.getAttribute("id"))) {
							return new MyxGenBrick(c);
						}
					}
					return null;
				}
			});

	static {
		pluginRegistry.addListener(new IRegistryEventListener() {

			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
				pluginMyxGenBricksCache.invalidateAll();
			}

			@Override
			public void removed(IExtension[] extensions) {
				pluginMyxGenBricksCache.invalidateAll();
			}

			@Override
			public void added(IExtensionPoint[] extensionPoints) {
				pluginMyxGenBricksCache.invalidateAll();
			}

			@Override
			public void added(IExtension[] extensions) {
				pluginMyxGenBricksCache.invalidateAll();
			}
		}, EXTENSION_POINT_ID);
	}

	private static final IExtensionRegistry workspaceRegistry = new WorkspaceExtensionRegistry();
	private static final Cache<String, MyxGenBrick> workspaceMyxGenBricksCache = CacheBuilder.newBuilder().build(
			new CacheLoader<String, MyxGenBrick>() {

				@Override
				public MyxGenBrick load(String myxGenBrickID) throws Exception {
					for (IConfigurationElement c : workspaceRegistry.getConfigurationElementsFor(
							EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME)) {
						if (myxGenBrickID.equals(c.getAttribute("id"))) {
							return new MyxGenBrick(c);
						}
					}
					return null;
				}
			});

	static {
		workspaceRegistry.addListener(new IRegistryEventListener() {

			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
				workspaceMyxGenBricksCache.invalidateAll();
			}

			@Override
			public void removed(IExtension[] extensions) {
				workspaceMyxGenBricksCache.invalidateAll();
			}

			@Override
			public void added(IExtensionPoint[] extensionPoints) {
				workspaceMyxGenBricksCache.invalidateAll();
			}

			@Override
			public void added(IExtension[] extensions) {
				workspaceMyxGenBricksCache.invalidateAll();
			}

		}, EXTENSION_POINT_ID);
	}

	public static MyxGenBrick getExtenalMyxGenBrick(String myxGenBrickId) {
		return pluginMyxGenBricksCache.getUnchecked(myxGenBrickId);
	}

	public static MyxGenBrick getWorkspaceMyxGenBrick(String myxGenBrickId) {
		return workspaceMyxGenBricksCache.getUnchecked(myxGenBrickId);
	}

	public static MyxGenBrick getActiveMyxGenBrick(String myxGenBrickId) {
		MyxGenBrick myxGenBrick = null;
		try {
			myxGenBrick = getWorkspaceMyxGenBrick(myxGenBrickId);
		}
		catch (NullPointerException e) {
			// ignore: thrown if non-existent in the workspace
		}
		if (myxGenBrick == null) {
			myxGenBrick = getExtenalMyxGenBrick(myxGenBrickId);
		}
		return myxGenBrick;
	}

	public static Iterable<MyxGenBrick> getWorkspaceMyxGenBricks(final IProject project) {
		Iterable<IConfigurationElement> allConfigurationElements = Arrays.asList(workspaceRegistry
				.getConfigurationElementsFor(EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME));
		Iterable<IConfigurationElement> configurationElementsInProject = Iterables.filter(allConfigurationElements,
				new Predicate<IConfigurationElement>() {
					@Override
					public boolean apply(IConfigurationElement input) {
						return input.getContributor().getName().equals(project.getName());
					}
				});
		return Iterables.transform(configurationElementsInProject, new Function<IConfigurationElement, MyxGenBrick>() {
			@Override
			public MyxGenBrick apply(IConfigurationElement input) {
				return workspaceMyxGenBricksCache.getUnchecked(input.getAttribute("id"));
			}
		});
	}
}
