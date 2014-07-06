package org.archstudio.bna.utils;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.constants.ArrowheadShape;

public class ShapeUtils {

	public static final Shape createArrowhead(ArrowheadShape arrowhead, Point2D anchor, Point2D stem, double baseWidth,
			double height) {
		Path2D path = new Path2D.Double();

		switch (arrowhead) {
		case TRIANGLE:
		case WEDGE: {

			if (anchor.equals(stem)) {
				path.moveTo(anchor.getX(), anchor.getY());
				return path;
			}

			double tipX = anchor.getX(), tipY = anchor.getY();
			double stemX = stem.getX(), stemY = stem.getY();
			double angle = Math.atan2(stemY - tipY, stemX - tipX);
			double radius = Math.sqrt(baseWidth * baseWidth / 4 + height * height);
			double wedgeAngle = Math.atan2(baseWidth, height);
			Point2D t1 = new Point2D.Double(tipX + radius * Math.cos(angle - wedgeAngle), tipY + radius
					* Math.sin(angle - wedgeAngle));
			Point2D t2 = new Point2D.Double(tipX + radius * Math.cos(angle + wedgeAngle), tipY + radius
					* Math.sin(angle + wedgeAngle));

			path.moveTo(t1.getX(), t1.getY());
			path.lineTo(tipX, tipY);
			path.lineTo(t2.getX(), t2.getY());
			if (arrowhead == ArrowheadShape.TRIANGLE) {
				path.closePath();
			}

			return path;
		}
		default:
			return path;
		}
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
