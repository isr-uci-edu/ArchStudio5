package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasAlpha extends IThing {
	public static final IThingKey<Float> ALPHA_KEY = ThingKey.create("alpha");

	public float getAlpha();
}
