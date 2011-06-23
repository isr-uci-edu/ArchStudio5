package org.archstudio.bna.things.utility;

import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.draw2d.geometry.Point;

public class EnvironmentPropertiesThing extends NoThing {

	public static final String ENVIRONMENT_PROPERTIES_THING_ID = EnvironmentPropertiesThing.class.getName();

	public static final IThingKey<Point> NEW_THING_SPOT_KEY = CloneThingKey.create("#newThingSpot", false,
			CloneThingKey.point());

	public EnvironmentPropertiesThing() {
		super(ENVIRONMENT_PROPERTIES_THING_ID);
	}

	public Point getNewThingSpot() {
		return get(NEW_THING_SPOT_KEY);
	}

	public void setNewThingSpot(Point point) {
		set(NEW_THING_SPOT_KEY, point);
	}
}
