package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasStandardCursor extends IHasCursor, IThing {
	public static final IThingKey<Integer> STANDARD_CURSOR_KEY = ThingKey.create("standardCursor");

	public int getStandardCursor();
}
