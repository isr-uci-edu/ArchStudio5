package org.archstudio.myxgen.jet.brick;

import java.util.Properties;

import org.archstudio.myx.fw.MyxJavaClassBrickDescription;

/**
 * Information container of for plugin java implementaion
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class MyxPluginJavaClassBrickDescription extends MyxJavaClassBrickDescription {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6139930164328082530L;

	/**
	 * plugin id of the plugin from which the class should be loaded
	 */
	private final String pluginId;

	public MyxPluginJavaClassBrickDescription(Properties properties, String mainBrickClassName, String pluginId) {
		super(mainBrickClassName);

		this.pluginId = pluginId;

	}

	/**
	 * Returns the plugin id of the plugin project where this brick is defined.
	 * 
	 * @return
	 */
	public String getPluginId() {
		return pluginId;
	}

}
