package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class ReshapeHandleThingPeer<T extends ReshapeHandleThing> extends AbstractBoundedAnchorPointThingPeer<T> {

	public ReshapeHandleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());

		if (r.setBackgroundColor(g, t, IHasColor.COLOR_KEY)) {
			g.fillRectangle(lbb);
		}
	}
}
