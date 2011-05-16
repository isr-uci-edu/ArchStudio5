package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class MappingThingPeer<T extends MappingThing> extends AbstractThingPeer<T> {

	public MappingThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalEndpointWorldThingID());
		if (iView == null) {
			return;
		}

		Point lp1 = BNAUtils.worldToLocal(view.getCoordinateMapper(), t.getAnchorPoint());
		Point lp2 = BNAUtils.worldToLocal(iView.getCoordinateMapper(), t.getInternalEndpoint());
		if (clip.intersects(BNAUtils.normalizeRectangle(new Rectangle(lp1.x, lp1.y, lp2.x - lp1.x, lp2.y - lp1.y)))) {
			g.setForegroundColor(res.getColor(t.getColor(), SWT.COLOR_BLACK));
			g.setLineWidth(t.getLineWidth());
			g.setLineStyle(t.getLineStyle());
			g.drawLine(lp1.x, lp1.y, lp2.x, lp2.y);
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}
}
