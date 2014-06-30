package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasScaled extends IThing {

	public static final IThingKey<Boolean> SCALED_KEY = ThingKey.create("scaled");

	public boolean isScaled();
}
