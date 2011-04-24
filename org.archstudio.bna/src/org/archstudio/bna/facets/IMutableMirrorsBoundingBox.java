package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Rectangle;

public interface IMutableMirrorsBoundingBox extends IMirrorsBoundingBox {
	public void setBoundingBoxMasterThingID(String boundingBoxMasterThingID);

	public void setBoundingBoxMirrorOffsets(Rectangle newOffsets);
}
