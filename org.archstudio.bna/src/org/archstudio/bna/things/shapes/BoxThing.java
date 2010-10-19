package org.archstudio.bna.things.shapes;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class BoxThing extends AbstractThing implements IHasMutableBoundingBox, IHasMutableColor, IHasMutableSecondaryColor, IHasMutableGradientFill,
        IHasMutableRoundedCorners, IMutableMirrorsBoundingBox {

	public BoxThing() {
		this(BNAUtils.generateUID(BoxThing.class.getName()));
	}

	public BoxThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setGradientFilled(false);
		setCornerWidth(0);
		setCornerHeight(0);
	}

	protected int getMinimumHeight() {
		return 5;
	}

	protected int getMinimumWidth() {
		return 5;
	}

	/*
	 * public void moveRelative(int dx, int dy){ Rectangle r = getBoundingBox();
	 * r.x += dx; r.y += dy; setBoundingBox(r); }
	 */
	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
	}

	public void setBoundingBox(Rectangle r) {
		Rectangle nr = BNAUtils.normalizeRectangle(r);
		/*
		 * if(nr.height < getMinimumHeight()){ nr.height = getMinimumHeight(); }
		 * if(nr.width < getMinimumWidth()){ nr.width = getMinimumWidth(); }
		 */
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

	public void setSecondaryColor(RGB c) {
		setProperty(SECONDARY_COLOR_PROPERTY_NAME, c);
	}

	public RGB getSecondaryColor() {
		return getProperty(SECONDARY_COLOR_PROPERTY_NAME);
	}

	public boolean isGradientFilled() {
		return getProperty(GRADIENT_FILLED_PROPERTY_NAME);
	}

	public void setGradientFilled(boolean newHasGradientFill) {
		setProperty(GRADIENT_FILLED_PROPERTY_NAME, newHasGradientFill);
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

	public int getCornerHeight() {
		return getProperty(CORNER_HEIGHT_PROPERTY_NAME);
	}

	public void setCornerHeight(int cornerHeight) {
		setProperty(CORNER_HEIGHT_PROPERTY_NAME, cornerHeight);
	}

	public int getCornerWidth() {
		return getProperty(CORNER_WIDTH_PROPERTY_NAME);
	}

	public void setCornerWidth(int cornerWidth) {
		setProperty(CORNER_WIDTH_PROPERTY_NAME, cornerWidth);
	}

}
