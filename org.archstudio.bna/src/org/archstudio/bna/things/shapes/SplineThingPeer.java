package org.archstudio.bna.things.shapes;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class SplineThingPeer<T extends SplineThing> extends AbstractSplineThingPeer<T> {

	public SplineThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, IRegion localClip, IRegion worldClip) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		int lbbExpand = t.getLineWidth();
		lbb.expand(lbbExpand, lbbExpand);
		if (!localClip.intersects(lbb)) {
			return;
		}

		List<Point> localPoints = BNAUtils.getWorldToLocal(cm, t.getPoints());
		if (localPoints.size() == 2) {
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(g, t)) {
				Point p0 = localPoints.get(0);
				Point p1 = localPoints.get(1);
				g.drawLine(p0.x, p0.y, p1.x, p1.y);
			}
		}
		else if (localPoints.size() > 2) {
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(g, t)) {
				g.drawPolyline(BNAUtils.toXYArray(localPoints));
			}
		}
	}
}
