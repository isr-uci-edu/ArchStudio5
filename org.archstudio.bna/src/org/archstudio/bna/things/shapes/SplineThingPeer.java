package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.ShapeUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class SplineThingPeer<T extends SplineThing> extends AbstractThingPeer<T> {

	public SplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected Shape createLocalShape() {
		Path2D localShape = new Path2D.Double();
		List<? extends Point2D> points = t.getPoints();
		Point2D point = cm.worldToLocal(points.get(0));
		localShape.moveTo(point.getX(), point.getY());
		for (int i = 1; i < points.size(); i++) {
			point = cm.worldToLocal(points.get(i));
			localShape.lineTo(point.getX(), point.getY());
		}
		return localShape;
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		lbb.width += 2;
		lbb.height += 2;
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		List<Point2D> points = BNAUtils.worldToLocalPoint2D(cm, t.getPoints());
		Shape localShape = createLocalShape();
		ArrowheadShape arrowhead1 = (t.getArrowhead1Color() != null || t.getArrowhead1EdgeColor() != null)
				&& t.getArrowhead1Shape() != ArrowheadShape.NONE ? t.getArrowhead1Shape() : ArrowheadShape.NONE;
		Shape localArrowhead1 = ShapeUtils.createArrowhead(arrowhead1, points.get(0), points.get(1),
				t.getArrowhead1Width(), t.getArrowhead1Length());
		ArrowheadShape arrowhead2 = (t.getArrowhead2Color() != null || t.getArrowhead2EdgeColor() != null)
				&& t.getArrowhead2Shape() != ArrowheadShape.NONE ? t.getArrowhead2Shape() : ArrowheadShape.NONE;
		Shape localArrowhead2 = ShapeUtils.createArrowhead(arrowhead2, points.get(points.size() - 1),
				points.get(points.size() - 2), t.getArrowhead2Width(), t.getArrowhead2Length());

		RGB glowColor = t.getGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getGlowWidth(), t.getGlowAlpha());
			if (arrowhead1 != ArrowheadShape.NONE) {
				r.glowShape(localArrowhead1, glowColor, t.getGlowWidth(), t.getGlowAlpha());
			}
			if (arrowhead2 != ArrowheadShape.NONE) {
				r.glowShape(localArrowhead2, glowColor, t.getGlowWidth(), t.getGlowAlpha());
			}
		}
		{
			RGB arrowhead1Color = arrowhead1.isFilled() ? t.getArrowhead1Color() : null;
			if (arrowhead1Color != null) {
				r.fillShape(localArrowhead1, arrowhead1Color, null, 1);
			}
			RGB arrowhead2Color = arrowhead2.isFilled() ? t.getArrowhead2Color() : null;
			if (arrowhead2Color != null) {
				r.fillShape(localArrowhead2, arrowhead2Color, null, 1);
			}
		}
		if (t.isSelected()) {
			int offset = t.getRotatingOffset();
			r.selectShape(localShape, offset);
			if (arrowhead1.isEdged()) {
				r.selectShape(localArrowhead1, offset);
			}
			if (arrowhead2.isEdged()) {
				r.selectShape(localArrowhead2, offset);
			}
		}
		else {
			r.drawShape(localShape, t.getEdgeColor(), t.getLineWidth(), t.getLineStyle(), 1);
			RGB arrowhead1Color = arrowhead1.isEdged() ? t.getArrowhead1EdgeColor() : null;
			if (arrowhead1Color != null) {
				r.drawShape(localArrowhead1, arrowhead1Color, t.getLineWidth(), t.getLineStyle(), 1);
			}
			RGB arrowhead2Color = arrowhead2.isEdged() ? t.getArrowhead2EdgeColor() : null;
			if (arrowhead2Color != null) {
				r.drawShape(localArrowhead2, arrowhead2Color, t.getLineWidth(), t.getLineStyle(), 1);
			}
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		lbb.x -= BNAUtils.LINE_CLICK_DISTANCE;
		lbb.y -= BNAUtils.LINE_CLICK_DISTANCE;
		lbb.width += BNAUtils.LINE_CLICK_DISTANCE * 2;
		lbb.height += BNAUtils.LINE_CLICK_DISTANCE * 2;
		Point lp = location.getLocalPoint();
		if (lbb.contains(lp)) {
			Point2D cp = BNAUtils.getClosestPointOnShape(createLocalShape(), lp.x, lp.y);
			if (Point2D.distanceSq(lp.x, lp.y, cp.getX(), cp.getY()) <= BNAUtils.LINE_CLICK_DISTANCE
					* BNAUtils.LINE_CLICK_DISTANCE) {
				return true;
			}
		}
		return false;
	}

}
