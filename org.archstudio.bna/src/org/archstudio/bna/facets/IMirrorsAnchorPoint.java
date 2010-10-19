package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IMirrorsAnchorPoint extends IHasMutableAnchorPoint {
	public static final String ANCHOR_POINT_MASTER_THING_ID_PROPERTY_NAME = "&anchorPointMaster";
	public static final String ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME = "anchorPointMirrorOffsets";

	public String getAnchorPointMasterThingID();

	public Point getAnchorPointMirrorOffsets();
}
