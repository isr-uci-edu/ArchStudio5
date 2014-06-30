package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasSelected extends IThing {

	public static final IThingKey<Boolean> SELECTED_KEY = ThingKey.create("selected");

	public boolean isSelected();
}
