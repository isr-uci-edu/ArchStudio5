package org.archstudio.bna.things.shapes;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class SplineThingPeer<T extends SplineThing> extends AbstractSplineThingPeer<T> {

	public SplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
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

		r.setLineStyle(t);
		BNAUtils.renderShapeEdge(gl, localBounds, localShape);
		r.resetLineStyle();
		if (t.isSelected()) {
			BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
		}
	}
}
