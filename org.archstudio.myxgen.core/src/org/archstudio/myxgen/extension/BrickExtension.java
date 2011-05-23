package org.archstudio.myxgen.extension;

import java.util.Collection;

import org.archstudio.myx.fw.IMyxBrickDescription;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.pde.core.plugin.IPluginModelBase;

import com.google.common.collect.Lists;

public class BrickExtension extends AbstractExtension implements IMyxBrickDescription {

	protected final String name;
	protected final String className;
	protected final String stubClassName;
	protected final boolean isAbstract;
	protected final String description;
	protected final Collection<InterfaceExtension> interfaces = Lists.newArrayList();
	protected final String parentBrickId;
	protected BrickExtension parentBrick = null;

	public BrickExtension(IPluginModelBase pluginModel, IConfigurationElement element) {
		super(pluginModel, element);
		try {
			this.name = element.getAttribute("name");
			this.className = element.getAttribute("class");
			this.stubClassName = element.getAttribute("stubClass") != null ? element.getAttribute("stubClass")
					: className + "Stub";
			this.isAbstract = Boolean.valueOf(element.getAttribute("abstract"));
			String description = null;
			String parentBrickId = null;
			for (IConfigurationElement child : element.getChildren()) {
				if ("description".equals(child.getName())) {
					description = child.getValue();
				}
				else if ("interface".equals(child.getName())) {
					interfaces.add(new InterfaceExtension(pluginModel, child));
				}
				else if ("parentBrick".equals(child.getName())) {
					parentBrickId = child.getAttribute("parentBrick");
				}
			}
			this.description = description;
			this.parentBrickId = parentBrickId;
		}
		catch (Throwable t) {
			throw new RuntimeException("Unable to parse " + pluginModel.getBundleDescription().getName(), t);
		}
	}

	public BrickExtension getParentBrick() {
		return parentBrick;
	}

	public void setParentBrick(BrickExtension parentBrick) {
		this.parentBrick = parentBrick;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getStubClassName() {
		return stubClassName;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public String getDescription() {
		return description;
	}

	public Collection<InterfaceExtension> getInterfaces() {
		return interfaces;
	}

	public String getParentBrickId() {
		return parentBrickId;
	}
}
