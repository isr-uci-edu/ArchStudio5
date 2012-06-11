package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IHasMutableAnchorPoint extends IHasAnchorPoint {
	public void setAnchorPoint(Point newAnchorPoint);
}
