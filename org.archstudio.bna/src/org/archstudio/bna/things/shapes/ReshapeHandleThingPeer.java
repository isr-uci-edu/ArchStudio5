package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ReshapeHandleThingPeer<T extends ReshapeHandleThing> extends AbstractAnchorPointThingPeer<T> {

	public ReshapeHandleThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	Shape createLocalShape() {
		Point ap = cm.worldToLocal(t.getAnchorPoint());
		Dimension size = t.getSize();
		return new Rectangle2D.Float(ap.x - size.width / 2, ap.y - size.height / 2, size.width, size.height);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		Shape localShape = createLocalShape();

		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			r.fillShape(localShape, null, null);
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return createLocalShape().contains(BNAUtils.toPoint2D(location.getLocalPoint()));
	}
}
