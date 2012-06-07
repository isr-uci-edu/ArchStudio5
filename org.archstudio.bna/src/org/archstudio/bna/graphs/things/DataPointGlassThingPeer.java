package org.archstudio.bna.graphs.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

public class DataPointGlassThingPeer<T extends DataPointGlassThing> extends AbstractAnchorPointThingPeer<T> {

	public DataPointGlassThingPeer(T thing) {
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
		if (t.isSelected()) {
			Rectangle lbb = getLocalBounds(view, cm);
			switch (t.getShape()) {
			case CIRCLE:
				g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
				g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
				g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
				g.setLineDash(new int[]{4,4});
				g.setLineWidth(1);
				if (g instanceof SWTGraphics) {
					((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
				}
				g.drawOval(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
				break;
			case SQUARE:
				g.setForegroundColor(r.getColor(new RGB(255, 255, 255)));
				g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
				g.setForegroundColor(r.getColor(new RGB(0, 0, 0)));
				g.setLineDash(new int[]{4,4});
				g.setLineWidth(1);
				if (g instanceof SWTGraphics) {
					((SWTGraphics) g).setLineDashOffset(t.getRotatingOffset());
				}
				g.drawRectangle(lbb.x, lbb.y, lbb.width - 1, lbb.height - 1);
				break;
			}
		}
	}
}
