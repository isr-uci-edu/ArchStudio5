package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractPolygonThingPeer;
import org.eclipse.draw2d.Graphics;

public class PolygonThingPeer<T extends PolygonThing> extends AbstractPolygonThingPeer<T> implements IHasShadowPeer<T> {

	public PolygonThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		if (localXYPoints.length == 4) {
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				g.drawLine(localXYPoints[0], localXYPoints[1], localXYPoints[2], localXYPoints[3]);
			}
		}
		else if (localXYPoints.length > 4) {
			if (t.isGradientFilled() && r.setForegroundColor(g, t, IHasSecondaryColor.SECONDARY_COLOR_KEY)) {
				if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
					g.fillPolygon(localXYPoints);
				}
			}
			else {
				if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
					g.fillPolygon(localXYPoints);
				}
			}
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				g.drawPolygon(localXYPoints);
			}
		}
	}

	@Override
	public void drawShadow(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, boolean fill) {
		if (fill) {
			g.fillPolygon(localXYPoints);
		}
		else {
			g.drawPolygon(localXYPoints);
		}
	}
}
