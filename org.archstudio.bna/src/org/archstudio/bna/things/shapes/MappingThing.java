package org.archstudio.bna.things.shapes;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.BNAUtils;

/*
 * Same as an ordinary SplineThing, except the 2nd endpoint is interpreted by
 * the peer to be in the coordinate space of the inner view
 */
public class MappingThing extends SplineThing {
	public static final String WORLD_THING_ID = "&worldThingID";

	public MappingThing() {
		this(BNAUtils.generateUID(MappingThing.class.getName()));
	}

	public MappingThing(String id) {
		super(id);
	}

	public String getWorldThingID() {
		return getProperty(WORLD_THING_ID);
	}

	public void setWorldThingID(String id) {
		setProperty(WORLD_THING_ID, id);
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
