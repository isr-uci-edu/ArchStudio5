package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IThing;

public interface IHasIndicatorPoint extends IThing {
	public static final String INDICATOR_POINT_PROPERTY_NAME = "indicatorPoint";

	public Point getIndicatorPoint();
}
