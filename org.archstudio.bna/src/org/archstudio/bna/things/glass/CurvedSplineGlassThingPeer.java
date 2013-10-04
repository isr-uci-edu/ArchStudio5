package org.archstudio.bna.things.glass;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineGlassThingPeer<T extends CurvedSplineGlassThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineGlassThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds) || !t.isSelected()) {
			return;
		}

		Shape localShape = createLocalShape();

		BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
	}

}
