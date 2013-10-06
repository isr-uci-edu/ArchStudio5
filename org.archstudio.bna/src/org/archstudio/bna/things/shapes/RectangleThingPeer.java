package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractRoundedRectangleThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class RectangleThingPeer<T extends RectangleThing> extends AbstractRoundedRectangleThingPeer<T> implements
		IHasShadowPeer<T> {

	public RectangleThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds)) {
			return false;
		}

		Shape localShape = createLocalShape(0);

		if (t.getColor() != null) {
			r.fillShape(localShape, t.getColor(), t.isGradientFilled() ? t.getSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			for (int count = 1; count < t.getCount(); count++) {
				r.drawShape(createLocalShape(count * 2 * t.getLineWidth()));
			}
			r.resetLineStyle();
		}
		if (t.isSelected()) {
			r.selectShape(localShape, t.getRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean drawShadow(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb) || t.getColor() == null) {
			return false;
		}

		Dimension corner = t.getCornerSize();
		Shape localShape = new RoundRectangle2D.Float(lbb.x, lbb.y, lbb.width, lbb.height, Math.min(lbb.width,
				corner.width), Math.min(lbb.height, corner.height));

		r.setColor(new RGB(0, 0, 0), 1f); // t.get(IHasAlpha.ALPHA_KEY, 1f));
		r.fillShape(localShape, null, null);

		return true;
	}
}
