package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasRadius extends IThing {
	public static final IThingKey<Integer> RADIUS_KEY = ThingKey.create("radius");

	public int getRadius();
}
