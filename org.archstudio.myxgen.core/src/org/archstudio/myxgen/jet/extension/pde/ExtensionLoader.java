package org.archstudio.myxgen.jet.extension.pde;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.archstudio.myxgen.jet.extension.IMyxBrickExtension;


/**
 * The ExtensionBrick loader. This class reads the eclipse's extension point and
 * holds the ExtensionBrick.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class ExtensionLoader {

	/**
	 * The eclipse resource URL prefix
	 */
	public static final String EXTENSION_URL_PREFIX = "platform:/resource/";

	/** an empty collection */
	final private static Collection<IMyxBrickExtension> emptyBricks = Collections.unmodifiableCollection(new ArrayList<IMyxBrickExtension>());

	/**
	 * A map such that key = name, value = ExtensionBrick
	 */
	private final Map<String, IMyxBrickExtension> nameBrickMap;

	/**
	 * A map such that key = id, value = ExtensionBrick
	 */
	private final Map<String, IMyxBrickExtension> idBrickMap;

	/**
	 * the plugin id of the extension point
	 */
	private final String pluginId;

	/**
	 * the project name of the extension point
	 */
	private final String projectName;

	ExtensionLoader(Map<String, IMyxBrickExtension> nameBrickMap, Map<String, IMyxBrickExtension> idBrickMap, String pluginId, String projectName) {
		this.nameBrickMap = nameBrickMap;
		this.idBrickMap = idBrickMap;
		this.pluginId = pluginId;
		this.projectName = projectName;
	}

	/**
	 * Returns the plugin id of the extension point
	 * 
	 * @return
	 */
	public String getPluginId() {
		return this.pluginId;
	}

	/**
	 * Returns the project name of the extension point
	 * 
	 * @return
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * Returns the brick of the given name
	 * 
	 * @param name
	 * @return the ExtensionBrick, or null if the specified name is not found.
	 */
	//	public IMyxBrickExtension getExtensionBrickByName(String name) {
	//		if(nameBrickMap != null) {
	//			return nameBrickMap.get(name);
	//		} else {
	//			return null;
	//		}
	//	}
	/**
	 * Returns the brick of the given id
	 * 
	 * @param id
	 * @return the ExtensionBrick, or null if the specified id is not found.
	 */
	public IMyxBrickExtension getExtensionBrickById(String id) {
		if (idBrickMap != null) {
			return idBrickMap.get(id);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the collection of all bricks
	 * 
	 * @return the collection of ExtensionBrick, or an empty collection
	 */
	public Collection<IMyxBrickExtension> getExtensionBricks() {
		if (idBrickMap != null) {
			return new ArrayList<IMyxBrickExtension>(idBrickMap.values());
		}
		else {
			return ExtensionLoader.emptyBricks;
		}
	}

}
