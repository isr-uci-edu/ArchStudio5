package org.archstudio.bna.things.labels;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.ShapeUtils;
import org.eclipse.swt.graphics.Rectangle;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> {

	public ArrowheadThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Shape localShape = ShapeUtils.createArrowhead(t, cm);
		if (localShape == null) {
			return false;
		}

		if (!localBounds.intersects(BNAUtils.toRectangle(localShape.getBounds2D()))) {
			return false;
		}

		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			r.fillShape(localShape);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			r.resetLineStyle();
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Shape localShape = ShapeUtils.createArrowhead(t, cm);
		if (localShape == null) {
			return false;
		}

		return localShape.contains(BNAUtils.toPoint2D(location.getLocalPoint()));
	}
}
