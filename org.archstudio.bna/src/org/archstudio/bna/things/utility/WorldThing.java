package org.archstudio.bna.things.utility;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.facets.IMutableMirrorsBoundingBox;

public class WorldThing extends AbstractThing implements IHasMutableBoundingBox, IMutableMirrorsBoundingBox, IHasMutableWorld {

	public WorldThing() {
		this(BNAUtils.generateUID(WorldThing.class.getName()));
	}

	public WorldThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
	}

	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
	}

	public void setBoundingBox(Rectangle r) {
		Rectangle nr = BNAUtils.normalizeRectangle(r);
		setProperty(BOUNDING_BOX_PROPERTY_NAME, nr);
	}

	public void setBoundingBox(int x, int y, int width, int height) {
		Rectangle r = new Rectangle(x, y, width, height);
		setBoundingBox(r);
	}

	public String getBoundingBoxMasterThingID() {
		return (String) getProperty(BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setBoundingBoxMasterThingID(String boundingBoxMasterThingID) {
		setProperty(BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME, boundingBoxMasterThingID);
	}

	public Rectangle getBoundingBoxMirrorOffsets() {
		return (Rectangle) getProperty(BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME);
	}

	public void setBoundingBoxMirrorOffsets(Rectangle newOffsets) {
		setProperty(BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME, newOffsets);
	}

	public IBNAWorld getWorld() {
		return getProperty(WORLD_PROPERTY_NAME);
	}

	public void setWorld(IBNAWorld world) {
		setProperty(WORLD_PROPERTY_NAME, world);
	}

	public void clearWorld() {
		removeProperty(WORLD_PROPERTY_NAME);
	}
}
