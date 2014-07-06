package org.archstudio.bna.things.borders;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class MarqueeBoxBorderThingPeer<T extends MarqueeBoxBorderThing> extends AbstractThingPeer<T> {

	public MarqueeBoxBorderThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = new Rectangle2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);

		r.selectShape(localShape, t.getRawRotatingOffset());

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}
}
