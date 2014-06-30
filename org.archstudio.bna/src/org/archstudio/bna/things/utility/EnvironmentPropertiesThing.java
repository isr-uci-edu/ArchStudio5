package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class EnvironmentPropertiesThing extends NoThing {

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

	public static final IThingKey<Point> NEW_THING_SPOT_KEY = ThingKey.create("#newThingSpot", ThingKey.point());
	public static final IThingKey<Point> LAST_OPEN_SPOT_KEY = ThingKey.create("#lastOpenSpot", ThingKey.point());
	public static final IThingKey<Rectangle> MODEL_BOUNDS_KEY = ThingKey.create("modelBounds", ThingKey.rectangle());

	protected EnvironmentPropertiesThing() {
		super(EnvironmentPropertiesThing.class);
	}

	@Override
	protected void initProperties() {
		setNewThingSpot(new Point(0, 0));
		setLastOpenSpot(new Point(0, 0));
		setModelBounds(new Rectangle(0, 0, 0, 0));
		super.initProperties();
	}

	public Point getNewThingSpot() {
		return get(NEW_THING_SPOT_KEY);
	}

	public void setNewThingSpot(Point point) {
		set(NEW_THING_SPOT_KEY, point);
	}

	public Point getLastOpenSpot() {
		return get(LAST_OPEN_SPOT_KEY);
	}

	public void setLastOpenSpot(Point point) {
		set(LAST_OPEN_SPOT_KEY, point);
	}

	public void setModelBounds(Rectangle modelBounds) {
		set(MODEL_BOUNDS_KEY, modelBounds);
	}

	public Rectangle getModelBounds() {
		return get(MODEL_BOUNDS_KEY);
	}
}
