package org.archstudio.bna.utils;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasArrowhead;
import org.eclipse.swt.graphics.Point;

public class ShapeUtils {

	public static final Shape createArrowhead(IHasArrowhead t, ICoordinateMapper cm) {
		ArrowheadShape arrowhead = t.getArrowheadShape();
		switch (arrowhead) {
		case TRIANGLE:
		case WEDGE: {

			Point lap = cm.worldToLocal(t.getAnchorPoint());
			Point lsp = cm.worldToLocal(t.getSecondaryAnchorPoint());
			if (lap.equals(lsp)) {
				return null;
			}

			double stemLength = cm.getLocalScale() * t.getArrowheadStemLength();
			int wedgeAngle = t.getArrowheadAngle();

			double tipX = lap.x, tipY = lap.y;
			double stemX = lsp.x, stemY = lsp.y;
			double dx = stemX - tipX, dy = stemY - tipY;
			double angle = Math.atan2(dy, dx);
			double radius = stemLength;
			double wedge2 = Math.PI * wedgeAngle / 180 / 2;
			Point2D t1 = new Point2D.Double(//
					tipX + radius * Math.cos(angle - wedge2), tipY + radius * Math.sin(angle - wedge2));
			Point2D ta = new Point2D.Double(tipX, tipY);
			Point2D t2 = new Point2D.Double(//
					tipX + radius * Math.cos(angle + wedge2), tipY + radius * Math.sin(angle + wedge2));

			Path2D path = new Path2D.Double();
			path.moveTo(t1.getX(), t1.getY());
			path.lineTo(ta.getX(), ta.getY());
			path.lineTo(t2.getX(), t2.getY());
			if (arrowhead == ArrowheadShape.TRIANGLE) {
				path.closePath();
			}

			return path;
		}
		case NONE:
			return null;
		}

		throw new IllegalArgumentException(arrowhead.toString());
	}

	public static Shape createUnitX(double width, double centerBendInward, double cornerBendInward) {
		width /= 2;
		Path2D.Double path = new Path2D.Double();
		path.moveTo(-0.5 + width, -0.5);
		path.lineTo(0, 0 - width + centerBendInward);
		path.lineTo(0.5 - width, -0.5);
		path.lineTo(0.5 - width / 2 - cornerBendInward, -0.5 + width / 2 + cornerBendInward);
		path.lineTo(0.5, -0.5 + width);
		path.lineTo(0 + width - centerBendInward, 0);
		path.lineTo(0.5, 0.5 - width);
		path.lineTo(0.5 - width / 2 - cornerBendInward, 0.5 - width / 2 - cornerBendInward);
		path.lineTo(0.5 - width, 0.5);
		path.lineTo(0, 0 + width - centerBendInward);
		path.lineTo(-0.5 + width, 0.5);
		path.lineTo(-0.5 + width / 2 + cornerBendInward, 0.5 - width / 2 - cornerBendInward);
		path.lineTo(-0.5, 0.5 - width);
		path.lineTo(0 - width + centerBendInward, 0);
		path.lineTo(-0.5, -0.5 + width);
		path.lineTo(-0.5 + width / 2 + cornerBendInward, -0.5 + width / 2 + cornerBendInward);
		path.closePath();
		return path;
	}
}
