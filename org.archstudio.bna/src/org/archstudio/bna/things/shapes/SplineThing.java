package org.archstudio.bna.things.shapes;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

@NonNullByDefault
public class SplineThing extends SplineThingBase {

	private Point referencePoint = new Point(0, 0);

	public SplineThing(@Nullable Object id) {
		super(id);
		updateProperties();
		addThingListener(new IThingListener() {

			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					updateProperties();
				}
			}
		});
	}

	protected void updateProperties() {
		List<Point2D> points = Lists.newArrayListWithCapacity(getRawMidpoints().size() + 2);
		points.add(getRawEndpoint1());
		points.addAll(getRawMidpoints());
		points.add(getRawEndpoint2());
		double x1 = Double.POSITIVE_INFINITY;
		double y1 = Double.POSITIVE_INFINITY;
		double x2 = Double.NEGATIVE_INFINITY;
		double y2 = Double.NEGATIVE_INFINITY;
		for (Point2D p : points) {
			x1 = Math.min(x1, p.getX());
			y1 = Math.min(y1, p.getY());
			x2 = Math.max(x2, p.getX());
			y2 = Math.max(y2, p.getY());
		}
		int ix = SystemUtils.floor(x1);
		int iy = SystemUtils.floor(y1);
		int iw = SystemUtils.ceil(x2 - ix);
		int ih = SystemUtils.ceil(y2 - iy);
		setRawPoints(points);
		setRawBoundingBox(new Rectangle(ix, iy, iw, ih));
	}

	@Override
	public Point getReferencePoint() {
		return BNAUtils.clone(referencePoint);
	}

	@Override
	public void setReferencePoint(Point value) {
		double dx = value.x - referencePoint.x;
		double dy = value.y - referencePoint.y;
		referencePoint.x = value.x;
		referencePoint.y = value.y;

		List<Point2D> points = getPoints();
		for (Point2D p : points) {
			p.setLocation(p.getX() + dx, p.getY() + dy);
		}

		setRawEndpoint1(points.get(0));
		setRawMidpoints(points.subList(1, points.size() - 1));
		setRawEndpoint2(points.get(points.size() - 1));
	}

	@Override
	public Point2D getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey) {
		if (pointKey.equals(ENDPOINT_1_KEY)) {
			List<? extends Point2D> points = getRawPoints();
			if (points.size() > 2) {
				return points.get(1);
			}
			return stickLogic.getStuckPoint(this, ENDPOINT_2_KEY, points.get(1));
		}
		if (pointKey.equals(ENDPOINT_2_KEY)) {
			List<? extends Point2D> points = getRawPoints();
			if (points.size() > 2) {
				return points.get(points.size() - 2);
			}
			return stickLogic.getStuckPoint(this, ENDPOINT_1_KEY, points.get(points.size() - 2));
		}
		throw new IllegalArgumentException(pointKey.toString());
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}
}
