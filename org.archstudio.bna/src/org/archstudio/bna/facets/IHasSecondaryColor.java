package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.swt.graphics.RGB;

public interface IHasSecondaryColor extends IThing {

	public static final IThingKey<RGB> SECONDARY_COLOR_KEY = CloneThingKey
			.create("secondaryColor", CloneThingKey.rgb());

	public RGB getSecondaryColor();
}
