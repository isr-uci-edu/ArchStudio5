package org.archstudio.bna.things;

import java.awt.geom.Path2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractPolygonThingPeer<T extends AbstractPolygonThing> extends AbstractThingPeer<T> {

	protected int[] localXYPoints = null;

	public AbstractPolygonThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void updateCache(IBNAView view, ICoordinateMapper cm) {
		localXYPoints = BNAUtils.toXYArray(cm, t.getPoints(), t.getAnchorPoint());
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (t.getBoundingBox().contains(location.getWorldPoint())) {
			List<Point> points = t.getPoints();
			Point anchorPoint = t.getAnchorPoint();
			Path2D path = new Path2D.Double(Path2D.WIND_NON_ZERO, points.size());
			Point point = points.get(0);
			point.x += anchorPoint.x;
			point.y += anchorPoint.y;
			path.moveTo(point.x, point.y);
			for (int i = 1; i < points.size(); i++) {
				point = points.get(i);
				point.x += anchorPoint.x;
				point.y += anchorPoint.y;
				path.lineTo(point.x, point.y);
			}
			point = location.getWorldPoint();
			return path.contains(point.x, point.y);
		}
		return false;
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		return cm.worldToLocal(t.getBoundingBox());
	}
}
