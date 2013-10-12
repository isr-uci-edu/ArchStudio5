package org.archstudio.bna.utils;

import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.swt.graphics.Point;

public class DefaultCoordinate implements ICoordinate {

	public static final DefaultCoordinate forLocal(Point localPoint, ICoordinateMapper cm) {
		return new DefaultCoordinate(localPoint, cm.localToWorld(new Point(localPoint.x, localPoint.y)));
	}

	public static final DefaultCoordinate forWorld(Point worldPoint, ICoordinateMapper cm) {
		return new DefaultCoordinate(cm.worldToLocal(new Point(worldPoint.x, worldPoint.y)), worldPoint);
	}

	Point localPoint;
	Point worldPoint;

	public DefaultCoordinate(Point localPoint, Point worldPoint) {
		super();
		this.localPoint = localPoint;
		this.worldPoint = worldPoint;
	}

	@Override
	public Point getLocalPoint() {
		return new Point(localPoint.x, localPoint.y);
	}

	@Override
	public Point getWorldPoint() {
		return new Point(worldPoint.x, worldPoint.y);
	}

	@Override
	public String toString() {
		return "DefaultCoordinate [localPoint=" + localPoint + ", worldPoint=" + worldPoint + "]";
	}

}
