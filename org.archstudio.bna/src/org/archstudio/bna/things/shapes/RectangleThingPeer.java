package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRectangleThingPeer<T> implements
		IHasShadowPeer<T> {

	public RectangleThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds) || t.getColor() == null) {
			return;
		}

		Dimension corner = t.getCornerSize();
		Shape localShape = new RoundRectangle2D.Float(lbb.x, lbb.y, lbb.width, lbb.height, Math.min(lbb.width,
				corner.width), Math.min(lbb.height, corner.height));

		if (t.getColor() != null) {
			BNAUtils.renderShapeFill(gl, localBounds, localShape, t.getColor(),
					t.isGradientFilled() ? t.getSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			for (int count = 0; count < t.getCount(); count++) {
				int offset = count * 2 * t.getLineWidth();
				Shape edgeShape = new RoundRectangle2D.Float(lbb.x + offset, lbb.y + offset, lbb.width - 2 * offset,
						lbb.height - 2 * offset, Math.min(lbb.width, corner.width), Math.min(lbb.height, corner.height));
				BNAUtils.renderShapeEdge(gl, localBounds, edgeShape);
			}
			r.resetLineStyle();
		}
		if (t.isSelected()) {
			BNAUtils.renderShapeSelected(gl, localBounds, localShape, t.getRotatingOffset());
		}
	}

	@Override
	public void drawShadow(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb) || t.getColor() == null) {
			return;
		}

		Dimension corner = t.getCornerSize();
		Shape localShape = new RoundRectangle2D.Float(lbb.x, lbb.y, lbb.width, lbb.height, Math.min(lbb.width,
				corner.width), Math.min(lbb.height, corner.height));

		r.setColor(new RGB(0, 0, 0), t.get(IHasAlpha.ALPHA_KEY, 1f));
		BNAUtils.renderShapeFill(gl, localBounds, localShape, null, null);
	}
}
