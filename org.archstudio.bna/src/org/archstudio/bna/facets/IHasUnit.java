package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;

public interface IHasUnit extends IThing {

	public static final IThingKey<Integer> UNIT_KEY = ThingKey.create("unit");

	public int getUnit();

}
