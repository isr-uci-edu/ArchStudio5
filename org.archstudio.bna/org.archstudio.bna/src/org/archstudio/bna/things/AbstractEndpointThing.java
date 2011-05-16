package org.archstudio.bna.things;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableSize;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractEndpointThing extends AbstractAnchorPointThing implements IHasBoundingBox,
		IHasMutableSize {

	public AbstractEndpointThing(Object id) {
		super(id);
		addThingListener(new IThingListener() {
			@Override
			public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
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
	}

	Rectangle calculateBoundingBox() {
		Point p = getAnchorPoint();
		Dimension size = getSize();
		return new Rectangle(p.x - size.width / 2, p.y - size.height / 2, size.width, size.height);
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
