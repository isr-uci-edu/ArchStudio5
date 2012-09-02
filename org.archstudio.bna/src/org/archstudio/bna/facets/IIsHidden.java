package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IIsHidden {

	public static final IThingKey<Boolean> HIDDEN_KEY = ThingKey.create("is-hidden");

}
