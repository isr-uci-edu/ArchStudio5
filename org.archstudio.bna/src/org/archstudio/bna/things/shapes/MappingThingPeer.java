package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class MappingThingPeer<T extends MappingThing> extends AbstractThingPeer<T> {

	public MappingThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalWorld(model));
		if (iView == null) {
			return false;
		}

		Point2D lp1 = cm.worldToLocal(t.getRawAnchorPoint());
		Point2D lp2 = iView.getCoordinateMapper().worldToLocal(t.getRawInternalPoint());

		Shape localShape = new Line2D.Double(lp1.getX(), lp1.getY(), lp2.getX(), lp2.getY());

		RGB glowColor = t.getRawGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
		}

		r.drawShape(localShape, t.getRawEdgeColor(), t.getRawLineWidth(), t.getRawLineStyle(), 1);
		if (t.isRawSelected()) {
			r.selectShape(localShape, t.getRawRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		IBNAView iView = BNAUtils.getInternalView(view, t.getInternalWorld(model));
		if (iView == null) {
			return false;
		}

		Point2D lp1 = cm.worldToLocal(t.getAnchorPoint());
		Point2D lp2 = iView.getCoordinateMapper().worldToLocal(t.getInternalPoint());
		Point p = location.getLocalPoint();

		if (Line2D.ptSegDistSq(lp1.getX(), lp1.getY(), lp2.getX(), lp2.getY(), p.x, p.y) <= BNAUtils.LINE_CLICK_DISTANCE
				* BNAUtils.LINE_CLICK_DISTANCE) {
			// leave a little room at the internal point so that we can interact with what the mapping is pointing to
			return Point2D.distanceSq(lp2.getX(), lp2.getY(), p.x, p.y) > BNAUtils.LINE_CLICK_DISTANCE
					* BNAUtils.LINE_CLICK_DISTANCE;
		}
		return false;
	}

}
