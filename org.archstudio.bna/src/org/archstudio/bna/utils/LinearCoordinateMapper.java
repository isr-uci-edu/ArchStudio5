package org.archstudio.bna.utils;

import java.awt.geom.Point2D;

import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IScrollableCoordinateMapper;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class LinearCoordinateMapper extends AbstractCoordinateMapper implements IMutableCoordinateMapper,
		IScrollableCoordinateMapper {

	public LinearCoordinateMapper() {
	}

	@Override
	public Rectangle getLocalBounds() {
		return new Rectangle(//
				SystemUtils.round(worldBounds.x * localScale), //
				SystemUtils.round(worldBounds.y * localScale), //
				SystemUtils.round(worldBounds.width * localScale), //
				SystemUtils.round(worldBounds.height * localScale));
	}

	@Override
	public Point worldToLocal(Point worldPoint) {
		return new Point(//
				SystemUtils.round(worldPoint.x * localScale - localOrigin.x), //
				SystemUtils.round(worldPoint.y * localScale - localOrigin.y));
	}

	@Override
	public Point2D worldToLocal(Point2D worldPoint) {
		return new Point2D.Double(//
				worldPoint.getX() * localScale - localOrigin.x, //
				worldPoint.getY() * localScale - localOrigin.y);
	}

	@Override
	public Point localToWorld(Point localPoint) {
		return new Point(//
				SystemUtils.round((localPoint.x + localOrigin.x) / localScale), //
				SystemUtils.round((localPoint.y + localOrigin.y) / localScale));
	}

	@Override
	public Point2D localToWorld(Point2D localPoint) {
		return new Point2D.Double(//
				(localPoint.getX() + localOrigin.x) / localScale, //
				(localPoint.getY() + localOrigin.y) / localScale);
	}
}
