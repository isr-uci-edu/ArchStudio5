package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Lists;

public class EnvironmentPropertiesThing extends NoThing {

	public static final IThingKey<Point> NEW_THING_SPOT_KEY = ThingKey.create(Lists.newArrayList("newThingSpot",
			EnvironmentPropertiesThing.class));
	public static final IThingKey<Double> LOCAL_SCALE_KEY = ThingKey.create(Lists.newArrayList("local-scale",
			EnvironmentPropertiesThing.class));
	public static final IThingKey<Point> LOCAL_ORIGIN_KEY = ThingKey.create(Lists.newArrayList("local-origin",
			EnvironmentPropertiesThing.class));

	public static EnvironmentPropertiesThing createIn(IBNAWorld world) {
		EnvironmentPropertiesThing thing = getIn(world);
		if (thing == null) {
			thing = world.getBNAModel().addThing(new EnvironmentPropertiesThing());
		}
		return thing;
	}

	public static EnvironmentPropertiesThing getIn(IBNAWorld world) {
		return (EnvironmentPropertiesThing) world.getBNAModel().getThing(EnvironmentPropertiesThing.class);
	}

	protected EnvironmentPropertiesThing() {
		super(EnvironmentPropertiesThing.class);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setNewThingSpot(new Point(0, 0));
	}

	public Point getNewThingSpot() {
		return get(NEW_THING_SPOT_KEY);
	}

	public void setNewThingSpot(Point point) {
		set(NEW_THING_SPOT_KEY, point);
	}

	public void restoreCoordinateMapperData(IMutableCoordinateMapper cm) {
		cm.setLocalOrigin(get(LOCAL_ORIGIN_KEY, new Point(0, 0)));
		cm.setLocalScale(get(LOCAL_SCALE_KEY, 1d));
	}

	public void storeCoordinateMapperData(ICoordinateMapper cm) {
		set(LOCAL_ORIGIN_KEY, cm.getLocalOrigin());
		set(LOCAL_SCALE_KEY, cm.getLocalScale());
	}

}
