package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Point;

public class DefaultCoordinate implements ICoordinate {

	public static final DefaultCoordinate forLocal(Point localPoint, ICoordinateMapper cm) {
		return new DefaultCoordinate(localPoint, cm.localToWorld(localPoint.getCopy()));
	}

	public static final DefaultCoordinate forWorld(Point worldPoint, ICoordinateMapper cm) {
		return new DefaultCoordinate(cm.worldToLocal(worldPoint.getCopy()), worldPoint);
	}

	Point localPoint;
	Point worldPoint;

	public DefaultCoordinate(Point localPoint, Point worldPoint) {
		super();
		this.localPoint = localPoint;
		this.worldPoint = worldPoint;
	}

	@Override
	public Point getLocalPoint(Point result) {
		return result.setLocation(localPoint);
	}

	@Override
	public Point getWorldPoint(Point result) {
		return result.setLocation(worldPoint);
	}

	@Override
	public String toString() {
		return "DefaultCoordinate [localPoint=" + localPoint + ", worldPoint=" + worldPoint + "]";
	}

}
