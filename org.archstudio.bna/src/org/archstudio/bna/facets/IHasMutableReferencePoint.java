package org.archstudio.bna.facets;

import org.eclipse.draw2d.geometry.Point;

public interface IHasMutableReferencePoint extends IHasReferencePoint {

	public void setReferencePoint(Point worldPoint);

}