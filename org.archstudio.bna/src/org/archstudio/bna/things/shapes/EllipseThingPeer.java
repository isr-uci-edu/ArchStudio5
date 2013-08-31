package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements IHasShadowPeer {

	public EllipseThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!clip.intersects(lbb)) {
			return;
		}

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		BNAUtils.renderShapeFill(t, view, cm, gl, clip, r, localShape);
		for (int count = 0; count < t.getCount(); count++) {
			int offset = count * 2 * t.getLineWidth();
			Shape edgeShape = new Ellipse2D.Float(lbb.x + offset, lbb.y + offset, lbb.width - 2 * offset, lbb.height
					- 2 * offset);
			BNAUtils.renderShapeEdge(t, view, cm, gl, clip, r, edgeShape);
		}
		BNAUtils.renderShapeSelected(t, view, cm, gl, clip, r, localShape);
	}

	@Override
	public void drawShadow(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!clip.intersects(lbb)) {
			return;
		}

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		r.setColor(new RGB(0, 0, 0), t.get(IHasAlpha.ALPHA_KEY, 1f));
		BNAUtils.renderShapeFill(view, cm, gl, clip, r, localShape);
	}
}
