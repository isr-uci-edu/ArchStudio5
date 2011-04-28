package org.archstudio.myxgen.jet.extension;

import java.net.URL;

/**
 * Eclipse Extension Point information such as project name and plugin id.
 * 
 * @author Nobu Takeo [nobu.takeo@gmail.com, nobu.takeo@uci.edu]
 */
public interface IExtensionPointLocation {

	///**
	// * Returns the project name where the brick extension point is defined as
	// * the eclipse's extension point
	// * 
	// * @return the srcProjectName
	// */
	//public String getSrcProjectName();
	//
	///**
	// * Returns the plugin id where this brick extension point is defined as the
	// * eclipse's extension point
	// * 
	// * @return the pluginId
	// */
	//public String getPluginId();

	/**
	 * Returns the actual URL of the resource or bundle containing the extension
	 * point.
	 */
	public URL getPluginUrl();

	/**
	 * Returns the URL of the extension point.
	 */
	public URL getExtensionPointUrl();

	/**
	 * Returns the symbolic name of the extension.
	 */
	public String getSymbolicName();
}
