package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.eclipse.swt.graphics.Point;

public interface IHasInternalWorldEndpoint extends IThing {

	public static final IThingRefKey<IHasWorld> INTERNAL_ENDPOINT_WORLD_THING_KEY = ThingRefKey
			.create("&internalEndpointWorldThingID");
	public static final IThingKey<Point> INTERNAL_ENDPOINT_KEY = ThingKey.create("internalEndpoint");

	public Object getInternalEndpointWorldThingID();

	public Point getInternalEndpoint();

}
