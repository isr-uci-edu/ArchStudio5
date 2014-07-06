package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class RectangleThing extends RectangleThingBase {

	public RectangleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public Shape getStickyShape() {
		Rectangle r = getRawBoundingBox();
		Dimension arc = getRawRoundCorners();
		if (arc.getWidth() == 0 && arc.getHeight() == 0) {
			return new Rectangle2D.Float(r.x, r.y, r.width, r.height);
		}
		return new RoundRectangle2D.Float(r.x, r.y, r.width, r.height, arc.width, arc.height);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}

	@Override
	public Point getReferencePoint() {
		Rectangle r = getRawBoundingBox();
		return new Point(r.x + r.width / 2, r.y + r.height / 2);
	}

	@Override
	public void setReferencePoint(Point value) {
		Point p = getReferencePoint();
		Rectangle r = getBoundingBox();
		r.x += value.x - p.x;
		r.y += value.y - p.y;
		setBoundingBox(r);
	}

}
