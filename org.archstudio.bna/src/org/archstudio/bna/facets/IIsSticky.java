package org.archstudio.bna.facets;

import org.archstudio.bna.constants.StickyMode;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IIsSticky extends IHasShapeKeys {

	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint);

}
