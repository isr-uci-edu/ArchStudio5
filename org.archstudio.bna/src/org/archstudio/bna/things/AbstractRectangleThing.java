package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableMinimumSize;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractRectangleThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutableBoundingBox, IHasMutableMinimumSize, IRelativeMovable, IIsSticky {

	public AbstractRectangleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					set(IIsSticky.STICKY_SHAPE_KEY, createStickyShape());
				}
			}
		});
		super.initProperties();
		setMinimumSize(new Dimension(5, 5));
		addShapeModifyingKey(MINIMUM_SIZE_KEY);
		addShapeModifyingKey(BOUNDING_BOX_KEY);
		setBoundingBox(new Rectangle(0, 0, 10, 10));
	}

	@Override
	public Dimension getMinimumSize() {
		return get(MINIMUM_SIZE_KEY, new Dimension(5, 5));
	}

	@Override
	public void setMinimumSize(final Dimension minimumDimension) {
		set(MINIMUM_SIZE_KEY, minimumDimension);
		Rectangle r = getBoundingBox();
		if (r != null) {
			setBoundingBox(r);
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
	}

	@Override
	public void setBoundingBox(final Rectangle r) {
		BNAUtils.normalizeRectangle(r);
		Dimension minDimension = getMinimumSize();
		if (minDimension != null) {
			if (r.width < minDimension.width) {
				r.width = minDimension.width;
			}
			if (r.height < minDimension.height) {
				r.height = minDimension.height;
			}
		}
		set(BOUNDING_BOX_KEY, r);
	}

	@Override
	public Point getReferencePoint() {
		Rectangle bounds = getBoundingBox();
		return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
	}

	@Override
	public void moveRelative(final Point worldDelta) {
		Rectangle bounds = getBoundingBox();
		bounds.x += worldDelta.x;
		bounds.y += worldDelta.y;
		setBoundingBox(bounds);
	}

	protected Shape createStickyShape() {
		Rectangle bounds = getBoundingBox();
		return new Rectangle2D.Float(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public Shape getStickyShape() {
		return get(IIsSticky.STICKY_SHAPE_KEY);
	}
}
