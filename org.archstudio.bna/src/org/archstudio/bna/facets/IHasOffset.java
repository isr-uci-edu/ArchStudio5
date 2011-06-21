package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasOffset {

	public static final IThingKey<Integer> OFFSET_KEY = ThingKey.create("offset");

	public int getOffset();

}
