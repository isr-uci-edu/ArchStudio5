package org.archstudio.archipelago.statechart.core.things;

import java.awt.geom.QuadCurve2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractCurvedSplineThingPeer<T extends AbstractCurvedSplineThing> extends AbstractThingPeer<T> {

	public AbstractCurvedSplineThingPeer(T thing) {
		super(thing);
	}

	protected static QuadCurve2D getShape(IBNAView view, ICoordinateMapper cm, AbstractCurvedSplineThing t) {
		Point p1 = cm.worldToLocal(t.getEndpoint1());
		Point p2 = cm.worldToLocal(t.getEndpoint2());
		Point c = cm.worldToLocal(getCurvedPoint(t));
		return new QuadCurve2D.Double(p1.x, p1.y, c.x, c.y, p2.x, p2.y);
	}

	public static Point getCurvedPoint(AbstractCurvedSplineThing t) {
		Point p1 = t.getEndpoint1();
		Point p2 = t.getEndpoint2();
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		double angle = Math.PI - Math.atan2(dy, dx);
		Point m = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
		double l = -t.getValue();
		double cx = m.x + Math.sin(angle) * l;
		double cy = m.y + Math.cos(angle) * l;
		return new Point(BNAUtils.round(cx), BNAUtils.round(cy));
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point l = location.getLocalPoint();
		return getShape(view, cm, t).intersects(l.x - 5, l.y - 5, 10, 10);
	}

}
