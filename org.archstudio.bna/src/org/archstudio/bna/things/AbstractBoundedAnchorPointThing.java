package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableSize;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractBoundedAnchorPointThing extends AbstractMutableAnchorPointThing implements
		IHasBoundingBox, IHasMutableSize {

	public AbstractBoundedAnchorPointThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setSize(new Dimension(6, 6));
		set(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
		addShapeModifyingKey(SIZE_KEY);
		addShapeModifyingKey(BOUNDING_BOX_KEY);
		super.initProperties();
		addThingListener(new IThingListener() {

			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (!BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					set(BOUNDING_BOX_KEY, calculateBoundingBox());
				}
			}
		});
		set(BOUNDING_BOX_KEY, calculateBoundingBox());
	}

	protected Rectangle calculateBoundingBox() {
		Point p = getAnchorPoint();
		Dimension size = getSize();
		return new Rectangle(p.x - size.width / 2, p.y - size.height / 2, size.width, size.height);
	}

	@Override
	protected Shape createStickyShape() {
		Point p = getAnchorPoint();
		Dimension size = getSize();
		return new Rectangle2D.Float(p.x - size.width / 2, p.y - size.height / 2, size.width, size.height);
	}

	@Override
	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	@Override
	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY);
	}
}