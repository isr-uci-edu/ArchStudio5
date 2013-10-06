package org.archstudio.bna.things.borders;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineGlowThingPeer<T extends CurvedSplineGlowThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineGlowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds)) {
			return false;
		}

		Shape localShape = createLocalShape();

		r.glowShape(localShape, t.getColor(), t.getWidth(), t.getAlpha());

		return true;
	};

}
