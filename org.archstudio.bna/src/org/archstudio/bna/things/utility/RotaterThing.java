package org.archstudio.bna.things.utility;

import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableRadius;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractMutableAnchorPointThing;
import org.eclipse.jdt.annotation.Nullable;

public class RotaterThing extends AbstractMutableAnchorPointThing implements IHasMutableAngle, IHasMutableRadius {

	public static final IThingKey<Integer> ROTATED_THING_IDS_KEY = ThingKey.create("rotatedThingIDs");
	public static final IThingKey<Integer> ADJUSTMENT_INCREMENT_KEY = ThingKey.create("adjustmentIncrement");

	public RotaterThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setAdjustmentIncrement(15);
		setRadius(50);
		setAngle(0);
		super.initProperties();
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
