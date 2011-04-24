package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IMirrorsPathData extends IHasMutablePathData {

	public static final String PATH_DATA_MASTER_THING_ID_PROPERTY_NAME = "&pathDataMaster";

	public static final String PATH_DATA_MIRROR_OFFSETS_PROPETY_NAME = "pathDataMirrorOffsets";

	public abstract String getPathDataMasterThingID();

	public abstract void setPathDataMasterThingID(String pathMasterThingID);

	public abstract Point getPathDataMirrorOffsets();

	public abstract void setPathDataMirrorOffsets(Point newOffsets);

}