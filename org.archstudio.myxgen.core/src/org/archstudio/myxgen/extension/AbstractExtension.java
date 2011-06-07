package org.archstudio.myxgen.extension;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;

public abstract class AbstractExtension {

	protected final IContributor contributor;
	protected final String id;

	public AbstractExtension(IConfigurationElement element) {
		this.contributor = checkNotNull(element.getContributor());
		this.id = checkNotNull(element.getAttribute("id"));
	}

	public IContributor getContributor() {
		return contributor;
	}

	public String getId() {
		return id;
	}
}
