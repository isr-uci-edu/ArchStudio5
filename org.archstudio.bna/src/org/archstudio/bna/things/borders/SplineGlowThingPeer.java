package org.archstudio.bna.things.borders;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractSplineThingPeer;
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
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		lbb.width += 1;
		lbb.height += 1;
		if (!localBounds.intersects(lbb)) {
			return;
		}

		Shape localShape = createLocalShape();

		BNAUtils.renderShapeGlow(gl, localBounds, localShape, t.getColor(), t.getWidth(), t.getAlpha());
	}
}
