package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasCount extends IThing {

	public static final IThingKey<Integer> COUNT_KEY = ThingKey.create("count");

	public int getCount();
}
