package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IThing;

public interface IHasMidpoints extends IThing {
	public static final String MIDPOINTS_PROPERTY_NAME = "midpoints";

	public Point[] getMidpoints();
}
