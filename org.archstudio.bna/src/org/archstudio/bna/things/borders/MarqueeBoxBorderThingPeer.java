package org.archstudio.bna.things.borders;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.swt.graphics.Rectangle;

public class MarqueeBoxBorderThingPeer<T extends MarqueeBoxBorderThing> extends AbstractRectangleThingPeer<T> {

	public MarqueeBoxBorderThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = new Rectangle2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);

		r.selectShape(localShape, t.getRotatingOffset());

		return true;
	}
}
