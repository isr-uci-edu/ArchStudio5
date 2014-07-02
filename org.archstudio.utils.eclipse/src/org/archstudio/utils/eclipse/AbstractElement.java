package org.archstudio.utils.eclipse;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;

public abstract class AbstractElement {

	private final IContributor contributor;
	private final String id;

	public AbstractElement(IConfigurationElement element) {
		this.contributor = checkNotNull(element.getContributor());
		this.id = element.getAttribute("id");
	}

	public IContributor getContributor() {
		return contributor;
	}

	public String getId() {
		return id;
	}
}
