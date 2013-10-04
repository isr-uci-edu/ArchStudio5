package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractRoundedRectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> {

	public AbstractRoundedRectangleThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	public boolean isInThing(IBNAView View, ICoordinate location) {
		Rectangle wb = t.getBoundingBox();
		Point worldPoint = location.getWorldPoint();
		if (wb.contains(worldPoint)) {
			Dimension wc = t.getCornerSize();
			if (wc.width != 0 || wc.height != 0) {
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
