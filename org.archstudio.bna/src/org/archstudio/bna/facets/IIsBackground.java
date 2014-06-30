package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IIsBackground extends IThing {

	public static final IThingKey<Boolean> BACKGROUND_KEY = ThingKey.create("background");

}
