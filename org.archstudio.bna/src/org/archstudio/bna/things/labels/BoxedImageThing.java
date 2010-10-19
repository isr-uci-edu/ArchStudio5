package org.archstudio.bna.things.labels;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableHorizontalAlignment;
import org.archstudio.bna.facets.IHasMutableImage;
import org.archstudio.bna.facets.IHasMutableVerticalAlignment;
import org.archstudio.bna.facets.IMutableMirrorsBoundingBox;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

public class BoxedImageThing extends AbstractThing implements IHasMutableBoundingBox, IMutableMirrorsBoundingBox, IHasMutableHorizontalAlignment,
        IHasMutableVerticalAlignment, IHasMutableImage {

	public BoxedImageThing() {
		this(BNAUtils.generateUID(BoxedImageThing.class.getName()));
	}

	public BoxedImageThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setImagePath(null);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
	}

	public void setBoundingBox(Rectangle r) {
		Rectangle nr = BNAUtils.normalizeRectangle(r);
		setProperty(BOUNDING_BOX_PROPERTY_NAME, nr);
	}

	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
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

	public HorizontalAlignment getHorizontalAlignment() {
		return (HorizontalAlignment) getProperty(HORIZONTAL_ALIGNMENT_PROPERTY_NAME);
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		setProperty(HORIZONTAL_ALIGNMENT_PROPERTY_NAME, horizontalAlignment);
	}

	public VerticalAlignment getVerticalAlignment() {
		return (VerticalAlignment) getProperty(VERTICAL_ALIGNMENT_PROPERTY_NAME);
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		setProperty(VERTICAL_ALIGNMENT_PROPERTY_NAME, verticalAlignment);
	}

	public String getImagePath() {
		return (String) getProperty(IMAGE_PATH_PROPERTY_NAME);
	}

	public void setImagePath(String s) {
		setProperty(IMAGE_PATH_PROPERTY_NAME, s);
	}

}
