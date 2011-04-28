package org.archstudio.myxgen.jet.extension.pde;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.myxgen.jet.extension.IInterface;
import org.archstudio.myxgen.jet.extension.IMyxBrickExtension;
import org.archstudio.myxgen.jet.extension.MyxBrickExtensionFactory;
import org.archstudio.myxgen.jet.extension.MyxBrickExtensionUtils;
import org.archstudio.myxgen.jet.util.PDEUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;


/**
 * A utility class for ExtensionLoader
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class ExtensionLoaderUtil {

	/**
	 * Creates an extension point loader of the given plugin model.
	 * 
	 * @param pluginModel
	 */
	public static ExtensionLoader getExtensionLoader(IPluginModelBase pluginModel) {
		assert pluginModel != null;

		return ExtensionLoaderUtil.createLoader(pluginModel);
	}

	/**
	 * Creates an extension point loader of the given project.
	 * 
	 * @param project
	 */
	public static ExtensionLoader getExtensionLoader(IProject project) {
		assert project != null;

		IPluginModelBase pluginModel = PluginRegistry.findModel(project);
		if (pluginModel == null) {
			throw new IllegalArgumentException("pluginModel for the project \"" + project.getName()
					+ "\" is not found.");
		}
		return ExtensionLoaderUtil.createLoader(pluginModel);
	}

	/**
	 * Loads extension brick info
	 * 
	 * @param pluginModel
	 */
	private static ExtensionLoader createLoader(IPluginModelBase pluginModel) {
		assert pluginModel != null;

		//String projectName = pluginModel.getUnderlyingResource().getProject().getName();
		String pluginSymbolicName = pluginModel.getBundleDescription().getSymbolicName();
		String projectName = pluginSymbolicName;
		Collection<IMyxBrickExtension> bricks = ExtensionLoaderUtil.loadBricks(pluginModel);
		Map<String, IMyxBrickExtension> idBrickMap = ExtensionLoaderUtil.createIdBrickMap(bricks);
		Map<String, IMyxBrickExtension> nameBrickMap = ExtensionLoaderUtil.createNameBrickMap(idBrickMap.values());
		//updateBrickIInterfaces(this.idBrickMap);

		//looks at parent bricks
		ExtensionLoaderUtil.updateBrickParents(idBrickMap);

		return new ExtensionLoader(nameBrickMap, idBrickMap, pluginSymbolicName, projectName);

	}

	private static Map<String, IMyxBrickExtension> createNameBrickMap(Collection<IMyxBrickExtension> bricks) {
		Map<String, IMyxBrickExtension> nameBrickMap = new HashMap<String, IMyxBrickExtension>();
		for (IMyxBrickExtension brick : bricks) {
			nameBrickMap.put(brick.getName(), brick);
		}
		return nameBrickMap;
	}

	private static Map<String, IMyxBrickExtension> createIdBrickMap(Collection<IMyxBrickExtension> bricks) {
		Map<String, IMyxBrickExtension> idBrickMap = new HashMap<String, IMyxBrickExtension>();
		for (IMyxBrickExtension brick : bricks) {
			idBrickMap.put(brick.getId(), brick);
		}
		return idBrickMap;
	}

	/**
	 * Returns a list of Brick objects. This method reads eclipse's extension registry of the specified pluginModel and
	 * load the Bricks into the map.
	 * 
	 * @param pluginModel
	 *            the target pluginModel where the extension point is defined
	 * @return
	 */
	static Collection<IMyxBrickExtension> loadBricks(IPluginModelBase pluginModel) {
		assert pluginModel != null;

		Map<String, IMyxBrickExtension> idBrickMap = new HashMap<String, IMyxBrickExtension>();

		String projectName = pluginModel.getUnderlyingResource().getProject().getName();

		// gets the extensionPoint of the given javaProject

		if (pluginModel != null) {
			IExtensionPoint extensionPoint = PDEUtils.getExtensionPoint(MyxBrickExtensionUtils.EXTENSION_POINT_ID,
					new IPluginModelBase[] { pluginModel });

			// reads bricks from the extensionPoint
			IConfigurationElement[] ces = extensionPoint.getConfigurationElements();
			for (IConfigurationElement element : ces) {

				if (!IMyxBrickExtension.ELEMENT_NAME.equals(element.getName())) {
					// ignoring top elements that are not "brick"
					continue;
				}

				String pluginId = element.getContributor().getName();
				URL pluginUrl;
				try {
					pluginUrl = new URL("platform:/plugin/" + pluginId + "/plugin.xml");
				}
				catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
				IMyxBrickExtension brick = MyxBrickExtensionFactory.parseBrickConfigurationElement(pluginUrl, element);
				//brick.setSrcProjectName(projectName);
				idBrickMap.put(brick.getId(), brick);
			}
		}

		return idBrickMap.values();

	}

	/**
	 * Returns a collection of plugin models where bricks are defined.
	 * 
	 * @return
	 */
	static Collection<IPluginModelBase> getAllExtensionPointPluginModels() {

		Collection<IPluginModelBase> pluginModels = new ArrayList<IPluginModelBase>();

		/*
		 * This iterates through all workspace and target plugins (see: Window | Preferences... | Plug-in Development |
		 * Target Platform )
		 */
		PROJECT: for (IPluginModelBase pluginModel : PluginRegistry.getActiveModels()) {
			if (!pluginModel.isEnabled() || !pluginModel.isValid()) {
				continue;
			}

			//			IProject project = ResourcesPlugin.getWorkspace().getRoot()
			//			.getProject(pluginModel.getBundleDescription().getName());
			IResource resource = pluginModel.getUnderlyingResource();
			if (resource == null) {
				continue;
			}
			IProject project = resource.getProject();
			if (project == null) {
				continue;
			}

			for (IPluginExtension pluginExtension : pluginModel.getPluginBase().getExtensions()) {
				String crntExtPoint = pluginExtension.getPoint();
				//if(!crntExtPoint.startsWith("org")) {
				//	System.out.println(crntExtPoint);
				//}
				if (!MyxBrickExtensionUtils.EXTENSION_POINT_ID.equals(crntExtPoint)) {
					continue;
				}

				if (!project.isAccessible()) {
					try {
						project.open(new NullProgressMonitor());
					}
					catch (CoreException e) {
						e.printStackTrace();
						break PROJECT;
					}
				}

				pluginModels.add(pluginModel);
			}
		}

		return pluginModels;
	}

	/**
	 * Updates the bricks' parentBrick. Through this each brick has a reference to its parentBrick.
	 * 
	 * @param idBrickMap
	 */
	static void updateBrickParents(Map<String, IMyxBrickExtension> idBrickMap) {
		Map<String, List<IMyxBrickExtension>> brickIdAncestorsMap = ExtensionLoaderUtil
				.createBrickIdAncestorsMap(idBrickMap);

		//a set of brick ids in order to reduce the redundant calculation
		Set<String> updatedBrickIds = new HashSet<String>();

		ANCESTORS: for (Entry<String, List<IMyxBrickExtension>> entry : brickIdAncestorsMap.entrySet()) {

			List<IMyxBrickExtension> ancestors = entry.getValue();
			Iterator<IMyxBrickExtension> it = ancestors.iterator();
			IMyxBrickExtension currentBrick = it.next();

			while (it.hasNext()) {
				IMyxBrickExtension parentBrick = it.next();
				if (updatedBrickIds.contains(currentBrick.getId())) {
					continue ANCESTORS;
				}
				//sets the parent
				currentBrick.setParentBrick(parentBrick);
				updatedBrickIds.add(currentBrick.getId());
				currentBrick = parentBrick;
			}

			updatedBrickIds.add(currentBrick.getId());
		}
	}

	/**
	 * Creates a map where the key is the brick id and the value is the collection of all the brick interfaces including
	 * those of the bricks ancestors from the given idBrickMap
	 * 
	 * @param idBrickMap
	 * @return
	 */
	static Map<String, Collection<IInterface>> getBrickIdIInterfacesMap(Map<String, IMyxBrickExtension> idBrickMap) {

		Map<String, Collection<IInterface>> brickIdIInterfacesMap = new HashMap<String, Collection<IInterface>>();

		//creates brickId-ancestors map
		Map<String, List<IMyxBrickExtension>> brickIdAncestorsMap = ExtensionLoaderUtil
				.createBrickIdAncestorsMap(idBrickMap);

		for (Entry<String, List<IMyxBrickExtension>> entry : brickIdAncestorsMap.entrySet()) {

			String brickId = entry.getKey();
			List<IMyxBrickExtension> ancestors = entry.getValue();

			//looks for all the interfaces of the ancestors
			//if the interfaces that have the same name are found,
			//younger generation is used just like class extension 
			Map<String, IInterface> intfMap = new HashMap<String, IInterface>();
			//reverses the ancestors list (from older to younger order)
			Collections.reverse(ancestors);
			for (IMyxBrickExtension ancestor : ancestors) {
				for (IInterface intf : ancestor.getInterfaces()) {
					//put or overwrite by the newer IInterface
					intfMap.put(intf.getName(), intf);
				}
			}

			brickIdIInterfacesMap.put(brickId, intfMap.values());
		}

		return brickIdIInterfacesMap;
	}

	/**
	 * Creates a map where the key is the brick id and the value is the collection of all the brick interfaces including
	 * those of the bricks ancestors from the given idBrickMap
	 * 
	 * @param idBrickMap
	 * @return
	 */
	static Map<String, List<IMyxBrickExtension>> createBrickIdAncestorsMap(Map<String, IMyxBrickExtension> idBrickMap) {
		//search scope of the bricks
		Map<String, IMyxBrickExtension> searcScopeIdBrickMap = new HashMap<String, IMyxBrickExtension>();
		if (!ExtensionLoaderUtil.isSelfContained(idBrickMap)) {

			// looks at all the extension points available
			for (IPluginModelBase pluginModel : ExtensionLoaderUtil.getAllExtensionPointPluginModels()) {
				for (IMyxBrickExtension brick : ExtensionLoaderUtil.loadBricks(pluginModel)) {
					searcScopeIdBrickMap.put(brick.getId(), brick);
				}

			}
		}
		searcScopeIdBrickMap.putAll(idBrickMap);

		Map<String, List<IMyxBrickExtension>> brickIdAncestorsMap = new HashMap<String, List<IMyxBrickExtension>>();
		//loop detection size
		int stopLevel = searcScopeIdBrickMap.size();
		for (IMyxBrickExtension brick : idBrickMap.values()) {
			//looks for ancestors. this list includes the brick itself
			List<IMyxBrickExtension> ancestors = new ArrayList<IMyxBrickExtension>();
			ExtensionLoaderUtil.searchAncestors(brick.getId(), ancestors, stopLevel, searcScopeIdBrickMap);

			brickIdAncestorsMap.put(brick.getId(), ancestors);
		}

		return brickIdAncestorsMap;
	}

	/**
	 * Returns true if all of the parent bricks are found in the given map
	 * 
	 * @param idBrickMap
	 * @return
	 */
	private static boolean isSelfContained(Map<String, IMyxBrickExtension> idBrickMap) {
		for (IMyxBrickExtension brick : idBrickMap.values()) {
			String parentId = brick.getParentBrickId();
			if (parentId == null) {
				continue;
			}

			if (idBrickMap.get(parentId) == null) {
				//parent id is not found in this map
				return false;
			}
		}
		return true;

	}

	/**
	 * Looks for all the ancestors of the given id from the given allIdBrickMap and update the given ancestors list.
	 * 
	 * @param id
	 * @param ancestors
	 * @param stopLevel
	 * @param allIdBrickMap
	 */
	private static void searchAncestors(String id, List<IMyxBrickExtension> ancestors, int stopLevel,
			Map<String, IMyxBrickExtension> allIdBrickMap) {
		if (ancestors.size() > stopLevel) {
			//probably a cyclic reference 
			throw new StackOverflowError("A cyclic refernece is found while searching parenet extension bricks.");
		}

		if (id == null) {
			return;
		}

		IMyxBrickExtension brick = allIdBrickMap.get(id);
		if (brick == null) {
			return;
		}
		else {
			//adds this brick to the list
			ancestors.add(brick);
			//looks for its ancestor
			ExtensionLoaderUtil.searchAncestors(brick.getParentBrickId(), ancestors, stopLevel, allIdBrickMap);
		}
	}

	/**
	 * Returns the plugin id from the given extension resource URL string
	 * 
	 * @param extensionUri
	 * @return
	 */
	public static String getPluginIdFromExtensionURI(String extensionUri) {
		try {
			URL url = new URL(extensionUri);
			if (MyxBrickExtensionUtils.PLATFORM_URL_SCHEME.equals(url.getProtocol().toLowerCase())) {
				return MyxBrickExtensionUtils.getPluginSymbolicName(url);
			}
		}
		catch (Exception e) {
		}
		return null;
	}

	/**
	 * Returns the plugin model from the given extension resource URL string.
	 * 
	 * @param extensionUri
	 * @return
	 */
	public static IPluginModelBase getPluginModelFromExtensionURI(String extensionUri) {
		String pluginId = ExtensionLoaderUtil.getPluginIdFromExtensionURI(extensionUri);

		if (pluginId != null) {
			IPluginModelBase pluginModel = PluginRegistry.findModel(pluginId);
			return pluginModel;
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the project specified by the name
	 * 
	 * @param projectName
	 * @return
	 */
	public static IProject findProject(String projectName) {
		for (IPluginModelBase pluginModel : PluginRegistry.getActiveModels()) {
			if (!pluginModel.isEnabled() || !pluginModel.isValid()) {
				continue;
			}
			IResource resource = pluginModel.getUnderlyingResource();
			if (resource == null) {
				continue;
			}
			IProject project = resource.getProject();
			if (project != null) {
				if (project.getName().equals(projectName)) {
					//found the project
					return project;
				}
			}
		}
		return null;
	}

	/**
	 * Reads all the extension points of the workspace and returns a collection of ExtensionLoaders
	 * 
	 * @return
	 */
	public static Collection<ExtensionLoader> getAllExtensionLoaders() {

		Collection<ExtensionLoader> loaders = new ArrayList<ExtensionLoader>();
		for (IPluginModelBase pluginModel : ExtensionLoaderUtil.getAllExtensionPointPluginModels()) {
			//reads brick info from the extension point 
			ExtensionLoader loader = ExtensionLoaderUtil.createLoader(pluginModel);
			loaders.add(loader);
		}

		return loaders;
	}
}
