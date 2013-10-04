package org.archstudio.bna.things.shapes;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractPreciselyAnchoredShapeThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class PreciselyAnchoredShapeThingPeer<T extends PreciselyAnchoredShapeThing> extends
		AbstractPreciselyAnchoredShapeThingPeer<T> {

	public PreciselyAnchoredShapeThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {

		Shape localShape = createLocalShape();

		if (localBounds.intersects(BNAUtils.toRectangle(localShape.getBounds()))) {
			if (t.getColor() != null) {
				BNAUtils.renderShapeFill(gl, localBounds, localShape, t.getColor(), null);
			}
			if (t.isSelected()) {
				BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
			}
		}
	}
}
