package org.archstudio.myxgen.extension;

import org.archstudio.myxgen.jet.util.PDEUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class BrickExtensionFactory {

	public static final String EXTENSION_POINT_ID = "org.archstudio.myxgen.brick";

	private static Multimap<String, BrickExtension> brickExtensions = HashMultimap.create();

	public static void refresh() {
		synchronized (brickExtensions) {
			brickExtensions.clear();
			for (IPluginModelBase pluginModel : PluginRegistry.getAllModels()) {
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
					if (brickExtensions.containsKey(parentBrickId)) {
						brickExtension.setParentBrick(brickExtensions.get(parentBrickId).iterator().next());
					}
				}
			}
		}
	}

	public static Iterable<BrickExtension> getBrickExtensions() {
		synchronized (brickExtensions) {
			return SystemUtils.copyIterable(brickExtensions.values());
		}
	}

	public static BrickExtension getBrickExtension(String brickID, boolean includeWorkspace) {
		synchronized (brickExtensions) {
			if (includeWorkspace) {
				for (BrickExtension e : brickExtensions.get(brickID)) {
					if (e.getPluginModel().getUnderlyingResource() != null) {
						return e;
					}
				}
			}
			for (BrickExtension e : brickExtensions.get(brickID)) {
				return e;
			}
			return null;
		}
	}
}
