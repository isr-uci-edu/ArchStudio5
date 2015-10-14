package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.ShapeUtils;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class CurvedSplineThingPeer<T extends CurvedSplineThing> extends AbstractThingPeer<T> {

	private static final boolean DEBUG = false;

	public CurvedSplineThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		lbb.width += 2;
		lbb.height += 2;
		if (!lbb.intersects(localBounds)) {
			return false;
		}

		if (DEBUG) {
			t.updateProperties();
		}

		Shape localShape = BNAUtils.worldToLocal(cm, t.shape);
		ArrowheadShape arrowhead1 = (t.getArrowhead1Color() != null || t.getArrowhead1EdgeColor() != null)
				&& t.getArrowhead1Shape() != ArrowheadShape.NONE ? t.getArrowhead1Shape() : ArrowheadShape.NONE;
		Shape localArrowhead1 = BNAUtils.worldToLocal(
				cm,
				ShapeUtils.createArrowhead(arrowhead1, t.getEndpoint1(), t.endpoint1StemPoint,
						t.getArrowhead1Width(), t.getArrowhead1Length()));
		ArrowheadShape arrowhead2 = (t.getArrowhead2Color() != null || t.getArrowhead2EdgeColor() != null)
				&& t.getArrowhead2Shape() != ArrowheadShape.NONE ? t.getArrowhead2Shape() : ArrowheadShape.NONE;
		Shape localArrowhead2 = BNAUtils.worldToLocal(
				cm,
				ShapeUtils.createArrowhead(arrowhead2, t.getEndpoint2(), t.endpoint2StemPoint,
						t.getArrowhead2Width(), t.getArrowhead2Length()));

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
		if (t.isSelected()) {
			int offset = t.getRotatingOffset();
			r.selectShape(localShape, offset);
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

		if (DEBUG) {
			Point2D a;
			a = cm.worldToLocal(t.getAnchorPoint());
			r.fillShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 0, 0), null, 1);
			a = cm.worldToLocal(t.getEndpoint1());
			r.fillShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 128, 0), null, 1);
			a = cm.worldToLocal(t.endpoint1StemPoint);
			r.drawShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 128, 0), 1,
					LineStyle.SOLID, 1);
			a = cm.worldToLocal(t.getEndpoint2());
			r.fillShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 64, 255), null, 1);
			a = cm.worldToLocal(t.endpoint2StemPoint);
			r.drawShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 64, 255), 1,
					LineStyle.SOLID, 1);
		}

		return true;
	};

	@Override
	public boolean isInThing(ICoordinate location) {
		Point worldPoint = location.getWorldPoint();

		Rectangle wbb = t.getBoundingBox();
		wbb.x -= ReshapeSplineLogic.SELECT_DIST;
		wbb.y -= ReshapeSplineLogic.SELECT_DIST;
		wbb.width += 2 * ReshapeSplineLogic.SELECT_DIST;
		wbb.height += 2 * ReshapeSplineLogic.SELECT_DIST;
		if (!wbb.contains(worldPoint)) {
			return false;
		}

		Point localPoint = location.getLocalPoint();
		Shape localShape = BNAUtils.worldToLocal(cm, t.shape);
		Point2D closestPoint = BNAUtils.getClosestPointOnShape(localShape, localPoint.x, localPoint.y);
		return closestPoint.distance(localPoint.x, localPoint.y) <= ReshapeSplineLogic.SELECT_DIST;
	}
}
