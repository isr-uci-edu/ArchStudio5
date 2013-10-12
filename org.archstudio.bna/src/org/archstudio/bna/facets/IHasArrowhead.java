package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.keys.ThingKey;

public interface IHasArrowhead extends IThing, IHasAnchorPoint, IHasSecondaryAnchorPoint {

	public static final IThingKey<ArrowheadShape> ARROWHEAD_SHAPE_KEY = ThingKey.create("arrowheadShape");
	public static final IThingKey<Integer> ARROWHEAD_STEM_LENGTH_KEY = ThingKey.create("arrowheadStemLength");
	public static final IThingKey<Integer> ARROWHEAD_ANGLE_KEY = ThingKey.create("arrowheadAngle");

	public ArrowheadShape getArrowheadShape();

	public int getArrowheadStemLength();

	public int getArrowheadAngle();
}
