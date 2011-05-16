package org.archstudio.bna.facets;

import org.archstudio.bna.keys.ThingKey;

public interface IHasInternalWorldMapping extends IHasMutableInternalWorldEndpoint {

	public static final IThingKey<String> INTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY = IThingKey
			.createKey("internalEndpointStuckToThingID");

	public String getInternalEndpointStuckToThingID();
}
