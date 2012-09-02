package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasMutableAnchorPoint extends IHasAnchorPoint {
	public void setAnchorPoint(Point newAnchorPoint);
}
