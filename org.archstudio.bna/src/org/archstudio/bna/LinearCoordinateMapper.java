package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class LinearCoordinateMapper extends CoordinateMapperAdapter implements IMutableCoordinateMapper,
		IScrollableCoordinateMapper {

	public LinearCoordinateMapper() {
	}

	@Override
	public Rectangle getLocalBounds(Rectangle result) {
		result.setBounds(worldBounds);
		result.scale(localScale);
		return result;
	}

	@Override
	public Point worldToLocal(Point worldPointResult) {
		worldPointResult.scale(localScale);
		worldPointResult.translate(-localOrigin.x, -localOrigin.y);
		return worldPointResult;
	}

	@Override
	public synchronized Rectangle worldToLocal(Rectangle worldRectangleResult) {
		worldRectangleResult.scale(localScale);
		worldRectangleResult.translate(-localOrigin.x, -localOrigin.y);
		return worldRectangleResult;
	}

	@Override
	public Point localToWorld(Point localPointResult) {
		localPointResult.translate(localOrigin);
		localPointResult.scale(1 / localScale);
		return localPointResult;
	}

	@Override
	public synchronized Rectangle localToWorld(Rectangle localRectangleResult) {
		localRectangleResult.translate(localOrigin);
		return localRectangleResult.scale(1 / localScale);
	}
}
