package org.archstudio.bna.things;

import java.awt.geom.Path2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

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
		if (t.getBoundingBox().contains(location.getWorldPoint(new Point()))) {
			List<Point> points = t.getPoints();
			Point anchorPoint = t.getAnchorPoint();
			Path2D path = new Path2D.Double(Path2D.WIND_NON_ZERO, points.size());
			Point point = points.get(0).translate(anchorPoint);
			path.moveTo(point.x, point.y);
			for (int i = 1; i < points.size(); i++) {
				point = points.get(i).translate(anchorPoint);
				path.lineTo(point.x, point.y);
			}
			location.getWorldPoint(point);
			return path.contains(point.x, point.y);
		}
		return false;
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		cm.worldToLocal(boundsResult.setBounds(t.getBoundingBox()));
		if (this instanceof IHasShadowPeer) {
			ShadowThingPeer.expandForShadow(cm, boundsResult);
		}
	}
}
