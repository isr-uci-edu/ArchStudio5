package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.IThing;

public interface IRelativeMovable extends IThing {
	public Point getReferencePoint();

	public void setReferencePoint(Point p);
}
