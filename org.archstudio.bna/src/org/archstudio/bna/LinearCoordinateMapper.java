package org.archstudio.bna;

import java.awt.geom.Point2D;

import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class LinearCoordinateMapper extends AbstractCoordinateMapper implements IMutableCoordinateMapper,
		IScrollableCoordinateMapper {

	public LinearCoordinateMapper() {
	}

	public Rectangle getLocalBounds() {
		return new Rectangle(//
				BNAUtils.round(worldBounds.x * localScale), //
				BNAUtils.round(worldBounds.y * localScale), //
				BNAUtils.round(worldBounds.width * localScale), //
				BNAUtils.round(worldBounds.height * localScale));
	}

	public Point worldToLocal(Point worldPoint) {
		return new Point(//
				BNAUtils.round(worldPoint.x * localScale - localOrigin.x), //
				BNAUtils.round(worldPoint.y * localScale - localOrigin.y));
	}

	public Point2D worldToLocal(Point2D worldPoint) {
		return new Point2D.Double(//
				worldPoint.getX() * localScale - localOrigin.x, //
				worldPoint.getY() * localScale - localOrigin.y);
	}

	public Point localToWorld(Point localPoint) {
		return new Point(//
				BNAUtils.round((localPoint.x + localOrigin.x) / localScale), //
				BNAUtils.round((localPoint.y + localOrigin.y) / localScale));
	}

	public Point2D localToWorld(Point2D localPoint) {
		return new Point2D.Double(//
				(localPoint.getX() + localOrigin.x) / localScale, //
				(localPoint.getY() + localOrigin.y) / localScale);
	}
}
