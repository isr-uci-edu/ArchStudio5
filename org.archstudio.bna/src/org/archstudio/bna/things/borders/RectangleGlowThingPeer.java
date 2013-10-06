package org.archstudio.bna.things.borders;

import java.awt.Shape;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractRoundedRectangleThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleGlowThingPeer<T extends RectangleGlowThing> extends AbstractRoundedRectangleThingPeer<T> {

	public RectangleGlowThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = createLocalShape(0);

		r.glowShape(localShape, t.getColor(), t.getWidth(), t.getAlpha());

		return false;
	}
}
