package org.archstudio.bna.things.glass;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractPreciselyAnchoredShapeThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class PreciselyAnchoredShapeGlassThingPeer<T extends PreciselyAnchoredShapeGlassThing> extends
		AbstractPreciselyAnchoredShapeThingPeer<T> {

	public PreciselyAnchoredShapeGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {

		Shape localShape = createLocalShape();

		BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
	}

}
