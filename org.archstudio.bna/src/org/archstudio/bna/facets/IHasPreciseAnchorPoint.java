package org.archstudio.bna.facets;

import java.awt.geom.Point2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;

public interface IHasPreciseAnchorPoint extends IThing, IIsSticky {

	public static final IThingKey<Point2D> PRECISION_ANCHOR_POINT_KEY = CloneThingKey.create("precisionAnchorPoint",
			CloneThingKey.point2D());

	public Point2D getPreciseAnchorPoint();
}
