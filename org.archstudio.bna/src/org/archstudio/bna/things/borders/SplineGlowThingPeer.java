package org.archstudio.bna.things.borders;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class SplineGlowThingPeer<T extends SplineGlowThing> extends AbstractSplineThingPeer<T> {

	public SplineGlowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
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

		r.glowShape(localShape, t.getColor(), t.getWidth(), t.getAlpha());

		return true;
	}
}
