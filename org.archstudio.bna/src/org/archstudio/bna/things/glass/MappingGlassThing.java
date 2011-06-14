package org.archstudio.bna.things.glass;

import org.archstudio.bna.facets.IHasMutableInternalWorldMapping;
import org.archstudio.bna.things.MappingEssenceThing;

public class MappingGlassThing extends MappingEssenceThing implements IHasMutableInternalWorldMapping {

	public MappingGlassThing(Object id) {
		super(id);
	}

	public String getInternalEndpointStuckToThingID() {
		return get(INTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY);
	}

	public void setInternalEndpointStuckToThingID(String internalWorldThingID) {
		set(INTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY, internalWorldThingID);
	}
}
