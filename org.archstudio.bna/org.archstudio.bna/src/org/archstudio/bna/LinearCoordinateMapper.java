package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class LinearCoordinateMapper extends CoordinateMapperAdapter implements IMutableCoordinateMapper,
		IScrollableCoordinateMapper {

	public LinearCoordinateMapper() {
	}

	protected boolean translate = true;

	@Override
	public void setTranslate(boolean translate) {
		this.translate = translate;
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
		if (translate) {
			worldPointResult.translate(-localOrigin.x, -localOrigin.y);
		}
		return worldPointResult;
	}

	@Override
	public synchronized Rectangle worldToLocal(Rectangle worldRectangleResult) {
		worldRectangleResult.scale(localScale);
		if (translate) {
			worldRectangleResult.translate(-localOrigin.x, -localOrigin.y);
		}
		return worldRectangleResult;
	}

	@Override
	public Point localToWorld(Point localPointResult) {
		if (translate) {
			localPointResult.translate(localOrigin);
		}
		localPointResult.scale(1 / localScale);
		return localPointResult;
	}

	@Override
	public synchronized Rectangle localToWorld(Rectangle localRectangleResult) {
		if (translate) {
			localRectangleResult.translate(localOrigin);
		}
		return localRectangleResult.scale(1 / localScale);
	}
}
