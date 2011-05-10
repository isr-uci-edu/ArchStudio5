package org.archstudio.myxgen.extension;

import java.util.Map;

import org.archstudio.myxgen.jet.util.PDEUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;

import com.google.common.collect.Maps;

public class BrickExtensionFactory {

	public static final String EXTENSION_POINT_ID = "org.archstudio.myxgen.brick";

	private static Map<String, BrickExtension> brickExtensions = Maps.newHashMap();

	public static void refresh() {
		for (IPluginModelBase pluginModel : PluginRegistry.getActiveModels()) {
			IExtensionPoint extensionPoint = PDEUtils.getExtensionPoint(EXTENSION_POINT_ID,
					new IPluginModelBase[] { pluginModel });
			for (IConfigurationElement element : extensionPoint.getConfigurationElements()) {
				if ("brick".equals(element.getName())) {
					try {
						BrickExtension brickExtension = new BrickExtension(pluginModel, element);
						brickExtensions.put(brickExtension.getId(), brickExtension);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		for (BrickExtension brickExtension : brickExtensions.values()) {
			String parentBrickId = brickExtension.getParentBrickId();
			if (parentBrickId != null) {
				brickExtension.setParentBrick(brickExtensions.get(parentBrickId));
			}
		}
	}

	public static Iterable<BrickExtension> getBrickExtensions() {
		refresh();
		return SystemUtils.copyIterable(brickExtensions.values());
	}
}
