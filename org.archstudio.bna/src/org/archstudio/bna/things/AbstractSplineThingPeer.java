package org.archstudio.bna.things;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractSplineThingPeer<T extends AbstractSplineThing> extends AbstractThingPeer<T> {

	public AbstractSplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Path2D localShape = new Path2D.Double();

		boolean firstPoint = true;
		for (Point p : t.getPoints()) {
			p = cm.worldToLocal(p);
			if (firstPoint) {
				firstPoint = false;
				localShape.moveTo(p.x, p.y);
			}
			else {
				localShape.lineTo(p.x, p.y);
			}
		}

		return localShape;
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
			List<Point> points = BNAUtils.worldToLocal(cm, t.getPoints());
			for (int i = 1; i < points.size(); i++) {
				Point p0 = points.get(i - 1);
				Point p1 = points.get(i);
				double distSq = Line2D.ptSegDistSq(p0.x, p0.y, p1.x, p1.y, lp.x, lp.y);
				if (distSq <= distance * distance) {
					return true;
				}
			}
		}
		return false;
	}

}