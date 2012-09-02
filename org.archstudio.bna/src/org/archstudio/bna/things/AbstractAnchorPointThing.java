package org.archstudio.bna.things;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public abstract class AbstractAnchorPointThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutableAnchorPoint, IIsSticky {

	public AbstractAnchorPointThing(@Nullable Object id) {
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
		return get(ANCHOR_POINT_KEY, new Point(0, 0));
	}

	@Override
	public void setAnchorPoint(@Nullable Point p) {
		set(ANCHOR_POINT_KEY, p);
	}

	@Override
	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	@Override
	public void moveRelative(final 
			Point moveDelta) {
		if (moveDelta.x != 0 || moveDelta.y != 0) {
			Point p = getAnchorPoint();
			setAnchorPoint(new Point(p.x + moveDelta.x, p.y + moveDelta.y));
		}
	}

	@Override
	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		return getAnchorPoint();
	}
}
