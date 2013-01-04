package org.archstudio.bna.things;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractPolygonThingPeer<T extends AbstractPolygonThing> extends AbstractThingPeer<T> {

	public AbstractPolygonThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (t.getBoundingBox().contains(location.getWorldPoint())) {
			List<Point> points = t.getPoints();
			Point anchorPoint = t.getAnchorPoint();
			GeneralPath path = new GeneralPath(Path2D.WIND_NON_ZERO, points.size());
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

}
