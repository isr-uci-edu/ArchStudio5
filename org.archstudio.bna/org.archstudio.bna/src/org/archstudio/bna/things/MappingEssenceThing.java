package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableInternalWorldEndpoint;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;

public class MappingEssenceThing extends AbstractAnchorPointThing implements IHasMutableInternalWorldEndpoint {

	public MappingEssenceThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setInternalEndpoint(new Point(0, 0));
	}

	public String getInternalEndpointWorldThingID() {
		K key = INTERNAL_ENDPOINT_WORLD_THING_ID_KEY;
		return get(key);
	}

	public void setInternalEndpointWorldThingID(String worldThingID) {
		put(INTERNAL_ENDPOINT_WORLD_THING_ID_KEY, worldThingID);
	}

	public Point getInternalEndpoint() {
		return get(INTERNAL_ENDPOINT_KEY);
	}

	public void setInternalEndpoint(Point internalWorldPoint) {
		put(INTERNAL_ENDPOINT_KEY, BNAUtils.clone(internalWorldPoint));
	}
}
