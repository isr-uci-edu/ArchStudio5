package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.IThingKey;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

public interface IReshapeSplineGuide {

	public boolean shouldBeStuck(IThing pointsThing, IThingKey<Point> pointKey);

	public @Nullable
	StickyMode getStickyMode(IThing pointsThing, IIsSticky stickyThing, IThingKey<Point> pointKey);

}
