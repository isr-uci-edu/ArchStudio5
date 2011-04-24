package org.archstudio.bna.facets;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;

public interface IHasWorld extends IThing {
	public static final String WORLD_PROPERTY_NAME = "$world";

	public IBNAWorld getWorld();
}
