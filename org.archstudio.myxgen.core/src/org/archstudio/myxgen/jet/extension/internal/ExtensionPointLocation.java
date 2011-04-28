package org.archstudio.myxgen.jet.extension.internal;

import java.net.URL;

import org.archstudio.myxgen.jet.extension.IExtensionPointLocation;
import org.archstudio.myxgen.jet.extension.MyxBrickExtensionUtils;


public abstract class ExtensionPointLocation implements IExtensionPointLocation {

	protected final URL pluginUrl;

	public ExtensionPointLocation(URL pluginUrl) {
		this.pluginUrl = pluginUrl;
	}

	public URL getPluginUrl() {
		return pluginUrl;
	}

	public String getSymbolicName() {
		return MyxBrickExtensionUtils.getPluginSymbolicName(pluginUrl);
	}
}
