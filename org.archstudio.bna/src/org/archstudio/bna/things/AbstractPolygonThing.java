package org.archstudio.bna.things;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

@NonNullByDefault
public abstract class AbstractPolygonThing extends AbstractPointsThing implements IHasMutableAnchorPoint,
		IHasMutablePoints, IHasMutableReferencePoint, IIsSticky {

	public AbstractPolygonThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		set(STICKY_SHAPE_KEY, new Rectangle2D.Float(-5, -5, 10, 10));
		addShapeModifyingKey(POINTS_KEY);
		addShapeModifyingKey(ANCHOR_POINT_KEY);
		super.initProperties();
		setPoints(Lists.newArrayList(new Point(0, -5), new Point(5, 0), new Point(0, 5), new Point(-5, 0)));
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
	protected Rectangle calculateBoundingBox() {
		Rectangle r = super.calculateBoundingBox();
		Point a = getAnchorPoint();
		r.x += a.x;
		r.y += a.y;
		return r;
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
	public List<Point> getPoints() {
		return get(POINTS_KEY);
	}

	@Override
	public void setPoints(List<Point> points) {
		set(POINTS_KEY, points);
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
		Point ap = getAnchorPoint();
		List<Point> points = getPoints();

		Polygon polygon = new Polygon();
		for (Point p : points) {
			polygon.addPoint(p.x + ap.x, p.y + ap.y);
		}

		return polygon;
	}

	@Override
	public Shape getStickyShape() {
		return get(STICKY_SHAPE_KEY);
	}
}
