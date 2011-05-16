package org.archstudio.bna.facets;

import org.eclipse.draw2d.geometry.Point;

public interface IHasMutableAnchorPoint extends IHasAnchorPoint {
	public void setAnchorPoint(Point newAnchorPoint);
}
