package org.archstudio.bna.builder;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import com.google.common.collect.Lists;

public class ElementPath {

	IConfigurationElement element;

	public ElementPath(IConfigurationElement element) {
		this.element = element;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(element.getContributor().getName());
		List<IConfigurationElement> parents = Lists.newArrayList();
		IConfigurationElement parent = element;
		while (parent != null) {
			parents.add(parent);
			Object actualParent = parent.getParent();
			parent = null;
			if (actualParent instanceof IConfigurationElement) {
				parent = (IConfigurationElement) actualParent;
			}
		}
		for (IConfigurationElement segment : Lists.reverse(parents)) {
			sb.append("/").append(segment.getName());
			String[] attributeNames = segment.getAttributeNames();
			if (attributeNames.length > 0) {
				sb.append("[").append(attributeNames[0]).append("=").append(segment.getAttribute(attributeNames[0]))
						.append("]");
			}
		}
		return sb.toString();
	}

}
