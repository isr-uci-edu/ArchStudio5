package org.archstudio.bna.things.borders;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.*;

public class PulsingBorderThing extends AbstractThing implements IHasMutableBoundingBox, IHasMutableColor, IMutableMirrorsBoundingBox,
        IHasMutableRotatingOffset {

	public PulsingBorderThing() {
		this(BNAUtils.generateUID(PulsingBorderThing.class.getName()));
	}

	public PulsingBorderThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setColor(new RGB(255, 0, 0));
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, 0);
	}

	public Rectangle getBoundingBox() {
		Rectangle r = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(r.x, r.y, r.width, r.height);
	}

	public void setBoundingBox(Rectangle r) {
		setProperty(BOUNDING_BOX_PROPERTY_NAME, r);
	}

	public void setBoundingBox(int x, int y, int width, int height) {
		setBoundingBox(new Rectangle(x, y, width, height));
	}

	public void moveRelative(int dx, int dy) {
		Rectangle r = getBoundingBox();
		r.x += dx;
		r.y += dy;
		setBoundingBox(r);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
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

	public int getRotatingOffset() {
		return getProperty(ROTATING_OFFSET_PROPERTY_NAME);
	}

	public void incrementRotatingOffset() {
		int i = getProperty(ROTATING_OFFSET_PROPERTY_NAME);
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, i + 1);
	}
}
