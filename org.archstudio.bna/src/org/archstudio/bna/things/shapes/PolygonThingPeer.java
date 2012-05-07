package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractPolygonThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;

public class PolygonThingPeer<T extends PolygonThing> extends AbstractPolygonThingPeer<T> implements IThingPeer<T> {

	public PolygonThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		int[] localXYPoints = BNAUtils.toXYArray(cm, t.getPoints(), t.getAnchorPoint());
		if (BNAUtils.setBackgroundColor(r, g, t, IHasColor.COLOR_KEY)) {
			g.fillPolygon(localXYPoints);
		}
		if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY) && BNAUtils.setLineStyle(r, g, t)) {
			g.drawPolygon(localXYPoints);
		}
	}
}
