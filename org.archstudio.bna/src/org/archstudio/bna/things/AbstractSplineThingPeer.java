package org.archstudio.bna.things;

import java.awt.geom.Line2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractSplineThingPeer<T extends AbstractSplineThing> extends AbstractThingPeer<T> {

	public AbstractSplineThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point lp = location.getLocalPoint();
		List<Point> points = BNAUtils.worldToLocal(cm, t.getPoints());
		for (int i = 1; i < points.size(); i++) {
			Point p0 = points.get(i - 1);
			Point p1 = points.get(i);
			double distSq = Line2D.ptSegDistSq(p0.x, p0.y, p1.x, p1.y, lp.x, lp.y);
			if (distSq <= 25) {
				return true;
			}
		}
		return false;
	}

}