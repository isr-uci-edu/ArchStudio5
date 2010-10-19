package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.IHasAnchorPoint;

public class MappingGlassThing extends SplineGlassThing implements IHasAnchorPoint {
	public static final String EXTERNAL_ENDPOINT_STUCK_TO_THING_ID_PROPERTY_NAME = "&externalEndpointStuckToThingID";
	public static final String WORLD_THING_ID = "&worldThingID";
	public static final String INTERNAL_ENDPOINT_STUCK_TO_THING_ID_PROPERTY_NAME = "internalEndpointStuckToThingID";

	public MappingGlassThing() {
		this(BNAUtils.generateUID(MappingGlassThing.class.getName()));
	}

	public MappingGlassThing(String id) {
		super(id);
	}

	public String getExternalEndpointStuckToThingID() {
		return getProperty(EXTERNAL_ENDPOINT_STUCK_TO_THING_ID_PROPERTY_NAME);
	}

	public void setExternalEndpointStuckToID(String id) {
		setProperty(EXTERNAL_ENDPOINT_STUCK_TO_THING_ID_PROPERTY_NAME, id);
	}

	public String getWorldThingID() {
		return getProperty(WORLD_THING_ID);
	}

	public void setWorldThingID(String id) {
		setProperty(WORLD_THING_ID, id);
	}

	public String getInternalEndpointStuckToThingID() {
		return getProperty(INTERNAL_ENDPOINT_STUCK_TO_THING_ID_PROPERTY_NAME);
	}

	public void setInternalEndpointStuckToThingID(String id) {
		setProperty(INTERNAL_ENDPOINT_STUCK_TO_THING_ID_PROPERTY_NAME, id);
	}

	public Point getAnchorPoint() {
		return getEndpoint1();
	}

	public Rectangle getBoundingBox() {
		Rectangle r = super.getBoundingBox();
		if (r != null) {
			Point p = getEndpoint1();
			return new Rectangle(p.x, p.y, 1, 1);
		}
		return null;
	}

}
