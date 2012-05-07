package org.archstudio.bna.facets;

import org.archstudio.bna.constants.StickyMode;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

public interface IIsSticky extends IHasShape {

	public PrecisionPoint getStickyPointNear(StickyMode stickyMode, Point nearPoint);

}
