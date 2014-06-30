package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasIndicatorPoint extends IThing {
	public static final IThingKey<Point> INDICATOR_POINT_KEY = ThingKey.create("indicatorPoint", ThingKey.point());

	public @Nullable
	Point getIndicatorPoint();
}
