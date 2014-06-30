package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasUserEditable extends IThing {
	public static final IThingKey<Boolean> USER_EDITABLE_KEY = ThingKey.create("userEditable");

	public boolean isUserEditable();
}
