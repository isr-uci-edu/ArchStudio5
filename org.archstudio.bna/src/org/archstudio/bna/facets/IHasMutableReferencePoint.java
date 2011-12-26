package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IHasMutableReferencePoint extends IHasReferencePoint {

	public void setReferencePoint(Point worldPoint);

}