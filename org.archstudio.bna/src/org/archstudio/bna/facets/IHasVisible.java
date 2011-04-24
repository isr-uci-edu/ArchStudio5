package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasVisible extends IThing {
	public static final String VISIBLE_PROPERTY_NAME = "visible";

	public boolean isVisible();
}
