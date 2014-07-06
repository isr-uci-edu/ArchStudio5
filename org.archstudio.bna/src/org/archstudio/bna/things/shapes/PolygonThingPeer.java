package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class PolygonThingPeer<T extends PolygonThing> extends AbstractThingPeer<T> implements IHasShadowPeer<T> {

	public PolygonThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	Shape createLocalShape() {
		Path2D path = new Path2D.Double();
		List<? extends Point2D> points = t.getRawPoints();
		if (points.size() > 0) {
			Point2D point = cm.worldToLocal(points.get(0));
			path.moveTo(point.getX(), point.getY());
			for (int i = 1; i < points.size(); i++) {
				point = cm.worldToLocal(points.get(i));
				path.lineTo(point.getX(), point.getY());
			}
			path.closePath();
		}
		return path;
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		Shape localShape = createLocalShape();

		RGB glowColor = t.getRawGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
		}
		RGB color = t.getRawColor();
		if (color != null) {
			r.fillShape(localShape, color, t.isRawGradientFilled() ? t.getRawSecondaryColor() : null);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			r.resetLineStyle();
		}
		if (t.isRawSelected()) {
			r.selectShape(localShape, t.getRawRotatingOffset());
		}

		return true;
	}

	@Override
	public boolean drawShadow(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!localBounds.intersects(lbb) || t.getColor() == null) {
			return false;
		}

		Shape localShape = createLocalShape();

		r.setColor(new RGB(0, 0, 0), (float) t.get(IHasAlpha.ALPHA_KEY, 1f));
		r.fillShape(localShape, null, null);
		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point worldPoint = location.getWorldPoint();

		Rectangle wbb = t.getRawBoundingBox();
		if (!wbb.contains(worldPoint)) {
			return false;
		}

		Point localPoint = location.getLocalPoint();
		return createLocalShape().contains(localPoint.x, localPoint.y);
	}
}
