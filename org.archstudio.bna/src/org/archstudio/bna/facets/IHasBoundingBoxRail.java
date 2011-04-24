package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;

public interface IHasBoundingBoxRail extends IThing, IHasMutableAnchorPoint {
	public static final String BOUNDING_BOX_RAIL_MASTER_THING_ID_PROPERTY_NAME = "&boundingBoxRailMasterThingID";

	public String getBoundingBoxRailMasterThingID();
}
