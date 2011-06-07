package org.archstudio.myxgen.extension;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;

public class MyxGenExtensions {

	public static final String EXTENSION_POINT_ID = "org.archstudio.myxgen.brick";
	public static final String EXTENSION_POINT_NAMESPACE = EXTENSION_POINT_ID.substring(0,
			EXTENSION_POINT_ID.lastIndexOf('.'));
	public static final String EXTENSION_POINT_NAME = EXTENSION_POINT_ID
			.substring(EXTENSION_POINT_ID.lastIndexOf('.') + 1);

	final static private IExtensionRegistry pluginRegistry = RegistryFactory.getRegistry();
	final static private Map<String, MyxGenBrick> pluginMyxGenBricks = new MapMaker()
			.makeComputingMap(new Function<String, MyxGenBrick>() {
				@Override
				public MyxGenBrick apply(String myxGenBrickID) {
					for (IConfigurationElement c : pluginRegistry.getConfigurationElementsFor(
							EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME, myxGenBrickID)) {
						return new MyxGenBrick(c);
					}
					return null;
				}
			});

	static {
		pluginRegistry.addListener(new IRegistryEventListener() {

			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
				pluginMyxGenBricks.clear();
			}

			@Override
			public void removed(IExtension[] extensions) {
				pluginMyxGenBricks.clear();
			}

			@Override
			public void added(IExtensionPoint[] extensionPoints) {
				pluginMyxGenBricks.clear();
			}

			@Override
			public void added(IExtension[] extensions) {
				pluginMyxGenBricks.clear();
			}
		}, EXTENSION_POINT_ID);
	}

	private static final IExtensionRegistry workspaceRegistry = new WorkspaceExtensionRegistry();
	private static final Map<String, MyxGenBrick> workspaceMyxGenBricks = new MapMaker()
			.makeComputingMap(new Function<String, MyxGenBrick>() {
				@Override
				public MyxGenBrick apply(String myxGenBrickID) {
					for (IConfigurationElement c : workspaceRegistry.getConfigurationElementsFor(
							EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME, myxGenBrickID)) {
						return new MyxGenBrick(c);
					}
					return null;
				}
			});

	static {
		workspaceRegistry.addListener(new IRegistryEventListener() {

			@Override
			public void removed(IExtensionPoint[] extensionPoints) {
				workspaceMyxGenBricks.clear();
			}

			@Override
			public void removed(IExtension[] extensions) {
				workspaceMyxGenBricks.clear();
			}

			@Override
			public void added(IExtensionPoint[] extensionPoints) {
				workspaceMyxGenBricks.clear();
			}

			@Override
			public void added(IExtension[] extensions) {
				workspaceMyxGenBricks.clear();
			}
		}, EXTENSION_POINT_ID);
	}

	public static MyxGenBrick getExtenalMyxGenBrick(String myxGenBrickId) {
		return pluginMyxGenBricks.get(myxGenBrickId);
	}

	public static MyxGenBrick getWorkspaceMyxGenBrick(String myxGenBrickId) {
		return workspaceMyxGenBricks.get(myxGenBrickId);
	}

	public static MyxGenBrick getActiveMyxGenBrick(String myxGenBrickId) {
		MyxGenBrick myxGenBrick = getWorkspaceMyxGenBrick(myxGenBrickId);
		if (myxGenBrick == null)
			myxGenBrick = getExtenalMyxGenBrick(myxGenBrickId);
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
				return workspaceMyxGenBricks.get(input.getAttribute("id"));
			}
		});
	}
}
