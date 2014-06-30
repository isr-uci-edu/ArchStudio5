package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasWidth extends IThing {

	public static final IThingKey<Integer> WIDTH_KEY = ThingKey.create("width");

	public int getWidth();

}
