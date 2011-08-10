package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;

public interface IHasTargetThingID {

	public static final IThingRefKey<IThing> THING_ID_KEY = ThingRefKey.create("thingID");

	public Object getThingID();

}
