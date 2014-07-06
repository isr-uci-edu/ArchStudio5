package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class PolygonThing extends PolygonThingBase {

	public PolygonThing(@Nullable Object id) {
		super(id);
		removeShapeModifyingKey(IHasBoundingBox.BOUNDING_BOX_KEY);
		updateBounds();
		addThingListener(new IThingListener() {

			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					updateBounds();
				}
			}
		});
	}

	protected void updateBounds() {
		double x1 = Double.MAX_VALUE;
		double y1 = Double.MAX_VALUE;
		double x2 = Double.MIN_VALUE;
		double y2 = Double.MIN_VALUE;
		for (Point2D p : getRawPoints()) {
			x1 = Math.min(x1, p.getX());
			y1 = Math.min(y1, p.getY());
			x2 = Math.max(x2, p.getX());
			y2 = Math.max(y2, p.getY());
		}
		int ix = SystemUtils.floor(x1);
		int iy = SystemUtils.floor(y1);
		int iw = SystemUtils.ceil(x2 - ix);
		int ih = SystemUtils.ceil(y2 - ix);
		setBoundingBox(new Rectangle(ix, iy, iw, ih));
	}

	@Override
	public Point getReferencePoint() {
		List<? extends Point2D> points = getRawPoints();
		if (points.size() > 0) {
			return BNAUtils.toPoint(points.get(0));
		}
		return new Point(0, 0);
	}

	@Override
	public void setReferencePoint(Point value) {
		Point oldReferencePoint = getReferencePoint();
		List<Point2D> points = getPoints();
		Point2D point = points.get(0);
		double dx = point.getX() - oldReferencePoint.x;
		double dy = point.getY() - oldReferencePoint.y;
		for (Point2D p : points) {
			p.setLocation(point.getX() + dx, point.getY() + dy);
		}
		setRawPoints(points);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}

	@Override
	public Shape getStickyShape() {
		Path2D path = new Path2D.Double();
		List<? extends Point2D> points = getRawPoints();
		if (points.size() > 0) {
			Point2D point = points.get(0);
			path.moveTo(point.getX(), point.getY());
			for (int i = 1; i < points.size(); i++) {
				point = points.get(i);
				path.lineTo(point.getX(), point.getY());
			}
			path.closePath();
		}
		return path;
	}
}
