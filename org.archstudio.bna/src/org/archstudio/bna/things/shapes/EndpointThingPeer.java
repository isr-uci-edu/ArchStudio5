package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.things.AbstractEndpointThingPeer;
import org.archstudio.bna.things.IHasShadowThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class EndpointThingPeer<T extends EndpointThing> extends AbstractEndpointThingPeer<T> implements
		IHasShadowThingPeer<T> {

	public EndpointThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, IRegion localClip, IRegion worldClip) {
		if (!worldClip.intersects(t.getBoundingBox())) {
			return;
		}

		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (t.isGradientFilled() && r.setForegroundColor(g, t, IHasSecondaryColor.SECONDARY_COLOR_KEY)) {
			if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
				g.fillGradient(lbb, true);
			}
		}
		else {
			if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
				g.fillRectangle(lbb);
			}
		}
		if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			g.drawRectangle(lbb);
		}
	}

	@Override
	public void drawShadow(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, boolean fill) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (fill) {
			g.fillRectangle(lbb);
		}
		else {
			g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		}
	}
}
