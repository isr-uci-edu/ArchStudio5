package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T>, IHasShadowPeer<T> {

	public RectangleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (BNAUtils.setBackgroundColor(r, g, t, IHasColor.COLOR_KEY)) {
			g.fillRectangle(lbb);
		}
		if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		}
	}

	@Override
	public void drawShadow(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		g.fillRectangle(lbb);
	}
}
