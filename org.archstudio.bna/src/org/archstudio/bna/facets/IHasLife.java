package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasLife extends IThing {
	public static final String LIFE_PROPERTY_NAME = "life";

	public int getLife();
}
