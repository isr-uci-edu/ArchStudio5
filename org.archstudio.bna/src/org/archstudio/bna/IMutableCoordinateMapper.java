package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IMutableCoordinateMapper extends ICoordinateMapper {

	public abstract void setWorldBounds(Rectangle worldBounds);

	public abstract void setLocalOrigin(Point localOffset);

	public abstract void setLocalScale(double localScale);

	public abstract void align(Point localPoint, Point worldPoint);

	public abstract void setLocalScaleAndAlign(double localScale, Point localPoint, Point worldPoint);

	public abstract void synchronizedUpdate(Runnable r);

}