package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasEditing extends IThing {
	public static final IThingKey<Boolean> EDITING_KEY = ThingKey.create("editing");

	public boolean isEditing();
}
