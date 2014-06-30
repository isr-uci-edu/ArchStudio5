package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Point;

public interface IHasSecondaryAnchorPoint extends IHasAnchorPoint {

	public static final IThingKey<Point> SECONDARY_ANCHOR_POINT_KEY = ThingKey.create("secondaryAnchorPoint");

	public Point getSecondaryAnchorPoint();
}
