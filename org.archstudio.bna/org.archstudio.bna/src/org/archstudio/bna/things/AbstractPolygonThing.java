package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractPolygonThing extends AbstractSplineThing implements IHasMutableAnchorPoint {

	public AbstractPolygonThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setAnchorPoint(new Point(0, 0));
	}

	@Override
	protected Rectangle calculateBoundingBox() {
		return super.calculateBoundingBox().translate(getAnchorPoint());
	}

	@Override
	public Point getAnchorPoint() {
		return get(ANCHOR_POINT_KEY);
	}

	@Override
	public void setAnchorPoint(Point p) {
		set(ANCHOR_POINT_KEY, p);
	}

	@Override
	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	@Override
	public void moveRelative(final Point moveDelta) {
		if (moveDelta.x != 0 || moveDelta.y != 0) {
			synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					setAnchorPoint(getAnchorPoint().translate(moveDelta));
				}
			});
		}
	}
}
