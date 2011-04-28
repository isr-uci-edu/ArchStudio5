package org.archstudio.myxgen.jet.extension;

import java.net.MalformedURLException;
import java.net.URL;

public class MyxBrickExtensionUtils {

	/**
	 * the extension point id through which this class is going to get
	 * information of a new MYX brick (component/connector).
	 */
	public static final String EXTENSION_POINT_ID = "org.archstudio.myxgen.brick";

	public static final String PLATFORM_URL_SCHEME = "platform";

	public static URL toUrl(String urlString) {
		try {
			return new URL(urlString);
		}
		catch (Exception e) {
		}
		return null;
	}

	public static String getPluginSymbolicName(URL url) {
		try {
			if (url != null) {
				// "platform:/plugin/symbolic_name/..."
				// "platform:/resource/symbolic_name/..."
				if (MyxBrickExtensionUtils.PLATFORM_URL_SCHEME.equals(url.getProtocol().toLowerCase())) {
					return url.getPath().split("\\/")[2];
				}
			}
		}
		catch (Exception e) {
		}
		return null;
	}

	/**
	 * Returns the id (after '#' ) from the given extension resource URL string
	 * 
	 * @param extensionUri
	 * @return the extension id, or <code>null</code> if no id exists.
	 */
	public static String getIdFromExtensionURI(URL url) {
		try {
			if (url != null) {
				if (MyxBrickExtensionUtils.PLATFORM_URL_SCHEME.equals(url.getProtocol().toLowerCase())) {
					return url.getRef();
				}
			}
		}
		catch (Exception e) {
		}
		return null;
	}

	public static URL getExtensionPointPluginUrl(IExtensionPointLocation extensionPointLocation) {
		URL url = extensionPointLocation.getExtensionPointUrl();
		if (MyxBrickExtensionUtils.PLATFORM_URL_SCHEME.equals(url.getProtocol().toLowerCase())) {
			if (url.getPath().startsWith("/resource/")) {
				try {
					return new URL(url.getProtocol() + ":/plugin/" + url.getPath().substring(10) + "#" + url.getRef());
				}
				catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return url;
	}

	public static URL getExtensionPointPluginUrl(String pluginSymbolicName, String extensionPointId) {
		try {
			return new URL(MyxBrickExtensionUtils.PLATFORM_URL_SCHEME + ":/plugin/" + pluginSymbolicName + "/plugin.xml"
			        + (extensionPointId == null ? "" : "#" + extensionPointId));
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
