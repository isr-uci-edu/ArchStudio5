package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasLife extends IThing {
	public static final IThingKey<Integer> LIFE_KEY = ThingKey.create("life");

	public int getLife();
}
