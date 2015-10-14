package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class EndpointThingPeer<T extends EndpointThing> extends AbstractThingPeer<T> implements IHasShadowPeer<T> {

	public EndpointThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	Shape createLocalShape(Rectangle lbb, int inset) {
		Dimension arc = t.getRoundCorners();
		if (arc.width == 0 && arc.height == 0) {
			return new Rectangle2D.Float(lbb.x + inset, lbb.y + inset, lbb.width - inset * 2, lbb.height - inset * 2);
		}
		double lWidth = arc.width * cm.getLocalScale() - inset * 2;
		double lHeight = arc.height * cm.getLocalScale() - inset * 2;
		return new RoundRectangle2D.Float(lbb.x + inset, lbb.y + inset, lbb.width - inset * 2, lbb.height - inset * 2,
				(float) lWidth, (float) lHeight);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = createLocalShape(lbb, 0);

		RGB glowColor = t.getGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getGlowWidth(), t.getGlowAlpha());
		}
		RGB color = t.getColor();
		if (color != null) {
			r.fillShape(localShape, color, t.isGradientFilled() ? t.getSecondaryColor() : null, t.getAlpha());
		}
		r.drawShape(localShape, t.getEdgeColor(), t.getLineWidth(), t.getLineStyle(), 1);
		for (int count = t.getCount() - 1; count > 0; count--) {
			r.drawShape(createLocalShape(lbb, count * 2 * t.getLineWidth()), t.getEdgeColor(),
					t.getLineWidth(), t.getLineStyle(), 1);
		}
		if (t.isSelected()) {
			r.selectShape(localShape, t.getRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean drawShadow(Rectangle localBounds, IUIResources r) {
		if (t.getGlowColor() == null && t.getColor() != null) {
			Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
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

		Rectangle wbb = t.getBoundingBox();
		if (!wbb.contains(worldPoint)) {
			return false;
		}

		Point localPoint = location.getLocalPoint();
		return createLocalShape(cm.worldToLocal(wbb), 0).contains(localPoint.x, localPoint.y);
	}
}
