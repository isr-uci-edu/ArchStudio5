package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasAnchorPoint extends IThing, IIsSticky {

	public static final IThingKey<Point> ANCHOR_POINT_KEY = ThingKey.create("anchorPoint", ThingKey.point());

	public Point getAnchorPoint();
}
