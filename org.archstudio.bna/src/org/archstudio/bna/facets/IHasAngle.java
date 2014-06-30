package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasAngle extends IThing {
	public static final IThingKey<Integer> ANGLE_KEY = ThingKey.create("angle");

	public int getAngle();
}
