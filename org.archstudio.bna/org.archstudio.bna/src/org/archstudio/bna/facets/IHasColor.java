package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.RGBThingKey;
import org.eclipse.swt.graphics.RGB;

public interface IHasColor extends IThing {

	public static final IThingKey<RGB> COLOR_KEY = RGBThingKey.create("color");

	public RGB getColor();
}
