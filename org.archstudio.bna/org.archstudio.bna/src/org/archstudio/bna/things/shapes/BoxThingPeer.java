package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.things.AbstractBoxThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class BoxThingPeer<T extends BoxThing> extends AbstractBoxThingPeer<T> {

	public BoxThingPeer(T thing) {
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
			/* This adjustment makes a drawn rectangle overlap the edge pixel of a filled rectangle. */
			g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		}
	}

	@Override
	public boolean isOpaqueAndFilled() {
		return true;
	}
}
