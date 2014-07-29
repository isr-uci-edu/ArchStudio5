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
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		lbb.width += 2;
		lbb.height += 2;
		if (!lbb.intersects(localBounds)) {
			return false;
		}

		if (DEBUG) {
			t.updateProperties();
		}

		Shape localShape = BNAUtils.worldToLocal(cm, t.shape);
		ArrowheadShape arrowhead1 = (t.getRawArrowhead1Color() != null || t.getRawArrowhead1EdgeColor() != null)
				&& t.getRawArrowhead1Shape() != ArrowheadShape.NONE ? t.getRawArrowhead1Shape() : ArrowheadShape.NONE;
		Shape localArrowhead1 = BNAUtils.worldToLocal(
				cm,
				ShapeUtils.createArrowhead(arrowhead1, t.getRawEndpoint1(), t.endpoint1StemPoint,
						t.getRawArrowhead1Width(), t.getRawArrowhead1Length()));
		ArrowheadShape arrowhead2 = (t.getRawArrowhead2Color() != null || t.getRawArrowhead2EdgeColor() != null)
				&& t.getRawArrowhead2Shape() != ArrowheadShape.NONE ? t.getRawArrowhead2Shape() : ArrowheadShape.NONE;
		Shape localArrowhead2 = BNAUtils.worldToLocal(
				cm,
				ShapeUtils.createArrowhead(arrowhead2, t.getRawEndpoint2(), t.endpoint2StemPoint,
						t.getRawArrowhead2Width(), t.getRawArrowhead2Length()));

		RGB glowColor = t.getRawGlowColor();
		if (glowColor != null) {
			r.glowShape(localShape, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
			if (arrowhead1 != ArrowheadShape.NONE) {
				r.glowShape(localArrowhead1, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
			}
			if (arrowhead2 != ArrowheadShape.NONE) {
				r.glowShape(localArrowhead2, glowColor, t.getRawGlowWidth(), t.getRawGlowAlpha());
			}
		}
		if (t.isSelected()) {
			int offset = t.getRawRotatingOffset();
			r.selectShape(localShape, offset);
		}
		{
			RGB arrowhead1Color = arrowhead1.isFilled() ? t.getRawArrowhead1Color() : null;
			if (arrowhead1Color != null) {
				r.fillShape(localArrowhead1, arrowhead1Color, null, 1);
			}
			RGB arrowhead2Color = arrowhead2.isFilled() ? t.getRawArrowhead2Color() : null;
			if (arrowhead2Color != null) {
				r.fillShape(localArrowhead2, arrowhead2Color, null, 1);
			}
		}
		if (t.isSelected()) {
			int offset = t.getRawRotatingOffset();
			if (arrowhead1.isEdged()) {
				r.selectShape(localArrowhead1, offset);
			}
			if (arrowhead2.isEdged()) {
				r.selectShape(localArrowhead2, offset);
			}
		}
		else {
			r.drawShape(localShape, t.getRawEdgeColor(), t.getRawLineWidth(), t.getRawLineStyle(), 1);
			RGB arrowhead1Color = arrowhead1.isEdged() ? t.getRawArrowhead1EdgeColor() : null;
			if (arrowhead1Color != null) {
				r.drawShape(localArrowhead1, arrowhead1Color, t.getRawLineWidth(), t.getRawLineStyle(), 1);
			}
			RGB arrowhead2Color = arrowhead2.isEdged() ? t.getRawArrowhead2EdgeColor() : null;
			if (arrowhead2Color != null) {
				r.drawShape(localArrowhead2, arrowhead2Color, t.getRawLineWidth(), t.getRawLineStyle(), 1);
			}
		}

		if (DEBUG) {
			Point2D a;
			a = cm.worldToLocal(t.getRawAnchorPoint());
			r.fillShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 0, 0), null, 1);
			a = cm.worldToLocal(t.getRawEndpoint1());
			r.fillShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 128, 0), null, 1);
			a = cm.worldToLocal(t.endpoint1StemPoint);
			r.drawShape(new Ellipse2D.Double(a.getX() - 3, a.getY() - 3, 6, 6), new RGB(255, 128, 0), 1,
					LineStyle.SOLID, 1);
			a = cm.worldToLocal(t.getRawEndpoint2());
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
