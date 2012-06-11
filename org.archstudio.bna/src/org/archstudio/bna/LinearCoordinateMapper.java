package org.archstudio.bna;

import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class LinearCoordinateMapper extends AbstractCoordinateMapper implements IMutableCoordinateMapper,
		IScrollableCoordinateMapper {

	public LinearCoordinateMapper() {
	}

	@Override
	public Rectangle getLocalBounds() {
		return new Rectangle(//
				BNAUtils.round(worldBounds.x * localScale), //
				BNAUtils.round(worldBounds.y * localScale), //
				BNAUtils.round(worldBounds.width * localScale), //
				BNAUtils.round(worldBounds.height * localScale));
	}

	@Override
	public Point worldToLocal(Point worldPoint) {
		return new Point(//
				BNAUtils.round(worldPoint.x * localScale - localOrigin.x), //
				BNAUtils.round(worldPoint.y * localScale - localOrigin.y));
	}

	@Override
	public Point localToWorld(Point localPoint) {
		return new Point(//
				BNAUtils.round((localPoint.x + localOrigin.x) / localScale), //
				BNAUtils.round((localPoint.y + localOrigin.y) / localScale));
	}
}
