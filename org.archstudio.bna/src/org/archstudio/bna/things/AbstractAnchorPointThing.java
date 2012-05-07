package org.archstudio.bna.things;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

public abstract class AbstractAnchorPointThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutableAnchorPoint, IIsSticky {

	public AbstractAnchorPointThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setAnchorPoint(new Point(0, 0));
		addShapeModifyingKey(ANCHOR_POINT_KEY);
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
					Point p = getAnchorPoint();
					setAnchorPoint(new Point(p.x + moveDelta.x, p.y + moveDelta.y));
				}
			});
		}
	}

	@Override
	public PrecisionPoint getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		return new PrecisionPoint(getAnchorPoint());
	}
}
