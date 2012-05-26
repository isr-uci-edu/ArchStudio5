package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

public class EllipseGlassThingPeer<T extends EllipseGlassThing> extends AbstractEllipseThingPeer<T> implements
		IThingPeer<T> {

	public EllipseGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (t.isSelected()) {
			Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
			g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
			g.drawOval(lbb);
			g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
			g.setLineDash(new int[]{4,4});
			g.setLineWidth(1);
			if (g instanceof SWTGraphics) {
				((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
			}
			g.drawOval(lbb);
		}
	}
}
