package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableInternalWorldEndpoint;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractMappingThing extends AbstractAnchorPointThing implements IHasMutableInternalWorldEndpoint {

	public AbstractMappingThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setInternalEndpoint(new Point(0, 0));
	}

	public Object getInternalEndpointWorldThingID() {
		return get(INTERNAL_ENDPOINT_WORLD_THING_KEY);
	}

	public void setInternalEndpointWorldThingID(Object worldThingID) {
		set(INTERNAL_ENDPOINT_WORLD_THING_KEY, worldThingID);
	}

	public Point getInternalEndpoint() {
		return get(INTERNAL_ENDPOINT_KEY);
	}

	public void setInternalEndpoint(Point internalWorldPoint) {
		set(INTERNAL_ENDPOINT_KEY, internalWorldPoint);
	}
}
