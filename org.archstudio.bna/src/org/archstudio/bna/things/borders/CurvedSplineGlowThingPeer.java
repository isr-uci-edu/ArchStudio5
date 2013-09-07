package org.archstudio.bna.things.borders;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractCurvedSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class CurvedSplineGlowThingPeer<T extends CurvedSplineGlowThing> extends AbstractCurvedSplineThingPeer<T> {

	public CurvedSplineGlowThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(clip)) {
			return;
		}

		Shape localShape = getShape(view, cm);

		BNAUtils.renderShapeGlow(t, view, cm, gl, clip, r, localShape);
	};

}
