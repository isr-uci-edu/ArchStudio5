package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.PointThingKey;
import org.eclipse.draw2d.geometry.Point;

public interface IHasAnchorPoint extends IThing {

	public static final IThingKey<Point> ANCHOR_POINT_KEY = PointThingKey.create("anchorPoint");

	public Point getAnchorPoint();
}
