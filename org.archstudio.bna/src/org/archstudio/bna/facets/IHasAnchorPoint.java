package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasAnchorPoint extends IThing, IIsSticky {

	public static final IThingKey<Point> ANCHOR_POINT_KEY = CloneThingKey.create("anchorPoint",
			AbstractCloneThingKey.point());

	public Point getAnchorPoint();
}
