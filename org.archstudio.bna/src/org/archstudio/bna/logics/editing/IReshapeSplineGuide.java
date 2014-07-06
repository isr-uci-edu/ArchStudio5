package org.archstudio.bna.logics.editing;

import java.awt.geom.Point2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.eclipse.jdt.annotation.Nullable;

public interface IReshapeSplineGuide {

	public boolean shouldBeStuck(IThing pointsThing, IThingKey<Point2D> pointKey);

	public @Nullable
	StickyMode getStickyMode(IThing pointsThing, IHasStickyShape stickyThing, IThingKey<Point2D> pointKey);

}
