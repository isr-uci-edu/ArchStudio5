package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasAngle extends IThing {
	public static final String ANGLE_PROPERTY_NAME = "angle";

	public int getAngle();
}
