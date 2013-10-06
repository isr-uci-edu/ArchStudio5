package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractRoundedRectangleThingPeer<T extends AbstractRoundedRectangleThing> extends
		AbstractRectangleThingPeer<T> {

	public AbstractRoundedRectangleThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape(int inset) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		Dimension corner = t.getCornerSize();
		Shape localShape = new RoundRectangle2D.Float(lbb.x + inset, lbb.y + inset, lbb.width - 2 * inset, lbb.height
				- 2 * inset, Math.min(lbb.width, corner.width), Math.min(lbb.height, corner.height));
		return localShape;
	}

	public boolean isInThing(IBNAView View, ICoordinate location) {
		Rectangle wbb = t.getBoundingBox();
		Point worldPoint = location.getWorldPoint();
		if (wbb.contains(worldPoint)) {
			Dimension wc = t.getCornerSize();
			if (wc.width != 0 || wc.height != 0) {
				if (!new RoundRectangle2D.Double(wbb.x, wbb.y, wbb.width, wbb.height, wc.width, wc.height).contains(
						worldPoint.x, worldPoint.y)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
