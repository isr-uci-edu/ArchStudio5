package org.archstudio.bna.facets;

import java.awt.geom.Point2D;

import org.eclipse.swt.graphics.Point;

public interface IHasMutableInternalWorldEndpoint extends IHasInternalWorldEndpoint {

	public void setInternalEndpointWorldThingID(Object worldThingID);

	public void setInternalEndpoint(Point internalWorldPoint);

	public void setExternalEndpoint(Point2D externalWorldPoint);

}
