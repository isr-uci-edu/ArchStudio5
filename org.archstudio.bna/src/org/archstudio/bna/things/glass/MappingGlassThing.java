package org.archstudio.bna.things.glass;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;

public class MappingGlassThing extends SplineGlassThing implements IHasAnchorPoint {

	private static final IThingRefKey<IThing> EXTERNAL_ENDPOINT_STUCK_TO_THING_KEY = ThingRefKey
			.create("&externalEndpointStuckToThingID");
	private static final IThingRefKey<IHasWorld> WORLD_THING_KEY = ThingRefKey.create("&worldThingID");
	private static final IThingRefKey<IThing> INTERNAL_ENDPOINT_STUCK_TO_THING_KEY = ThingRefKey
			.create("&internalEndpointStuckToThingID");

	public MappingGlassThing() {
		this(BNAUtils.generateUID(MappingGlassThing.class.getName()));
	}

	public MappingGlassThing(String id) {
		super(id);
	}

	public Object getExternalEndpointStuckToThingID() {
		return get(EXTERNAL_ENDPOINT_STUCK_TO_THING_KEY);
	}

	public void setExternalEndpointStuckToID(Object id) {
		set(EXTERNAL_ENDPOINT_STUCK_TO_THING_KEY, id);
	}

	public Object getWorldThingID() {
		return get(WORLD_THING_KEY);
	}

	public void setWorldThingID(Object id) {
		set(WORLD_THING_KEY, id);
	}

	public Object getInternalEndpointStuckToThingID() {
		return get(INTERNAL_ENDPOINT_STUCK_TO_THING_KEY);
	}

	public void setInternalEndpointStuckToThingID(Object id) {
		set(INTERNAL_ENDPOINT_STUCK_TO_THING_KEY, id);
	}

	public Point getAnchorPoint() {
		return getEndpoint1();
	}

	//public Rectangle getBoundingBox() {
	//	Rectangle r = super.getBoundingBox();
	//	if (r != null) {
	//		Point p = getEndpoint1();
	//		return new Rectangle(p.x, p.y, 1, 1);
	//	}
	//	return null;
	//}
}
