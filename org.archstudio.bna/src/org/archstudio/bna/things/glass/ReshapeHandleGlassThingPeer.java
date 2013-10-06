package org.archstudio.bna.things.glass;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

public class ReshapeHandleGlassThingPeer<T extends ReshapeHandleGlassThing> extends AbstractAnchorPointThingPeer<T> {

	public ReshapeHandleGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point ap = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();

		Shape localShape = new Rectangle2D.Double(ap.x - size.width / 2, ap.y - size.height / 2, size.width,
				size.height);

		return localShape.contains(BNAUtils.toPoint2D(location.getLocalPoint()));
	}
}
