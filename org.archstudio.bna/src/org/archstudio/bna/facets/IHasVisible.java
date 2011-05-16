package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;

public interface IHasVisible extends IThing {
	public static final IThingKey<Boolean> VISIBLE_KEY = ThingKey.create("visible");

	public boolean isVisible();
}
