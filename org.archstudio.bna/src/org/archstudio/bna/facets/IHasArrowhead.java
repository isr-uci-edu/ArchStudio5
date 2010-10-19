package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.ArrowheadShape;

public interface IHasArrowhead extends IThing {
	public static final String ARROWHEAD_SHAPE_PROPERTY_NAME = "arrowheadShape";
	public static final String ARROWHEAD_SIZE_PROPERTY_NAME = "arrowheadSize";
	public static final String ARROWHEAD_FILLED_PROPERTY_NAME = "arrowheadFilled";

	public ArrowheadShape getArrowheadShape();

	public int getArrowheadSize();

	public boolean isArrowheadFilled();
}
