package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public class CurvedSplineThing extends CurvedSplineThingBase {

	private Point referencePoint = new Point(0, 0);

	Shape shape;
	Point2D endpoint1StemPoint;
	Point2D endpoint2StemPoint;

	public CurvedSplineThing(@Nullable Object id) {
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

	void updateProperties() {
		Point2D p1 = getRawEndpoint1();
		Point2D p2 = getRawEndpoint2();
		// curve amount
		double l = getRawCurve();
		double spacing = getRawSpacing();
		double a = l + spacing;
		// midpoint between p1 & p2
		double mx = (p1.getX() + p2.getX()) / 2;
		double my = (p1.getY() + p2.getY()) / 2;
		// delta of p1 & p2
		double dx = p2.getX() - p1.getX();
		double dy = p2.getY() - p1.getY();
		// angle between p1 & p2
		double angle = Math.PI - Math.atan2(dy, dx);

		switch (getRawLoopOrientation()) {
		case NONE: {
			double cx = mx + 2 * l * Math.sin(angle);
			double cy = my + 2 * l * Math.cos(angle);
			double ax = mx + a * Math.sin(angle);
			double ay = my + a * Math.cos(angle);
			shape = new QuadCurve2D.Double(p1.getX(), p1.getY(), cx, cy, p2.getX(), p2.getY());
			setRawAnchorPoint(new Point2D.Double(ax, ay));
			double sx = mx + 5 * l * Math.sin(angle) / 4;
			double sy = my + 5 * l * Math.cos(angle) / 4;
			endpoint1StemPoint = new Point2D.Double(sx, sy);
			endpoint2StemPoint = new Point2D.Double(sx, sy);
		}
			break;
		case NORTHWEST:
		case NORTH:
		case NORTHEAST:
		case WEST:
		case EAST:
		case SOUTHWEST:
		case SOUTH:
		case SOUTHEAST: {
			double dl = Math.sqrt(dx * dx + dy * dy);
			double radius = dl / 2 + Math.abs(l) / 2;
			double ml = Math.sqrt(radius * radius - dl * dl / 4);
			double cx = mx + ml * Math.sin(angle);
			double cy = my + ml * Math.cos(angle);
			double p2Angle = Math.atan2(cy - p1.getY(), cx - p1.getX());
			double p1Angle = Math.atan2(cy - p2.getY(), cx - p2.getX());
			double angleExtent = SystemUtils.loop(0, p1Angle - p2Angle, 2 * Math.PI);
			shape = new Arc2D.Double(cx - radius, cy - radius, radius * 2, radius * 2, //
					(Math.PI - p1Angle) * 180 / Math.PI, angleExtent * 180 / Math.PI, Arc2D.OPEN);
			double ax = mx + (ml + radius + spacing) * Math.sin(angle);
			double ay = my + (ml + radius + spacing) * Math.cos(angle);
			setRawAnchorPoint(new Point2D.Double(ax, ay));
			double arrowheadStemLength = 20;
			double circumference = radius * 2 * Math.PI;
			double dAngle = 2 * Math.PI * arrowheadStemLength / circumference;
			double d2Angle = p1Angle + Math.PI - dAngle;
			double d1Angle = p2Angle + Math.PI + dAngle;
			endpoint1StemPoint = new Point2D.Double(cx + radius * Math.cos(d1Angle), cy + radius * Math.sin(d1Angle));
			endpoint2StemPoint = new Point2D.Double(cx + radius * Math.cos(d2Angle), cy + radius * Math.sin(d2Angle));
		}
			break;
		default:
			//throw new IllegalArgumentException(getRawLoopOrientation().toString());
		}
		setBoundingBox(BNAUtils.toRectangle(shape.getBounds2D()));
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}

	@Override
	public Point2D getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey) {
		if (stickLogic.isLoopingPoint(this, ENDPOINT_1_KEY, ENDPOINT_2_KEY)) {
			if (ENDPOINT_1_KEY.equals(pointKey)) {
				return endpoint1StemPoint;
			}
			else if (ENDPOINT_2_KEY.equals(pointKey)) {
				return endpoint2StemPoint;
			}
			else {
				throw new IllegalArgumentException(pointKey.toString());
			}
		}
		else {
			if (ENDPOINT_1_KEY.equals(pointKey) || ENDPOINT_2_KEY.equals(pointKey)) {
				Point2D p1 = stickLogic.getStuckPoint(this, ENDPOINT_1_KEY);
				Point2D p2 = stickLogic.getStuckPoint(this, ENDPOINT_2_KEY);
				double l = -getRawCurve();
				double dx = p2.getX() - p1.getX();
				double dy = p2.getY() - p1.getY();
				double angle = Math.PI - Math.atan2(dy, dx);
				double mx = (p1.getX() + p2.getX()) / 2;
				double my = (p1.getY() + p2.getY()) / 2;
				double cx = mx + l * -Math.sin(angle);
				double cy = my + l * -Math.cos(angle);
				return new Point2D.Double(cx, cy);
			}
			else {
				throw new IllegalArgumentException(pointKey.toString());
			}
		}
	}

	@Override
	public Orientation getLoopOrientation(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey) {
		if (ENDPOINT_1_KEY.equals(pointKey) || ENDPOINT_2_KEY.equals(pointKey)) {
			if (stickLogic.isLoopingPoint(this, ENDPOINT_1_KEY, ENDPOINT_2_KEY)) {
				return getRawCurve() >= 0 ? Orientation.NORTHEAST : Orientation.SOUTHWEST;
			}
			return Orientation.NONE;
		}
		throw new IllegalArgumentException(pointKey.toString());
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

		Point2D p1 = getEndpoint1();
		p1.setLocation(p1.getX() + dx, p1.getY() + dy);
		setRawEndpoint1(p1);

		Point2D p2 = getEndpoint2();
		p2.setLocation(p2.getX() + dx, p2.getY() + dy);
		setRawEndpoint2(p2);
	}

}
