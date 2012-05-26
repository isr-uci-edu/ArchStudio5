package org.archstudio.bna.things.borders;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

public class MarqueeBoxBorderThingPeer<T extends MarqueeBoxBorderThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T> {

	public MarqueeBoxBorderThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
		g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
		g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
		g.setLineDash(new int[]{4,4});
		g.setLineWidth(1);
		if (g instanceof SWTGraphics) {
			((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
		}
		g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
	}
}
