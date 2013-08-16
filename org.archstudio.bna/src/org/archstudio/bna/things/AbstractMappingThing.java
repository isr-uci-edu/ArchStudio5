package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableInternalWorldEndpoint;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractMappingThing extends AbstractMutableAnchorPointThing implements IHasMutableInternalWorldEndpoint {

	public AbstractMappingThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setInternalEndpoint(new Point(0, 0));
	}

	@Override
	public Object getInternalEndpointWorldThingID() {
		return get(INTERNAL_ENDPOINT_WORLD_THING_KEY);
	}

	@Override
	public void setInternalEndpointWorldThingID(Object worldThingID) {
		set(INTERNAL_ENDPOINT_WORLD_THING_KEY, worldThingID);
	}

	@Override
	public Point getInternalEndpoint() {
		return get(INTERNAL_ENDPOINT_KEY);
	}

	@Override
	public void setInternalEndpoint(Point internalWorldPoint) {
		set(INTERNAL_ENDPOINT_KEY, internalWorldPoint);
	}
}
