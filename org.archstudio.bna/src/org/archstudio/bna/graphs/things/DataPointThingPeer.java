package org.archstudio.bna.graphs.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class DataPointThingPeer<T extends DataPointThing> extends AbstractAnchorPointThingPeer<T> {

	public DataPointThingPeer(T thing) {
		super(thing);
	}

	protected Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point lp = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle(lp.x - size.width / 2, lp.y - size.height / 2, size.width, size.height);
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		return getLocalBounds(view, cm);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return getLocalBounds(view, cm).contains(location.getLocalPoint());
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		Rectangle lbb = getLocalBounds(view, cm);

		switch (t.getShape()) {
		case CIRCLE:
			if (BNAUtils.setBackgroundColor(r, g, t, IHasColor.COLOR_KEY)) {
				g.fillOval(lbb);
			}
			if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
			}
			break;
		case SQUARE:
			if (BNAUtils.setBackgroundColor(r, g, t, IHasColor.COLOR_KEY)) {
				g.fillRectangle(lbb);
			}
			if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
			}
			break;
		}
	}
}
