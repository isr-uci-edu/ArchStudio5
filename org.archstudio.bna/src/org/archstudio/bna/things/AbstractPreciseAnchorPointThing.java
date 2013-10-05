package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasMutablePreciseAnchorPoint;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public abstract class AbstractPreciseAnchorPointThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutablePreciseAnchorPoint, IIsSticky {

	public AbstractPreciseAnchorPointThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setPreciseAnchorPoint(new Point2D.Double(0, 0));
		set(STICKY_SHAPE_KEY, new Rectangle2D.Double(0, 0, 0, 0));
		addShapeModifyingKey(PRECISION_ANCHOR_POINT_KEY);
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
	public Point2D getPreciseAnchorPoint() {
		return get(PRECISION_ANCHOR_POINT_KEY);
	}

	@Override
	public void setPreciseAnchorPoint(Point2D p) {
		set(PRECISION_ANCHOR_POINT_KEY, p);
	}

	@Override
	public Point getReferencePoint() {
		Point2D p = getPreciseAnchorPoint();
		return new Point(BNAUtils.round(p.getX()), BNAUtils.round(p.getY()));
	}

	@Override
	public void moveRelative(final Point moveDelta) {
		if (moveDelta.x != 0 || moveDelta.y != 0) {
			Point2D ap = getPreciseAnchorPoint();
			setPreciseAnchorPoint(new Point2D.Double(ap.getX() + moveDelta.x, ap.getY() + moveDelta.y));
		}
	}

	protected Shape createStickyShape() {
		Point2D point = getPreciseAnchorPoint();
		return new Rectangle2D.Double(point.getX(), point.getY(), 0, 0);
	}

	@Override
	public Shape getStickyShape() {
		return get(STICKY_SHAPE_KEY);
	}
}
