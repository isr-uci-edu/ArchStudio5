package org.archstudio.bna.things.glass;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractPreciselyAnchoredShapeThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class PreciselyAnchoredShapeGlassThingPeer<T extends PreciselyAnchoredShapeGlassThing> extends
		AbstractPreciselyAnchoredShapeThingPeer<T> {

	public PreciselyAnchoredShapeGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		if (!t.isSelected()) {
			return false;
		}

		Shape localShape = createLocalShape();

		if (!localBounds.intersects(BNAUtils.toRectangle(localShape.getBounds()))) {
			return false;
		}

		r.selectShape(localShape, t.getRotatingOffset());

		return true;
	}
}
