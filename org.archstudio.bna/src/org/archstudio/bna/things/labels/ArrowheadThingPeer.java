package org.archstudio.bna.things.labels;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Path2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.ArrowheadUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> {

	public ArrowheadThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Point lp1 = cm.worldToLocal(t.getAnchorPoint());
		if (!localBounds.contains(lp1)) {
			return false;
		}

		Point lp2 = cm.worldToLocal(t.getSecondaryAnchorPoint());

		ArrowheadShape arrowheadShape = t.getArrowheadShape();
		if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
			return false;
		}

		int arrowheadSize = t.getArrowheadSize();
		int[] xyPoints = ArrowheadUtils.calculateTriangularArrowhead(lp2.x, lp2.y, lp1.x, lp1.y, arrowheadSize);
		if (xyPoints == null) {
			return false;
		}

		Shape localShape;
		switch (arrowheadShape) {
		case TRIANGLE: {
			Polygon polygon = new Polygon();
			polygon.addPoint(xyPoints[0], xyPoints[1]);
			polygon.addPoint(xyPoints[2], xyPoints[3]);
			polygon.addPoint(xyPoints[4], xyPoints[5]);
			localShape = polygon;
		}
			break;
		case WEDGE: {
			Path2D path = new Path2D.Double();
			path.moveTo(xyPoints[0], xyPoints[1]);
			path.lineTo(xyPoints[2], xyPoints[3]);
			path.lineTo(xyPoints[4], xyPoints[5]);
			localShape = path;
		}
			break;
		default:
			return false;
		}

		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			r.fillShape(localShape, null, null);
		}
		if (r.setLineStyle(t)) {
			r.drawShape(localShape);
			r.resetLineStyle();
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (lbb.contains(location.getLocalPoint())) {
			ArrowheadShape arrowheadShape = t.getArrowheadShape();
			if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
				return false;
			}

			Point lp1 = cm.worldToLocal(t.getAnchorPoint());
			Point lp2 = cm.worldToLocal(t.getSecondaryAnchorPoint());
			int arrowheadSize = t.getArrowheadSize();
			int[] xyPoints = ArrowheadUtils.calculateTriangularArrowhead(lp2.x, lp2.y, lp1.x, lp1.y, arrowheadSize);
			if (xyPoints == null) {
				return false;
			}

			return new Polygon(new int[] { xyPoints[0], xyPoints[2], xyPoints[4] }, new int[] { xyPoints[1],
					xyPoints[3], xyPoints[5] }, 3).contains(location.getLocalPoint().x, location.getLocalPoint().y);
		}

		return false;
	}

}
