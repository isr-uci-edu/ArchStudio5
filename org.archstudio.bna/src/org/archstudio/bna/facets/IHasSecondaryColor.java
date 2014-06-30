package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.RGB;

public interface IHasSecondaryColor extends IThing {

	public static final IThingKey<RGB> SECONDARY_COLOR_KEY = ThingKey.create("secondaryColor", ThingKey.rgb());

	public RGB getSecondaryColor();
}
