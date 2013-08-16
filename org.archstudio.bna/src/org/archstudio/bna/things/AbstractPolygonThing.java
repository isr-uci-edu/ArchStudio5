package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.Collections;
import java.util.List;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

@NonNullByDefault
public abstract class AbstractPolygonThing extends AbstractPointsThing implements IHasMutableAnchorPoint,
		IHasMutablePoints, IHasMutableReferencePoint, IIsSticky {

	public AbstractPolygonThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					set(IIsSticky.STICKY_SHAPE_KEY, createStickyShape());
				}
			}
		});
		setAnchorPoint(new Point(0, 0));
		super.initProperties();
		addShapeModifyingKey(POINTS_KEY);
		addShapeModifyingKey(ANCHOR_POINT_KEY);
		setPoints(Lists.newArrayList(new Point(0, -5), new Point(5, 0), new Point(0, 5), new Point(-5, 0)));
	}

	@Override
	protected Rectangle calculateBoundingBox() {
		Rectangle r = super.calculateBoundingBox();
		Point a = getAnchorPoint();
		r.x += a.x;
		r.y += a.y;
		return r;
	}

	@Override
	public Point getAnchorPoint() {
		return get(ANCHOR_POINT_KEY, new Point(0, 0));
	}

	@Override
	public void setAnchorPoint(Point p) {
		set(ANCHOR_POINT_KEY, p);
	}

	@Override
	public List<Point> getPoints() {
		return get(IHasPoints.POINTS_KEY, Collections.<Point> emptyList());
	}

	@Override
	public void setPoints(List<Point> points) {
		set(IHasPoints.POINTS_KEY, points);
	}

	@Override
	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	@Override
	public void setReferencePoint(Point worldPoint) {
		setAnchorPoint(worldPoint);
	}

	@Override
	public void moveRelative(final Point moveDelta) {
		if (moveDelta.x != 0 || moveDelta.y != 0) {
			Point a = getAnchorPoint();
			a.x += moveDelta.x;
			a.y += moveDelta.y;
			setAnchorPoint(a);
		}
	}

	protected Shape createStickyShape() {
		Path2D.Double path = new Path2D.Double();
		Point ap = getAnchorPoint();
		boolean firstPoint = true;
		for (Point p : getPoints()) {
			if (firstPoint) {
				path.moveTo(p.x + ap.x, p.y + ap.y);
				firstPoint = false;
			}
			else {
				path.lineTo(p.x + ap.x, p.y + ap.y);
			}
		}
		path.closePath();
		return path;
	}

	@Override
	public Shape getStickyShape() {
		return get(IIsSticky.STICKY_SHAPE_KEY);
	}
}
