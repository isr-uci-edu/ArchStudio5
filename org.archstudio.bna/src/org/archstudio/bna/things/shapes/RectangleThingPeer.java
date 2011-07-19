package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> implements
		IHasShadowPeer<T> {

	public RectangleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());

		if (t.isGradientFilled() && g.getAdvanced()
				&& r.setForegroundColor(g, t, IHasSecondaryColor.SECONDARY_COLOR_KEY)) {
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
			/*
			 * This adjustment makes a drawn rectangle overlap the edge pixel of
			 * a filled rectangle.
			 */
			g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
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
