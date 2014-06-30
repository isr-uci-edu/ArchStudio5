package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasLineWidth extends IThing {

	public static final IThingKey<Integer> LINE_WIDTH_KEY = ThingKey.create("lineWidth");

	public int getLineWidth();
}
