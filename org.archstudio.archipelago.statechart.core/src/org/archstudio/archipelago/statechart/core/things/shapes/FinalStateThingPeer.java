package org.archstudio.archipelago.statechart.core.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class FinalStateThingPeer<T extends FinalStateThing> extends AbstractThingPeer<T> implements IHasShadowPeer<T> {

	public FinalStateThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape outerShape = new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);
		float delta = SystemUtils.bound(5, 6 * (float) cm.getLocalScale(), Float.MAX_VALUE);
		Shape innerShape = new Ellipse2D.Double(lbb.x + delta, lbb.y + delta, lbb.width - 2 * delta + 1, lbb.height - 2
				* delta + 1);

		r.fillShape(outerShape, new RGB(255, 255, 255), null, 1);
		r.fillShape(innerShape, t.getRawColor(), t.isRawGradientFilled() ? t.getRawSecondaryColor() : null, 1);
		r.drawShape(outerShape, t.getRawEdgeColor(), t.getRawLineWidth(), t.getRawLineStyle(), 1);
		if (t.isSelected()) {
			r.selectShape(outerShape, t.getRawRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean drawShadow(Rectangle localBounds, IUIResources r) {
		if (t.getRawGlowColor() == null) {
			Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
			if (!localBounds.intersects(lbb)) {
				return false;
			}

			Shape outerShape = new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);
			r.fillShape(outerShape, null, null, 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Rectangle r = t.getRawBoundingBox();
		return new Ellipse2D.Double(r.x, r.y, r.width, r.height).contains(BNAUtils.toPoint2D(location.getWorldPoint()));
	}

}
