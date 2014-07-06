package org.archstudio.bna.things.shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class EllipseThing extends EllipseThingBase {

	public EllipseThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public Shape getStickyShape() {
		Rectangle r = getRawBoundingBox();
		return new Ellipse2D.Float(r.x, r.y, r.width, r.height);
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
