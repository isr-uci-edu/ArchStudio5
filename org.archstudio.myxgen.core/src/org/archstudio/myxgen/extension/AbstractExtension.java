package org.archstudio.myxgen.extension;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.pde.core.plugin.IPluginModelBase;

public abstract class AbstractExtension {

	protected final IPluginModelBase pluginModel;
	protected final String id;

	public AbstractExtension(IPluginModelBase pluginModel, IConfigurationElement element) {
		this.pluginModel = checkNotNull(pluginModel);
		this.id = checkNotNull(element.getAttribute("id"));
	}

	public IPluginModelBase getPluginModel() {
		return pluginModel;
	}

	public String getId() {
		return id;
	}
}
