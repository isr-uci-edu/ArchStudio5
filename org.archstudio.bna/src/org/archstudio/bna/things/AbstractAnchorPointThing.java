package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public abstract class AbstractAnchorPointThing extends AbstractRelativeMovableReferencePointThing implements
		IHasAnchorPoint, IIsSticky {

	public AbstractAnchorPointThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		set(STICKY_SHAPE_KEY, new Rectangle2D.Double(0, 0, 0, 0));
		addShapeModifyingKey(ANCHOR_POINT_KEY);
		super.initProperties();
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					set(STICKY_SHAPE_KEY, createStickyShape());
				}
			}
		});
		set(STICKY_SHAPE_KEY, createStickyShape());
	}

	@Override
	public Point getAnchorPoint() {
		return get(ANCHOR_POINT_KEY);
	}

	protected void setAnchorPoint(@Nullable Point p) {
		set(ANCHOR_POINT_KEY, p);
	}

	@Override
	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	@Override
	public void moveRelative(final Point moveDelta) {
		if (moveDelta.x != 0 || moveDelta.y != 0) {
			Point p = getAnchorPoint();
			setAnchorPoint(new Point(p.x + moveDelta.x, p.y + moveDelta.y));
		}
	}

	protected Shape createStickyShape() {
		Point anchor = getAnchorPoint();
		return new Rectangle2D.Float(anchor.x, anchor.y, 0, 0);
	}

	@Override
	public Shape getStickyShape() {
		return get(STICKY_SHAPE_KEY);
	}
}
