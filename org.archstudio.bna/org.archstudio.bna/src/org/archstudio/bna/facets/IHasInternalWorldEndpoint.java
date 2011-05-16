package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic.ReferenceThingKey;
import org.eclipse.draw2d.geometry.Point;

public interface IHasInternalWorldEndpoint extends IThing {

	public static final ReferenceThingKey<String> INTERNAL_ENDPOINT_WORLD_THING_ID_KEY = ReferenceThingKey
			.createKey("&internalEndpointWorldThingID");
	public static final IThingKey<Point> INTERNAL_ENDPOINT_KEY = ThingKey.create("internalEndpoint");

	public String getInternalEndpointWorldThingID();

	public Point getInternalEndpoint();
}
