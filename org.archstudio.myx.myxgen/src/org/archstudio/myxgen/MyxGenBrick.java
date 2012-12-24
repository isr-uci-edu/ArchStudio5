package org.archstudio.myxgen;

import java.util.Collection;

import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.runtime.IConfigurationElement;

import com.google.common.collect.Lists;

public class MyxGenBrick extends AbstractExtension {

	protected final String name;
	protected final String className;
	protected final String stubClassName;
	protected final boolean isAbstract;
	protected final String description;
	protected final Collection<MyxGenInterface> interfaces = Lists.newArrayList();
	protected final String parentBrickId;

	public MyxGenBrick(IConfigurationElement element) {
		super(element);
		try {
			this.name = element.getAttribute("name");
			this.className = element.getAttribute("class");
			this.stubClassName = element.getAttribute("stubClass") != null ? element.getAttribute("stubClass")
					: className + "Stub";
			this.isAbstract = Boolean.valueOf(element.getAttribute("abstract"));
			this.parentBrickId = element.getAttribute("parentBrick");
			String description = null;
			for (IConfigurationElement child : element.getChildren()) {
				if ("description".equals(child.getName())) {
					description = child.getValue();
				}
				else if ("interface".equals(child.getName())) {
					interfaces.add(new MyxGenInterface(child));
				}
			}
			this.description = description;
		}
		catch (Throwable t) {
			throw new RuntimeException("Unable to parse " + element.getContributor().getName(), t);
		}
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

	public Collection<MyxGenInterface> getInterfaces() {
		return interfaces;
	}

	public String getParentBrickId() {
		return parentBrickId;
	}

	@Override
	public String toString() {
		return SystemUtils.simpleName(this.getClass()) + "[" //
				+ "name=" + name + ","//
				+ "className=" + className + ","//
				+ "stubClassName=" + stubClassName + ","//
				+ "isAbstract=" + isAbstract + ","//
				+ "description=" + description + ","//
				+ "interfaces=" + interfaces + "," //
				+ "parentBrickId=" + parentBrickId + "]";
	}
}
