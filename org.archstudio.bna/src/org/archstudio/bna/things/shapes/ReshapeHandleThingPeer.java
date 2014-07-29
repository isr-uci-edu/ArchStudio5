package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class ReshapeHandleThingPeer<T extends ReshapeHandleThing> extends AbstractThingPeer<T> {

	public ReshapeHandleThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	Shape createLocalShape() {
		Point2D ap = cm.worldToLocal(t.getRawAnchorPoint());
		Dimension size = t.getRawSize();
		return new Rectangle2D.Double(ap.getX() - size.width / 2, ap.getY() - size.height / 2, size.width, size.height);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		Shape localShape = createLocalShape();

		r.fillShape(localShape, t.getRawColor(), null, 1);

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return createLocalShape().contains(BNAUtils.toPoint2D(location.getLocalPoint()));
	}
}
