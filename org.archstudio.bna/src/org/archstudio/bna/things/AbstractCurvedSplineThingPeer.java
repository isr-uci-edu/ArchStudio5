package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.QuadCurve2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.logics.coordinating.StickPointLogic.IHasLoopablePoint.LoopType;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractCurvedSplineThingPeer<T extends AbstractCurvedSplineThing> extends AbstractThingPeer<T> {

	static class ShapeInfo {
		Shape shape;
		Point anchorPoint;
		Point endpoint1StemPoint;
		Point endpoint2StemPoint;
	}

	static ShapeInfo getShapeInfo(AbstractCurvedSplineThing t, ICoordinateMapper cm) {

		ShapeInfo shapeInfo = new ShapeInfo();
		LoopType loopType = t.getLoopType();
		Point p1 = cm.worldToLocal(t.getEndpoint1());
		Point p2 = cm.worldToLocal(t.getEndpoint2());
		double l = t.getValue() * cm.getLocalScale();

		double mx = (p1.x + p2.x) / 2;
		double my = (p1.y + p2.y) / 2;
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		double dl = Math.sqrt(dx * dx + dy * dy);
		double angle = Math.PI - Math.atan2(dy, dx);

		switch (loopType) {
		case NONE: {
			double cx = mx + 2 * l * -Math.sin(angle);
			double cy = my + 2 * l * -Math.cos(angle);
			double ax = mx + l * -Math.sin(angle);
			double ay = my + l * -Math.cos(angle);
			shapeInfo.shape = new QuadCurve2D.Double(p1.x, p1.y, cx, cy, p2.x, p2.y);
			shapeInfo.anchorPoint = BNAUtils.toPoint(ax, ay);
			double sx = (ax + cx) / 2;
			double sy = (ay + cy) / 2;
			shapeInfo.endpoint1StemPoint = BNAUtils.toPoint(sx, sy);
			shapeInfo.endpoint2StemPoint = BNAUtils.toPoint(sx, sy);
		}
			break;
		case TOP_RIGHT:
		case BOTTOM_LEFT: {
			double radius = Math.sqrt((dx * dx + dy * dy) / 4) + Math.abs(l) / 2;
			double ml = Math.sqrt(radius * radius - dl * dl / 4);
			double cx = mx + ml * -Math.sin(angle);
			double cy = my + ml * -Math.cos(angle);
			double p1Angle = Math.atan2(cy - p1.y, cx - p1.x);
			double p2Angle = Math.atan2(cy - p2.y, cx - p2.x);
			double angleExtent = SystemUtils.loop(0, p1Angle - p2Angle, 2 * Math.PI);
			shapeInfo.shape = new Arc2D.Double(cx - radius, cy - radius, radius * 2, radius * 2, //
					(Math.PI - p1Angle) * 180 / Math.PI, angleExtent * 180 / Math.PI, Arc2D.OPEN);
			double ax = mx + (ml + radius) * -Math.sin(angle);
			double ay = my + (ml + radius) * -Math.cos(angle);
			shapeInfo.anchorPoint = BNAUtils.toPoint(ax, ay);
			double arrowheadStemLength = 20;
			double circumference = radius * 2 * Math.PI;
			double dAngle = 2 * Math.PI * arrowheadStemLength / circumference;
			double d1Angle = p1Angle + Math.PI - dAngle;
			double d2Angle = p2Angle + Math.PI + dAngle;
			shapeInfo.endpoint1StemPoint = BNAUtils.toPoint(cx + radius * Math.cos(d1Angle),
					cy + radius * Math.sin(d1Angle));
			shapeInfo.endpoint2StemPoint = BNAUtils.toPoint(cx + radius * Math.cos(d2Angle),
					cy + radius * Math.sin(d2Angle));
		}
			break;
		default:
			throw new IllegalArgumentException(loopType.toString());
		}

		return shapeInfo;
	}

	public AbstractCurvedSplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		return getShapeInfo(t, cm).shape;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		final int LINE_CLICK_DISTANCE = AbstractSplineThingPeer.LINE_CLICK_DISTANCE;
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		lbb.x -= LINE_CLICK_DISTANCE;
		lbb.y -= LINE_CLICK_DISTANCE;
		lbb.width += LINE_CLICK_DISTANCE * 2;
		lbb.height += LINE_CLICK_DISTANCE * 2;
		if (lbb.contains(location.getLocalPoint())) {
			Point lp = location.getLocalPoint();
			PathIterator pi = createLocalShape().getPathIterator(new AffineTransform(), 2);
			double[] coords = new double[6];
			double x1 = 0;
			double y1 = 0;
			double x2;
			double y2;
			while (!pi.isDone()) {
				switch (pi.currentSegment(coords)) {
				case PathIterator.SEG_MOVETO:
					x1 = coords[0];
					y1 = coords[1];
					break;
				case PathIterator.SEG_LINETO:
					x2 = coords[0];
					y2 = coords[1];
					double distSq = Line2D.ptSegDistSq(x1, y1, x2, y2, lp.x, lp.y);
					if (distSq <= LINE_CLICK_DISTANCE * LINE_CLICK_DISTANCE) {
						return true;
					}
					x1 = x2;
					y1 = y2;
					break;
				default:
					throw new IllegalArgumentException();
				}
				pi.next();
			}
		}
		return false;
	}

}
