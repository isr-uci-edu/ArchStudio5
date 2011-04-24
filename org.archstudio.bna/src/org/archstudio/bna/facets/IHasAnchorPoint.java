package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IThing;

public interface IHasAnchorPoint extends IThing {
	public static final String ANCHOR_POINT_PROPERTY_NAME = "anchorPoint";

	public Point getAnchorPoint();
}
