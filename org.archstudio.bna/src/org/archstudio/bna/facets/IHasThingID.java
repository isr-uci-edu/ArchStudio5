package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasThingID {

	public static final IThingKey<Object> THING_ID_KEY = ThingKey.create("thingID");

}
