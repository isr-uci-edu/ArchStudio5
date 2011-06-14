package org.archstudio.bna.things;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasPoints;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class AbstractSplineThing extends AbstractPointsThing implements IHasBoundingBox {

	public AbstractSplineThing(Object id) {
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
		set(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
	}

	protected Rectangle calculateBoundingBox() {
		List<Point> points = get(IHasPoints.POINTS_KEY);
		Rectangle allPoints = new Rectangle(points.get(0), points.get(1));
		for (int i = 2; i < points.size(); i++) {
			allPoints.union(points.get(i));
		}
		return allPoints;
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY);
	}
}
