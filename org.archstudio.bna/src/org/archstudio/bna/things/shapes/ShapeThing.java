package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class ShapeThing extends ShapeThingBase {

	public ShapeThing(@Nullable Object id) {
		super(id);
		updateProperties();
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					updateProperties();
				}
			}
		});
	}

	protected void updateProperties() {
		Shape shape = getRawShape();
		Dimension size = getRawSize();
		Point2D anchor = getRawAnchorPoint();
		Rectangle2D bounds = shape.getBounds2D();
		double x = bounds.getMinX() * size.width + anchor.getX();
		double y = bounds.getMinY() * size.height + anchor.getY();
		double w = bounds.getWidth() * size.width;
		double h = bounds.getHeight() * size.height;
		int ix = SystemUtils.floor(x);
		int iy = SystemUtils.floor(y);
		int iw = SystemUtils.ceil(x + w) - ix;
		int ih = SystemUtils.ceil(y + h) - iy;
		setRawBoundingBox(new Rectangle(ix, iy, iw, ih));
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}

}
