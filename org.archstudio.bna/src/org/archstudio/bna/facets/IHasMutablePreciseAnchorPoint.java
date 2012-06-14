package org.archstudio.bna.facets;

import java.awt.geom.Point2D;

public interface IHasMutablePreciseAnchorPoint extends IHasPreciseAnchorPoint {
	public void setPreciseAnchorPoint(Point2D newAnchorPoint);
}
