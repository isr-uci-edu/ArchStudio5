package org.archstudio.bna.things;

import java.awt.geom.Point2D;

import org.archstudio.bna.facets.IHasMutableInternalWorldEndpoint;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractMappingThing extends AbstractMutableAnchorPointThing implements
		IHasMutableInternalWorldEndpoint {

	public AbstractMappingThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setInternalEndpoint(new Point(0, 0));
		setExternalEndpoint(new Point2D.Double(0, 0));
		super.initProperties();
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

	@Override
	public Point2D getExternalEndpoint() {
		return get(EXTERNAL_ENDPOINT_KEY);
	}

	@Override
	public void setExternalEndpoint(Point2D externalWorldPoint) {
		set(EXTERNAL_ENDPOINT_KEY, externalWorldPoint);
	}
}
