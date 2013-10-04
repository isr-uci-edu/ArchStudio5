package org.archstudio.bna.things.shapes;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineThingPeer<T extends CurvedSplineThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds)) {
			return;
		}

		Shape localShape = createLocalShape();

		r.setLineStyle(t);
		BNAUtils.renderShapeEdge(gl, localBounds, localShape);
		r.resetLineStyle();
		if (t.isSelected()) {
			BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
		}
	};

}
