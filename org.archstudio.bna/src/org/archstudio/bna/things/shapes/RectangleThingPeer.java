package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> implements
		IThingPeer<T>, IHasShadowPeer {

	public RectangleThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(clip)) {
			return;
		}

		Dimension corner = t.getCornerSize();
		Shape localShape = new RoundRectangle2D.Float(lbb.x, lbb.y, lbb.width, lbb.height, Math.min(lbb.width,
				corner.width), Math.min(lbb.height, corner.height));

		BNAUtils.renderShapeFill(t, view, cm, gl, clip, r, localShape);
		for (int count = 0; count < t.getCount(); count++) {
			int offset = count * 2 * t.getLineWidth();
			Shape edgeShape = new RoundRectangle2D.Float(lbb.x + offset, lbb.y + offset, lbb.width - 2 * offset,
					lbb.height - 2 * offset, Math.min(lbb.width, corner.width), Math.min(lbb.height, corner.height));
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

		Dimension corner = t.getCornerSize();
		Shape localShape = new RoundRectangle2D.Float(lbb.x, lbb.y, lbb.width, lbb.height, Math.min(lbb.width,
				corner.width), Math.min(lbb.height, corner.height));

		r.setColor(new RGB(0, 0, 0), t.get(IHasAlpha.ALPHA_KEY, 1f));
		BNAUtils.renderShapeFill(view, cm, gl, clip, r, localShape);
	}
}
