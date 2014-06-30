package org.archstudio.bna.facets;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasWorld extends IThing {

	public static final IThingKey<IBNAWorld> WORLD_KEY = ThingKey.create("$world");

	public IBNAWorld getWorld();
}
