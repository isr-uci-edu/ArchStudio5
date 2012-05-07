package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements IThingPeer<T> {

	public EllipseThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (BNAUtils.setBackgroundColor(r, g, t, IHasColor.COLOR_KEY)) {
			g.fillOval(lbb);
		}
		if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
			g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		}
	}
}
