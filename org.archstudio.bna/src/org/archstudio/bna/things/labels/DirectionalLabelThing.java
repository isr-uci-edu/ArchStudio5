package org.archstudio.bna.things.labels;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class DirectionalLabelThing extends AbstractThing implements IHasMutableBoundingBox, IHasMutableColor, IMutableMirrorsBoundingBox,
        IHasMutableOrientation, IHasMutableFlow {

	public DirectionalLabelThing() {
		this(BNAUtils.generateUID(DirectionalLabelThing.class.getName()));
	}

	public DirectionalLabelThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setOrientation(Orientation.NONE);
		setFlow(Flow.NONE);
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

	public Orientation getOrientation() {
		return getProperty(ORIENTATION_PROPERTY_NAME);
	}

	public void setOrientation(Orientation o) {
		setProperty(ORIENTATION_PROPERTY_NAME, o);
	}

	public Flow getFlow() {
		return getProperty(FLOW_PROPERTY_NAME);
	}

	public void setFlow(Flow f) {
		setProperty(FLOW_PROPERTY_NAME, f);
	}

}
