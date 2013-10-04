package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements IHasShadowPeer<T> {

	public EllipseThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return;
		}

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		if (t.getColor() != null) {
			BNAUtils.renderShapeFill(gl, localBounds, localShape, t.getColor(),
					t.isGradientFilled() ? t.getSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			for (int count = 0; count < t.getCount(); count++) {
				int offset = count * 2 * t.getLineWidth();
				Shape edgeShape = new Ellipse2D.Float(lbb.x + offset, lbb.y + offset, lbb.width - 2 * offset,
						lbb.height - 2 * offset);
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

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		r.setColor(new RGB(0, 0, 0), t.get(IHasAlpha.ALPHA_KEY, 1f));
		BNAUtils.renderShapeFill(gl, localBounds, localShape, null, null);
	}
}
