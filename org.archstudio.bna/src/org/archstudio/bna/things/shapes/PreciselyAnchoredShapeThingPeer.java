package org.archstudio.bna.things.shapes;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractPreciselyAnchoredShapeThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class PreciselyAnchoredShapeThingPeer<T extends PreciselyAnchoredShapeThing> extends
		AbstractPreciselyAnchoredShapeThingPeer<T> {

	public PreciselyAnchoredShapeThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		Shape localShape = createLocalShape();

		if (!localBounds.intersects(BNAUtils.toRectangle(localShape.getBounds()))) {
			return false;
		}

		if (t.getColor() != null) {
			r.fillShape(localShape, t.getColor(), null);
		}
		if (t.isSelected()) {
			r.selectShape(localShape, t.getRotatingOffset());
		}

		return true;
	}
}
