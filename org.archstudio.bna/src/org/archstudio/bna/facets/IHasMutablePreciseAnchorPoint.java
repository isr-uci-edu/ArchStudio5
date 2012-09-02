package org.archstudio.bna.facets;

import java.awt.geom.Point2D;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutablePreciseAnchorPoint extends IHasPreciseAnchorPoint {
	public void setPreciseAnchorPoint(Point2D newAnchorPoint);
}
