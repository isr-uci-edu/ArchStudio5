package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IMovesWith extends IRelativeMovable, IThing {
	public static final String MOVES_WITH_THING_ID_PROPERTY_NAME = "&movesWithThingID";
	public static final String MOVES_WITH_MODE_PROPERTY_NAME = "movesWithMode";

	public static final int TRACK_BOUNDING_BOX_ONLY = 1010;
	public static final int TRACK_ANCHOR_POINT_ONLY = 1025;
	public static final int TRACK_BOUNDING_BOX_FIRST = 1030;
	public static final int TRACK_ANCHOR_POINT_FIRST = 1050;

	public String getMovesWithThingID();

	public int getMovesWithMode();

}
