package org.archstudio.bna.facets;

import org.archstudio.bna.keys.ThingKey;

public interface IHasScaled {

	public static final ThingKey<Boolean, V> SCALED_KEY = ThingKey.createKey("scaled");

	public boolean isScaled();
}
