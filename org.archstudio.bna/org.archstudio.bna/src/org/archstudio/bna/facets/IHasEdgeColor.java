package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.RGB;

public interface IHasEdgeColor extends IThing {

	public static final IThingKey<RGB> EDGE_COLOR_KEY = ThingKey.create("edgeColor");

	public RGB getEdgeColor();
}
