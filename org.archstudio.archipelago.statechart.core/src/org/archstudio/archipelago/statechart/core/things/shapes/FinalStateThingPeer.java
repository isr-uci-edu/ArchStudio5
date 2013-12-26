package org.archstudio.archipelago.statechart.core.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.shapes.EllipseThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class FinalStateThingPeer<T extends FinalStateThing> extends
		EllipseThingPeer<T> {

	public FinalStateThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape outerShape = new Ellipse2D.Double(lbb.x, lbb.y, lbb.width,
				lbb.height);
		float delta = SystemUtils.bound(5, 8 * (float) cm.getLocalScale(),
				Float.MAX_VALUE);
		Shape innerShape = new Ellipse2D.Double(lbb.x + delta, lbb.y + delta,
				lbb.width - 2 * delta, lbb.height - 2 * delta);

		r.fillShape(outerShape, new RGB(255, 255, 255), null);
		if (t.getColor() != null) {
			r.fillShape(innerShape, t.getColor(),
					t.isGradientFilled() ? t.getSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(outerShape);
			r.drawShape(innerShape);
			r.resetLineStyle();
		}
		if (t.isSelected()) {
			r.selectShape(outerShape, t.getRotatingOffset());
		}

		return true;
	}

}
