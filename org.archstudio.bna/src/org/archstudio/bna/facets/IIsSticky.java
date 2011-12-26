package org.archstudio.bna.facets;

import org.archstudio.bna.constants.StickyMode;
import org.eclipse.swt.graphics.Point;

public interface IIsSticky extends IHasShape {

	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint);

}
