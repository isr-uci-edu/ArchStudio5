package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IIsHidden extends IThing {

	public static final IThingKey<Boolean> HIDDEN_KEY = ThingKey.create("is-hidden");

}
