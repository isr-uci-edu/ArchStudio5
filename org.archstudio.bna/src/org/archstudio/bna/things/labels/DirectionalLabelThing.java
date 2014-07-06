package org.archstudio.bna.things.labels;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class DirectionalLabelThing extends DirectionalLabelThingBase {

	public DirectionalLabelThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public Point getReferencePoint() {
		Rectangle r = getRawBoundingBox();
		return new Point(r.x + r.width / 2, r.y + r.height / 2);
	}

	@Override
	public void setReferencePoint(Point value) {
		Point oldReferencePoint = getReferencePoint();
		Rectangle r = getBoundingBox();
		r.x += value.x - oldReferencePoint.x;
		r.y += value.y - oldReferencePoint.y;
		setRawBoundingBox(r);
	}

}
