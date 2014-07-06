package org.archstudio.bna.builder;

import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;

public class Mappings {

	private final Map<String, IConfigurationElement> facets;
	private final Map<String, IConfigurationElement> things;

	public Mappings(Map<String, IConfigurationElement> facets, Map<String, IConfigurationElement> things) {
		this.facets = facets;
		this.things = things;
	}

	public IConfigurationElement getFacet(String name) {
		return facets.get(name);
	}

	public IConfigurationElement getThing(String name) {
		return things.get(name);
	}

}
