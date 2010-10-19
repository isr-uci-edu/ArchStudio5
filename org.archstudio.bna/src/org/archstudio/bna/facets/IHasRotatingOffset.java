package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasRotatingOffset extends IThing {
	public static final String ROTATING_OFFSET_PROPERTY_NAME = "rotatingOffset";

	public int getRotatingOffset();
}
