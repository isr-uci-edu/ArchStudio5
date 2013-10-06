package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractEllipseThingPeer<T> implements IHasShadowPeer<T> {

	public EllipseThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		if (t.getColor() != null) {
			r.fillShape(localShape, t.getColor(), t.isGradientFilled() ? t.getSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			for (int count = 1; count < t.getCount(); count++) {
				int inset = count * 2 * t.getLineWidth();
				Shape insetShape = new Ellipse2D.Float(lbb.x + inset, lbb.y + inset, lbb.width - 2 * inset, lbb.height
						- 2 * inset);
				r.drawShape(insetShape);
			}
			r.resetLineStyle();
		}
		if (t.isSelected()) {
			r.selectShape(localShape, t.getRotatingOffset());
		}

		return true;
	}

	@Override
	public void drawShadow(GL2 gl, Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb) || t.getColor() == null) {
			return;
		}

		Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);

		r.setColor(new RGB(0, 0, 0), t.get(IHasAlpha.ALPHA_KEY, 1f));
		r.fillShape(localShape, null, null);
	}
}
