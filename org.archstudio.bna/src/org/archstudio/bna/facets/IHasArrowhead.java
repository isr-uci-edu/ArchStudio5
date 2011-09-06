package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.keys.ThingKey;

public interface IHasArrowhead extends IThing {

	public static final IThingKey<ArrowheadShape> ARROWHEAD_SHAPE_KEY = ThingKey.create("arrowheadShape");
	public static final IThingKey<Integer> ARROWHEAD_SIZE_KEY = ThingKey.create("arrowheadSize");

	public ArrowheadShape getArrowheadShape();

	public int getArrowheadSize();
}
