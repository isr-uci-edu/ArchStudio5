package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.RGB;

public interface IHasTint extends IThing {
	public static final IThingKey<RGB> TINT_KEY = ThingKey.create("tint");
}
