package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class EndpointGlassThingPeer<T extends EndpointGlassThing> extends AbstractBoundedAnchorPointThingPeer<T>
		implements IThingPeer<T> {

	public EndpointGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (t.isSelected()) {
			Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
			g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
			g.drawRectangle(lbb);
			g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
			if (g instanceof SWTGraphics) {
				((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
			}
			g.setLineStyle(SWT.LINE_DASH);
			g.drawRectangle(lbb);
		}
	}
}
