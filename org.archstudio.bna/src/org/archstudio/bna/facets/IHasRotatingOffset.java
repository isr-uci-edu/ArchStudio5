package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasRotatingOffset extends IThing {
	public static final IThingKey<Integer> ROTATING_OFFSET_KEY = ThingKey.create("rotatingOffset");

	public int getRotatingOffset();
}
