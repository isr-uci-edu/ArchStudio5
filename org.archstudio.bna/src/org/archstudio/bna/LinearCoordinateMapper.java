package org.archstudio.bna;

import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class LinearCoordinateMapper extends CoordinateMapperAdapter implements IMutableCoordinateMapper,
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
	public synchronized Rectangle worldToLocal(Rectangle worldRectangle) {
		return new Rectangle(//
				BNAUtils.round(worldRectangle.x * localScale - localOrigin.x), //
				BNAUtils.round(worldRectangle.y * localScale - localOrigin.y), //
				BNAUtils.round(worldRectangle.width * localScale), //
				BNAUtils.round(worldRectangle.height * localScale));
	}

	@Override
	public Point localToWorld(Point localPoint) {
		return new Point(//
				BNAUtils.round((localPoint.x + localOrigin.x) / localScale), //
				BNAUtils.round((localPoint.y + localOrigin.y) / localScale));
	}

	@Override
	public synchronized Rectangle localToWorld(Rectangle localRectangle) {
		return new Rectangle(//
				BNAUtils.round((localRectangle.x + localOrigin.x) / localScale), //
				BNAUtils.round((localRectangle.y + localOrigin.y) / localScale), //
				BNAUtils.round(localRectangle.width / localScale), //
				BNAUtils.round(localRectangle.height / localScale));
	}
}
