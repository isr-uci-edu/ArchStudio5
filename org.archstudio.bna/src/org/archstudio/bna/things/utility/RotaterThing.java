package org.archstudio.bna.things.utility;

import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableRadius;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class RotaterThing extends AbstractAnchorPointThing implements IHasBoundingBox, IHasMutableAngle,
		IHasMutableRadius {

	public static final IThingKey<Integer> ROTATED_THING_IDS_KEY = ThingKey.create("rotatedThingIDs");
	public static final IThingKey<Integer> ADJUSTMENT_INCREMENT_KEY = ThingKey.create("adjustmentIncrement");

	public RotaterThing() {
		this(null);
	}

	public RotaterThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setAdjustmentIncrement(15);
		setRadius(50);
		setAngle(0);
	}

	@Override
	public int getRadius() {
		return get(RADIUS_KEY);
	}

	@Override
	public void setRadius(int radius) {
		set(RADIUS_KEY, radius);
	}

	@Override
	public Rectangle getBoundingBox() {
		Point anchorPoint = getAnchorPoint();
		int radius = getRadius();
		return new Rectangle(anchorPoint.x - radius, anchorPoint.y - radius, radius * 2, radius * 2);
	}

	@Override
	public void setAngle(int degrees) {
		set(ANGLE_KEY, degrees);
	}

	@Override
	public int getAngle() {
		return get(ANGLE_KEY);
	}

	public void setAdjustmentIncrement(int increment) {
		set(ADJUSTMENT_INCREMENT_KEY, increment);
	}

	public int getAdjustmentIncrement() {
		return get(ADJUSTMENT_INCREMENT_KEY);
	}
}
