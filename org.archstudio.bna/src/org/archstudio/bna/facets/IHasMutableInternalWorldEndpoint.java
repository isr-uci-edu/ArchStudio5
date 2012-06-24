package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IHasMutableInternalWorldEndpoint extends IHasInternalWorldEndpoint {

	public void setInternalEndpointWorldThingID(Object worldThingID);

	public void setInternalEndpoint(Point internalWorldPoint);

}
