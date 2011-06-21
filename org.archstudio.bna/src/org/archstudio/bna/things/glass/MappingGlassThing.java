package org.archstudio.bna.things.glass;


public class MappingGlassThing extends AbstractMappingThing implements IHasMutableInternalWorldMapping {

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
