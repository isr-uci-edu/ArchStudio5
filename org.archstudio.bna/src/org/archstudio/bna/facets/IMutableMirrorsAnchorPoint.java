package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IMutableMirrorsAnchorPoint extends IMirrorsAnchorPoint {
	public void setAnchorPointMasterThingID(String anchorPointMasterThingID);

	public void setAnchorPointMirrorOffsets(Point newOffsets);
}
