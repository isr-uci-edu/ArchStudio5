package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.eclipse.draw2d.geometry.Point;

public abstract class AbstractAnchorPointThing extends AbstractRelativeMovableThing implements IHasMutableAnchorPoint {

	public AbstractAnchorPointThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setAnchorPoint(new Point(0, 0));
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
