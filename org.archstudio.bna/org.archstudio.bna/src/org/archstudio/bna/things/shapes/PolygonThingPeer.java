package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.things.AbstractPolygonThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;

public class PolygonThingPeer<T extends PolygonThing> extends AbstractPolygonThingPeer<T> {

	public PolygonThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, IRegion localClip, IRegion worldClip) {
		if (!worldClip.intersects(t.getBoundingBox())) {
			return;
		}

		int[] xyPoints = BNAUtils.toXYArray(cm, t.getPoints(), t.getAnchorPoint());
		if (xyPoints.length == 4) {
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				g.drawLine(xyPoints[0], xyPoints[1], xyPoints[2], xyPoints[3]);
			}
		}
		else if (xyPoints.length > 4) {
			if (t.isGradientFilled() && r.setForegroundColor(g, t, IHasSecondaryColor.SECONDARY_COLOR_KEY)) {
				if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
					g.fillPolygon(xyPoints);
				}
			}
			else {
				if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
					g.fillPolygon(xyPoints);
				}
			}
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				g.drawPolygon(xyPoints);
			}
		}
	}
}
