package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Point;

public interface ICoordinate {

	public Point getLocalPoint(Point result);

	public Point getWorldPoint(Point result);

}
