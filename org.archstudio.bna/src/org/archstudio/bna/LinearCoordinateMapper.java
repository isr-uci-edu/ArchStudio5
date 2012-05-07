package org.archstudio.bna;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

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
		checkNotNull(worldPoint);
		
		if(worldPoint instanceof PrecisionPoint){
			return new PrecisionPoint(
					worldPoint.preciseX() * localScale - localOrigin.x, //
					worldPoint.preciseY() * localScale - localOrigin.y);
		}
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
