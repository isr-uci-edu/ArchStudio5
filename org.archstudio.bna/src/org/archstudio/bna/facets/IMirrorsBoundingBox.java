package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Rectangle;

public interface IMirrorsBoundingBox extends IHasMutableBoundingBox {
	public static final String BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME = "&boundingBoxMaster";
	public static final String BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME = "boundingBoxMirrorOffsets";

	public String getBoundingBoxMasterThingID();

	public Rectangle getBoundingBoxMirrorOffsets();

}
