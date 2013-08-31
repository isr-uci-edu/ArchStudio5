package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasAlpha {
	public static final IThingKey<Float> ALPHA_KEY = ThingKey.create("alpha");

	public float getAlpha();
}
