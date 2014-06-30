package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasGradientFill extends IHasColor, IHasSecondaryColor {
	public static final IThingKey<Boolean> GRADIENT_FILLED_KEY = ThingKey.create("gradientFilled");

	public boolean isGradientFilled();
}
