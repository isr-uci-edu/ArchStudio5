package org.archstudio.bna.things;

import java.awt.Polygon;
import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractPolygonThingPeer<T extends AbstractPolygonThing> extends AbstractThingPeer<T> {

	public AbstractPolygonThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Point a = t.getAnchorPoint();

		Polygon localShape = new Polygon();
		for (Point p : t.getPoints()) {
			p.x += a.x;
			p.y += a.y;
			p = cm.worldToLocal(p);
			localShape.addPoint(p.x, p.y);
		}

		return localShape;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		if (t.getBoundingBox().contains(location.getWorldPoint())) {
			return createLocalShape().contains(BNAUtils.toPoint2D(location.getLocalPoint()));
		}
		return false;
	}
}
