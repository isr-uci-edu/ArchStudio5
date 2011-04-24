package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IMutableMirrorsPathData extends IMirrorsPathData {
	public void setPathDataMasterThingID(String pathDataMasterThingID);

	public void setPathDataMirrorOffsets(Point newOffsets);
}
