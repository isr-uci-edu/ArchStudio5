package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasSpacing {

	public static final IThingKey<Integer> SPACING_KEY = ThingKey.create("spacing");

	public int getSpacing();
}
