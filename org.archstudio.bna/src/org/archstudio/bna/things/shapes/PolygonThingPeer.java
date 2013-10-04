package org.archstudio.bna.things.shapes;

import java.awt.Shape;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractPolygonThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class PolygonThingPeer<T extends PolygonThing> extends AbstractPolygonThingPeer<T> implements IHasShadowPeer<T> {

	public PolygonThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return;
		}

		Shape localShape = createLocalShape();

		r.setLineStyle(t);
		BNAUtils.renderShapeEdge(gl, localBounds, localShape);
		r.resetLineStyle();
	}

	@Override
	public void drawShadow(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb) || t.getColor() == null) {
			return;
		}

		Shape localShape = createLocalShape();

		r.setColor(new RGB(0, 0, 0), t.get(IHasAlpha.ALPHA_KEY, 1f));
		BNAUtils.renderShapeFill(gl, localBounds, localShape, null, null);
	}
}
