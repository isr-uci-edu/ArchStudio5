package org.archstudio.myxgen.jet.extension;

import java.net.URL;

import org.archstudio.myxgen.jet.extension.internal.MyxBrickExtension;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.RegistryFactory;


public class MyxBrickExtensionFactory {

	/**
	 * Does <b>not</b> set the parent brick extension.
	 * 
	 * @param pluginUrl
	 * @param brickConfigurationElement
	 * @return
	 */
	public static IMyxBrickExtension parseBrickConfigurationElement(URL pluginUrl, IConfigurationElement brickConfigurationElement) {
		return new MyxBrickExtension(pluginUrl, brickConfigurationElement);
	}

	/**
	 * Does set the parent brick extension based on the extension registry
	 * entries.
	 * 
	 * @param pluginUrl
	 * @param brickConfigurationElement
	 * @return
	 */
	public static IMyxBrickExtension parseBrickConfigurationElement(URL brickUrl) {
		IMyxBrickExtension myxBrickExtension = null;
		String pluginSymbolicName = MyxBrickExtensionUtils.getPluginSymbolicName(brickUrl);
		String brickId = MyxBrickExtensionUtils.getIdFromExtensionURI(brickUrl);
		IExtensionPoint extensionPoint = RegistryFactory.getRegistry().getExtensionPoint(MyxBrickExtensionUtils.EXTENSION_POINT_ID);
		for (IConfigurationElement brickConfigurationElement : extensionPoint.getConfigurationElements()) {
			if (pluginSymbolicName.equals(brickConfigurationElement.getContributor().getName()) && brickId.equals(brickConfigurationElement.getAttribute("id"))) {
				myxBrickExtension = MyxBrickExtensionFactory.parseBrickConfigurationElement(brickUrl, brickConfigurationElement);
				if (myxBrickExtension != null && myxBrickExtension.getParentBrickId() != null) {
					myxBrickExtension.setParentBrick(MyxBrickExtensionFactory.parseBrickConfigurationElement(myxBrickExtension.getParentBrickId()));
				}
				break;
			}
		}
		return myxBrickExtension;
	}

	private static IMyxBrickExtension parseBrickConfigurationElement(String brickId) {
		IMyxBrickExtension myxBrickExtension = null;
		IExtensionPoint extensionPoint = RegistryFactory.getRegistry().getExtensionPoint(MyxBrickExtensionUtils.EXTENSION_POINT_ID);
		for (IConfigurationElement brickConfigurationElement : extensionPoint.getConfigurationElements()) {
			if (brickId.equals(brickConfigurationElement.getAttribute("id"))) {
				URL pluginUrl = MyxBrickExtensionUtils.getExtensionPointPluginUrl(brickConfigurationElement.getContributor().getName(), null);
				myxBrickExtension = MyxBrickExtensionFactory.parseBrickConfigurationElement(pluginUrl, brickConfigurationElement);
				if (myxBrickExtension != null && myxBrickExtension.getParentBrickId() != null) {
					myxBrickExtension.setParentBrick(MyxBrickExtensionFactory.parseBrickConfigurationElement(myxBrickExtension.getParentBrickId()));
				}
				break;
			}
		}
		return myxBrickExtension;
	}
}
