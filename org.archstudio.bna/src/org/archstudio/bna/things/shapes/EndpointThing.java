package org.archstudio.bna.things.shapes;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Point2D;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class EndpointThing extends EndpointThingBase {

	public EndpointThing(@Nullable Object id) {
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
		Point2D anchor = getRawAnchorPoint();
		Dimension size = getRawSize();
		int x = SystemUtils.round(anchor.getX() - (double) size.width / 2);
		int y = SystemUtils.round(anchor.getY() - (double) size.height / 2);
		setRawBoundingBox(new Rectangle(x, y, size.width, size.height));
	}

	@Override
	public Point getReferencePoint() {
		return BNAUtils.toPoint(getRawAnchorPoint());
	}

	@Override
	public void setReferencePoint(Point value) {
		setRawAnchorPoint(BNAUtils.toPoint2D(value));
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}

	@Override
	public Shape getStickyShape() {
		return BNAUtils.toRectangle2D(getRawBoundingBox());
	}

}
