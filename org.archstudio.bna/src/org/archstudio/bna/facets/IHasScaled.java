package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasScaled {

	public static final IThingKey<Boolean> SCALED_KEY = ThingKey.create("scaled");

	public boolean isScaled();
}
