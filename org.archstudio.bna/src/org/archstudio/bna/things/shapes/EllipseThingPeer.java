package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.things.IHasShadowThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements
		IHasShadowThingPeer<T> {

	public EllipseThingPeer(T thing) {
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
				// TODO: correctly create gradient
				g.fillOval(lbb);
			}
		}
		else {
			if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
				g.fillOval(lbb);
			}
		}
		if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			/* This adjustment makes a drawn oval overlap the edge pixel of a filled oval. */
			g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		}
	}

	@Override
	public void drawShadow(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, boolean fill) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (fill) {
			g.fillOval(lbb);
		}
		else {
			g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		}
	}
}
