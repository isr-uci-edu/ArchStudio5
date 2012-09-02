package org.archstudio.bna.things;

import java.awt.Dimension;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractBoundedAnchorPointThing extends AbstractAnchorPointThing implements IHasBoundingBox,
		IHasMutableSize {

	public AbstractBoundedAnchorPointThing(Object id) {
		super(id);
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (!IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
				}
			}
		});
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setSize(new Dimension(6, 6));
		set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
		addShapeModifyingKey(SIZE_KEY);
		addShapeModifyingKey(BOUNDING_BOX_KEY);
	}

	protected Rectangle calculateBoundingBox() {
		Point p = getAnchorPoint();
		Dimension size = getSize();
		return new Rectangle(p.x - size.width / 2, p.y - size.height / 2, size.width, size.height);
	}

	@Override
	public Dimension getSize() {
		return get(SIZE_KEY, new Dimension(6, 6));
	}

	@Override
	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
	}

	@Override
	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		Rectangle bb = getBoundingBox();
		switch (stickyMode) {
		case CENTER:
			return new Point(bb.x + bb.width / 2, bb.y + bb.height / 2);
		case EDGE:
			return BNAUtils.getClosestPointOnRectangle(bb, new Dimension(0, 0), nearPoint);
		case EDGE_FROM_CENTER:
			return BNAUtils.getClosestPointOnRectangle(bb, new Dimension(0, 0), nearPoint, new Point(bb.x + bb.width
					/ 2, bb.y + bb.height / 2));
		}
		throw new IllegalArgumentException(stickyMode.name());
	}
}