package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.QuadCurve2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractCurvedSplineThingPeer<T extends AbstractCurvedSplineThing> extends AbstractThingPeer<T> {

	public AbstractCurvedSplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Point p1 = cm.worldToLocal(t.getEndpoint1());
		Point p2 = cm.worldToLocal(t.getEndpoint2());
		Point ap = cm.worldToLocal(t.getAnchorPoint());
		Point lp = t.getLoopPoint();
		if (lp != null) {
			lp = cm.worldToLocal(lp);
		}
		if (lp != null) {
			double l = t.getValue() * cm.getLocalScale();
			return new CubicCurve2D.Double(p1.x, p1.y, lp.x + l * 2, lp.y, lp.x, lp.y - l * 2, p2.x, p2.y);
		}
		else {
			return new QuadCurve2D.Double(p1.x, p1.y, ap.x, ap.y, p2.x, p2.y);
		}
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		int distance = 5;
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		lbb.x -= distance;
		lbb.y -= distance;
		lbb.width += distance * 2;
		lbb.height += distance * 2;
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
					if (distSq <= distance * distance) {
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
