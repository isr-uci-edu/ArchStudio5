package org.archstudio.bna.things;

import java.awt.geom.RoundRectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractRoundedRectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> {

	public AbstractRoundedRectangleThingPeer(T thing) {
		super(thing);
	}

	public boolean isInThing(IBNAView View, ICoordinate location) {
		Rectangle wb = t.getBoundingBox();
		Point worldPoint = location.getWorldPoint(new Point());
		if (wb.contains(worldPoint)) {
			Dimension wc = t.getCornerSize();
			if (!wc.isEmpty()) {
				if (!new RoundRectangle2D.Double(wb.x, wb.y, wb.width, wb.height, wc.width, wc.height).contains(
						worldPoint.x, worldPoint.y)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
