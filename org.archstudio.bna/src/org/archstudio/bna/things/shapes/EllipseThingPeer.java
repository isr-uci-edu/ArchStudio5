package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractThingPeer<T> implements IHasShadowPeer<T> {

	public EllipseThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	Shape createLocalShape(Rectangle lbb, int inset) {
		return new Ellipse2D.Float(lbb.x + inset, lbb.y + inset, lbb.width - 2 * inset, lbb.height - 2 * inset);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = createLocalShape(lbb, 0);

		RGB glowColor = t.getRawGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
		}
		RGB color = t.getRawColor();
		if (color != null) {
			r.fillShape(localShape, color, t.isRawGradientFilled() ? t.getRawSecondaryColor() : null, t.getRawAlpha());
		}
		r.drawShape(localShape, t.getRawEdgeColor(), t.getRawLineWidth(), t.getRawLineStyle(), 1);
		for (int count = t.getRawCount() - 1; count > 0; count--) {
			r.drawShape(createLocalShape(lbb, count * 2 * t.getRawLineWidth()), t.getRawEdgeColor(),
					t.getRawLineWidth(), t.getRawLineStyle(), 1);
		}
		if (t.isRawSelected()) {
			r.selectShape(localShape, t.getRawRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean drawShadow(Rectangle localBounds, IUIResources r) {
		if (t.getRawGlowColor() == null && t.getColor() != null) {
			Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
			if (!localBounds.intersects(lbb)) {
				return false;
			}

			Shape localShape = createLocalShape(lbb, 0);
			r.fillShape(localShape, new RGB(0, 0, 0), null, 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point worldPoint = location.getWorldPoint();

		Rectangle wbb = t.getRawBoundingBox();
		if (!wbb.contains(worldPoint)) {
			return false;
		}

		Point localPoint = location.getLocalPoint();
		return createLocalShape(cm.worldToLocal(wbb), 0).contains(localPoint.x, localPoint.y);
	}
}
