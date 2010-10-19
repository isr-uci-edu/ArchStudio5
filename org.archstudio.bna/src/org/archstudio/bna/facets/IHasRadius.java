package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasRadius extends IThing {
	public static final String RADIUS_PROPERTY_NAME = "radius";

	public int getRadius();
}
