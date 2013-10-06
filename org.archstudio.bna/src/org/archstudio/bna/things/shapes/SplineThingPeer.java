package org.archstudio.bna.things.shapes;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class SplineThingPeer<T extends SplineThing> extends AbstractSplineThingPeer<T> {

	public SplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		lbb.width += 1;
		lbb.height += 1;
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = createLocalShape();

		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			r.resetLineStyle();
		}
		if (t.isSelected()) {
			r.selectShape(localShape, t.getRotatingOffset());
		}

		return true;
	}
}
